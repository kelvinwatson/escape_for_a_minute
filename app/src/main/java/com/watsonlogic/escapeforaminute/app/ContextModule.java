package com.watsonlogic.escapeforaminute.app;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ContextModule
{
    @Provides
    static Context providesContext(App app)
    {
        return app.getApplicationContext();
    }
}
