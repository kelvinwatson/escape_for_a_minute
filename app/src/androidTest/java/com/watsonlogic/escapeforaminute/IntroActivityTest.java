package com.watsonlogic.escapeforaminute;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class IntroActivityTest
{
    Context appContext;

    @Before
    public void init()
    {
        appContext = InstrumentationRegistry.getTargetContext();
        PreferenceManager.getDefaultSharedPreferences(appContext).edit().clear().commit();
    }

    @Test
    public void testShouldViewIntro()
    {
        assertThat(IntroActivity
            .shouldViewIntro(PreferenceManager.getDefaultSharedPreferences(appContext)), is(true));
        assertThat(IntroActivity
            .shouldViewIntro(PreferenceManager.getDefaultSharedPreferences(appContext)), is(false));

    }
}
