package com.wilin.multioperationedittext.textinput;

import android.content.Context;
import android.content.res.TypedArray;

import com.wilin.multioperationedittext.R;

/**
 * copy from android.support.design
 */
public class ThemeUtils {

    private static final int[] APPCOMPAT_CHECK_ATTRS = {
            R.attr.colorPrimary
    };

    public static void checkAppCompatTheme(Context context) {
        TypedArray a = context.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
        final boolean failed = !a.hasValue(0);
        if (a != null) {
            a.recycle();
        }
        if (failed) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme "
                    + "(or descendant) with the design library.");
        }
    }
}
