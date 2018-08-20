package com.watsonlogic.escapeforaminute;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import com.watsonlogic.escapeforaminute.databinding.ActivityIntroBinding;
import com.watsonlogic.escapeforaminute.viewmodel.IntroViewModel;

public class IntroActivity extends AppCompatActivity
{
    private static final String PREF_VIEWED_INTRO = "has_viewed_intro";
    private static final String STATE_VIEW_INTRO = "state_view_intro";
    private boolean shouldViewIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initDataBinding(savedInstanceState == null);

        if (savedInstanceState == null)
        {
            shouldViewIntro = shouldViewIntro(PreferenceManager
                .getDefaultSharedPreferences(this));
            if (!shouldViewIntro)
            {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
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
        ActivityIntroBinding binding = DataBindingUtil
            .setContentView(this, R.layout.activity_intro);

        if (!isInitialLoad)
            binding.setIntroViewModel(new IntroViewModel(true));
        else
            binding.setIntroViewModel(new IntroViewModel());

        binding.executePendingBindings();

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
