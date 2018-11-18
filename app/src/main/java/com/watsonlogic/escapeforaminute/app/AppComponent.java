package com.watsonlogic.escapeforaminute.app;

import android.app.Application;

import com.watsonlogic.escapeforaminute.activity.introactivity.ActivityBindingModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

//1. define an AppComponent interface with an inner builder to seed the application instance
//3. AndroidInjectionModule.class is required for the @Binds @IntoMap stuff for
// contributesAndroidInjector
//4. It's good practice to define an ActivityBindingModule for the various activities to be
// injected.
//
@Singleton
// installing AppModule here means we can inject any objects provided by AppModule into anyway in
// the app. i.e. since AppModule provides application, we can literally inject it anywhere
@Component(modules = {AppModule.class, AndroidInjectionModule.class, ActivityBindingModule.class})
public interface AppComponent
{
    void inject(App app);

    @Component.Builder
    interface Builder
    {
        //2. Define a builder to seed AppModule with an application instance:
        //  This step is more of a convenience. If we don't do this, dagger will generate
        //  this builder anyway. However, we want to customize the builder
        //  This prevents having to define application variable in AppModule
        //  Now, AppModule has an "application" to provide/use (i.e. you can pretend that it has
        //  the class variable Application application.
        @BindsInstance
        Builder app(Application application);

        AppComponent build();
    }

}
