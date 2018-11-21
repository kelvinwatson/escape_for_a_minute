package com.watsonlogic.escapeforaminute.activity.webwork;

import com.watsonlogic.escapeforaminute.fragment.FragmentScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ColumnWidthModule
{
    private static final int COLUMN_WIDTH = 300;

    @FragmentScope
    @Provides
    @Named("gridLayoutManagerColumnWidth")
    static int provideColumnWidth()
    {
        return COLUMN_WIDTH;
    }
}
