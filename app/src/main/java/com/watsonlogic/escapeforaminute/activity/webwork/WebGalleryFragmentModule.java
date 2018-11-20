package com.watsonlogic.escapeforaminute.activity.webwork;

import com.watsonlogic.escapeforaminute.fragment.FragmentScope;
import com.watsonlogic.escapeforaminute.fragment.WebGalleryFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * provides dependencies (e.g. fragments) for WebWorkActivity.
 */
@Module
//    (includes = ColumnWidthModule.class) this works too, but we can put this in ActivityBindingModule
public abstract class WebGalleryFragmentModule
{
    @FragmentScope
    @ContributesAndroidInjector(modules = ColumnWidthModule.class)
    abstract WebGalleryFragment bindWebGalleryFragment();
}
