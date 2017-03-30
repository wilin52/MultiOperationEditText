package com.wilin.multioperationedittext.textinput;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Caution. Gross hacks ahead.
 */
public class DrawableUtils {

    private static final String LOG_TAG = "DrawableUtils";

    private static Method sSetConstantStateMethod;
    private static boolean sSetConstantStateMethodFetched;

    private DrawableUtils() {}

    public static boolean setContainerConstantState(DrawableContainer drawable,
                                             Drawable.ConstantState constantState) {
        // We can use getDeclaredMethod() on v9+
        return setContainerConstantStateV9(drawable, constantState);
    }

    private static boolean setContainerConstantStateV9(DrawableContainer drawable,
                                                       Drawable.ConstantState constantState) {
        if (!sSetConstantStateMethodFetched) {
            try {
                sSetConstantStateMethod = DrawableContainer.class.getDeclaredMethod(
                        "setConstantState", DrawableContainer.DrawableContainerState.class);
                sSetConstantStateMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.e(LOG_TAG, "Could not fetch setConstantState(). Oh well.");
            }
            sSetConstantStateMethodFetched = true;
        }
        if (sSetConstantStateMethod != null) {
            try {
                sSetConstantStateMethod.invoke(drawable, constantState);
                return true;
            } catch (Exception e) {
                Log.e(LOG_TAG, "Could not invoke setConstantState(). Oh well.");
            }
        }
        return false;
    }
}
