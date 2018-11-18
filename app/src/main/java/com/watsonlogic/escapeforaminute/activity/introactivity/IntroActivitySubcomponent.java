package com.watsonlogic.escapeforaminute.activity.introactivity;

import com.watsonlogic.escapeforaminute.app.SharedPreferencesModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = SharedPreferencesModule.class)
public interface IntroActivitySubcomponent extends AndroidInjector<IntroActivity>
{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<IntroActivity>
    {
    }
}
