package com.watsonlogic.escapeforaminute.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.watsonlogic.escapeforaminute.activity.ActivityScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class SharedPreferencesModule
{
    @ActivityScope
    @Provides
    static SharedPreferences providesSharedPreferences(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
