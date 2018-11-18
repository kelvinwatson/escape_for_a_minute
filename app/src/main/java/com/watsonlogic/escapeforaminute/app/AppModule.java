package com.watsonlogic.escapeforaminute.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule
{
    @Binds
    @Singleton
    abstract Context provideContext(Application application);

//    3. above is the same as
//    @Provides
//    @Singleton
//    static Context provideContext(Application application)
//    {
//        return application;
//    }
}
