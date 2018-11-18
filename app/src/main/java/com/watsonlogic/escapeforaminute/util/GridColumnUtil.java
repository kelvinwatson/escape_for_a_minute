package com.watsonlogic.escapeforaminute.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class GridColumnUtil
{
    public static int calculateAutoFitColumns(DisplayMetrics displayMetrics)
    {
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int)(dpWidth / 180);
        return noOfColumns;
    }
}
