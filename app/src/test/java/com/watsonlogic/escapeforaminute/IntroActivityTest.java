package com.watsonlogic.escapeforaminute;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IntroActivityTest
{
    @Test
    public void testShouldViewIntroUninitializedPreferences()
    {
        SharedPreferences mockPrefs = mock(SharedPreferences.class);
        final SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);
        when(mockPrefs.edit()).thenReturn(editor);
        when(editor.putBoolean(anyString(), anyBoolean())).thenReturn(editor);
        when(editor.commit()).thenReturn(true);
        assertThat(IntroActivity.shouldViewIntro(mockPrefs), is(true));
    }

    @Test
    public void testShouldViewIntroPreviouslySetPreferences()
    {
        SharedPreferences mockPrefs = mock(SharedPreferences.class);
        final SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);
        when(mockPrefs.edit()).thenReturn(editor);
        when(editor.putBoolean(anyString(), anyBoolean())).thenReturn(editor);
        when(editor.commit()).thenReturn(true);
        assertThat(IntroActivity.shouldViewIntro(mockPrefs), is(true));
        assertThat(IntroActivity.shouldViewIntro(mockPrefs), is(false));
    }
}