package com.watsonlogic.escapeforaminute;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class SettingsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupActionBar();
        if (savedInstanceState == null)
            initSettingsFragment();
    }

    private void setupActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initSettingsFragment()
    {
        getFragmentManager().beginTransaction()
            .add(R.id.fragment_container, new SettingsFragment()).commit();
    }

    public static class SettingsFragment extends Fragment implements DialogInterface
        .OnClickListener, View.OnClickListener
    {
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState)
        {
            ViewDataBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_settings, container, false);
            dataBinding.setVariable(BR.onClickListener, this);
            dataBinding.executePendingBindings();
            return dataBinding.getRoot();
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item)
        {
            int id = item.getItemId();
            if (id == android.R.id.home)
            {
                getActivity().finish();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.view_intro_layout:
                    new AlertDialog.Builder(v.getContext())
                        .setTitle(R.string.view_intro_screen_alert_title)
                        .setMessage(R.string.view_intro_screen_alert_body)
                        .setPositiveButton(R.string.view_intro_screen_alert_confirm, this)
                        .setNegativeButton(R.string.view_intro_screen_alert_cancel, this)
                        .show();
                    break;
            }
        }

        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            switch(which)
            {
                case DialogInterface.BUTTON_POSITIVE:
                    final Activity activity = getActivity();
                    activity.setResult(RESULT_OK);
                    activity.finish();
                    break;
            }
        }
    }
}
