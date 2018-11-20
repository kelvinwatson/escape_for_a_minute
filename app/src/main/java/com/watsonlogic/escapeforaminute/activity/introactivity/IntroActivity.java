package com.watsonlogic.escapeforaminute.activity.introactivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.watsonlogic.escapeforaminute.BR;
import com.watsonlogic.escapeforaminute.OnClickListener;
import com.watsonlogic.escapeforaminute.R;
import com.watsonlogic.escapeforaminute.activity.MainActivity;
import com.watsonlogic.escapeforaminute.viewmodel.AppViewModel;
import com.watsonlogic.escapeforaminute.viewmodel.IntroViewModel;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjection;

public class IntroActivity extends AppCompatActivity implements OnClickListener
{
    @Inject
    Provider<SharedPreferences> defaultSharedPreferences;
    @Inject
    Application application;
    // @Inject Provider<SharedPreferences> is very similar to @Inject SharedPreferences except that
    // using provider is more efficient in cases where you only conditionally need the injected
    // variable. e.g. defaultSharedPreferences is only needed if savedInstanceState is null.
    private static final String PREF_VIEWED_INTRO = "has_viewed_intro";
    private static final String STATE_VIEW_INTRO = "state_view_intro";
    private boolean shouldViewIntro;

    @Override
    public boolean onClick(View view, AppViewModel viewModel)
    {
        if (viewModel instanceof IntroViewModel)
        {
            startMainActivityAndFinish();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);
        // Don't forget about this!
        // This gets the {@link App#dispatchingActivityInjector} from {@link App}, which does the
        // map<Activity.class, Subcomponent.Builder.class> look up so that you can inject
        // dependencies into this activity.

        super.onCreate(savedInstanceState);

        initDataBinding(savedInstanceState == null);

        if (savedInstanceState == null)
        {
            shouldViewIntro = shouldViewIntro(defaultSharedPreferences.get());
            if (!shouldViewIntro)
                startMainActivityAndFinish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_VIEW_INTRO, shouldViewIntro);
    }

    private void initDataBinding(boolean isInitialLoad)
    {
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_intro);

        binding.setVariable(BR.onClickListener, this);

        if (!isInitialLoad)
            binding.setVariable(BR.viewModelContent, new IntroViewModel(application,true));
        else
            binding.setVariable(BR.viewModelContent, new IntroViewModel(application));

        binding.executePendingBindings();
    }

    private void startMainActivityAndFinish()
    {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * Determines whether or not the intro screen should be shown. Will not show the intro screen
     * again once it's been shown for the first time.
     *
     * @param preferences
     * @return false if user has already seen the intro screen or if preferences is null,
     * otherwise true
     */
    @VisibleForTesting
    public static boolean shouldViewIntro(@Nullable SharedPreferences preferences)
    {
        if (preferences == null || preferences.getBoolean(PREF_VIEWED_INTRO, false))
            return false;

        return preferences.edit().putBoolean(PREF_VIEWED_INTRO, true).commit();
    }
}
