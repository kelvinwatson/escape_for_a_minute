package com.watsonlogic.escapeforaminute.activity.introactivity;

import com.watsonlogic.escapeforaminute.activity.ActivityScope;
import com.watsonlogic.escapeforaminute.activity.MainActivity;
import com.watsonlogic.escapeforaminute.activity.webwork.WebGalleryFragmentModule;
import com.watsonlogic.escapeforaminute.activity.webwork.WebWorkActivity;
import com.watsonlogic.escapeforaminute.app.SharedPreferencesModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

/**
 * 5. This class is to define bindings for our activity subcomponents so Dagger can inject them.
 * Here is where you define the @Binds @IntoMap for the various activities so the
 * dispatchingActivityInjector
 * <p>
 * {@link com.watsonlogic.escapeforaminute.app.App#dispatchingActivityInjector} can look in the
 * Map<Activity.class, ActivitySubComponent.class> to find the subcomponent to use for injecting
 * the activity and its dependencies.
 * <p>
 * Notice how IntroActivitySubcomponent is written out manually. This is the old way which uses
 *
 * @Binds @IntoMap. Instead, you can use @ContributesAndroidInjector, which would actually generate
 * the subcomponent automatically.
 * i.e. see how MainActivity is injected in {@link com.watsonlogic.escapeforaminute.app.AppModule}.
 */
@Module(subcomponents = IntroActivitySubcomponent.class)
// this links this ActivityBindingModule to the IntroActivitySubComponent, effectively adding
// IntroActivitySubComponent to the AppComponent, since ActivityBindingModule is inside the
// AppComponent
public abstract class ActivityBindingModule
{
    @Binds
    @IntoMap
    @ClassKey(IntroActivity.class)
    @ActivityScope
    abstract AndroidInjector.Factory<?> bindIntroActivityInjectorFactory
        (IntroActivitySubcomponent.Builder builder);

//    @Binds
//    @IntoMap
//    @ClassKey(MainActivity.class)
//    abstract AndroidInjector.Factory<?> bindMainActivityInjectorFactory
//        (MainActivitySubcomponent.Builder builder);

    // 6. Instead of the @Binds @IntoMap @ClassKey method above for IntroActivity, we can
    // actually use @ContributesAndroidInjector instead! It will auto generate the @Binds @Into @ClassKey for us.
    @ActivityScope
    @ContributesAndroidInjector(modules = SharedPreferencesModule.class)
    abstract MainActivity bindMainActivity();

    // 8. let's try this so that we can add injection to our WebWorkActivity to inject
    // our webGalleryFragment, and any other dependencies needed by the webGalleryFragment (e.g.
    // column width etc)
    @ActivityScope
    @ContributesAndroidInjector(modules = {WebGalleryFragmentModule.class})
    abstract WebWorkActivity bindWebWorkActivity();

}
