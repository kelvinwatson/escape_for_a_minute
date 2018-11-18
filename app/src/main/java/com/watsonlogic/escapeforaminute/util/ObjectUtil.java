package com.watsonlogic.escapeforaminute.util;

import android.support.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ObjectUtil
{
    public static boolean isEmpty(@Nullable Object obj)
    {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence)obj).length() == 0;

        if (obj.getClass().isArray())
            return Array.getLength(obj) == 0;

        if (obj instanceof Collection)
            return ((Collection)obj).isEmpty();

        if (obj instanceof Map)
            return ((Map)obj).isEmpty();

        return false;
    }

}
