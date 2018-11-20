package com.watsonlogic.escapeforaminute.activity.webwork;

import com.watsonlogic.escapeforaminute.fragment.FragmentScope;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ColumnWidthModule
{
    @FragmentScope
    @Provides
    static ColumnWidth provideColumnWidth()
    {
        return new ColumnWidth(300);
    }

    public static class ColumnWidth
    {
        final int val;

        public ColumnWidth(int val)
        {
            this.val = val;
        }

        public int get()
        {
            return val;
        }
    }
}
