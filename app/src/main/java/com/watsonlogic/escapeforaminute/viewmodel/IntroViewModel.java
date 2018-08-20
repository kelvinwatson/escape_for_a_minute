package com.watsonlogic.escapeforaminute.viewmodel;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.view.View;

public class IntroViewModel
{
    private static final float STARTING_ALPHA = 0f;
    private static final float ENDING_ALPHA = 1f;
    public static final long imageAnimationDuration = 2000;
    public static final long titleAnimationDuration = 2500;
    public static final long subtitleAnimationDuration = 3500;
    public final boolean skipAnimation;

    public IntroViewModel()
    {
        this.skipAnimation = false;
    }

    public IntroViewModel(boolean skipAnimation)
    {
        this.skipAnimation = skipAnimation;
    }

    @BindingAdapter({"fadeInImageAnimationDuration", "shouldSkipAnimation"})
    public static void fadeInImage(@NonNull View view, long duration, boolean skipAnimation)
    {
        fadeIn(view, duration, skipAnimation);
    }

    @BindingAdapter({"fadeInTitleAnimationDuration", "shouldSkipAnimation"})
    public static void fadeInTitle(@NonNull View view, long duration, boolean skipAnimation)
    {
        fadeIn(view, duration, skipAnimation);
    }

    @BindingAdapter({"fadeInSubtitleAnimationDuration", "shouldSkipAnimation"})
    public static void fadeInSubtitle(@NonNull View view, long duration, boolean skipAnimation)
    {
        fadeIn(view, duration, skipAnimation);
    }

    private static void fadeIn(@NonNull View view, long duration, boolean skipAnimation)
    {
        if (!skipAnimation)
        {
            view.setAlpha(STARTING_ALPHA);
            view.animate().alpha(ENDING_ALPHA).setDuration(duration).setListener(null);
        } else
            view.setAlpha(ENDING_ALPHA);
    }
}