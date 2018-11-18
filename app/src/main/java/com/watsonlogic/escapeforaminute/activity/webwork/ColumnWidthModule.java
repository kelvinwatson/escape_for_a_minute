package com.watsonlogic.escapeforaminute.activity.webwork;

import com.watsonlogic.escapeforaminute.fragment.FragmentScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ColumnWidthModule
{
    @FragmentScope
    @Provides
    @Named("ColumnWidth")
    static int columnWidth()
    {
        return 300;
    }
}
