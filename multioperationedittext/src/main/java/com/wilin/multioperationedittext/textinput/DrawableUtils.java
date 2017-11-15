package com.wilin.multioperationedittext.textinput;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Caution. Gross hacks ahead.
 */
@SuppressLint("RestrictedApi")
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

    /**
     * Some drawable implementations have problems with mutation. This method returns false if
     * there is a known issue in the given drawable's implementation.
     */
    public static boolean canSafelyMutateDrawable(@NonNull Drawable drawable) {
        if (Build.VERSION.SDK_INT < 15 && drawable instanceof InsetDrawable) {
            return false;
        }  else if (Build.VERSION.SDK_INT < 15 && drawable instanceof GradientDrawable) {
            // GradientDrawable has a bug pre-ICS which results in mutate() resulting
            // in loss of color
            return false;
        } else if (Build.VERSION.SDK_INT < 17 && drawable instanceof LayerDrawable) {
            return false;
        }

        if (drawable instanceof DrawableContainer) {
            // If we have a DrawableContainer, let's traverse it's child array
            final Drawable.ConstantState state = drawable.getConstantState();
            if (state instanceof DrawableContainer.DrawableContainerState) {
                final DrawableContainer.DrawableContainerState containerState =
                        (DrawableContainer.DrawableContainerState) state;
                for (final Drawable child : containerState.getChildren()) {
                    if (!canSafelyMutateDrawable(child)) {
                        return false;
                    }
                }
            }
        } else if (drawable instanceof android.support.v4.graphics.drawable.DrawableWrapper) {
            return canSafelyMutateDrawable(
                    ((android.support.v4.graphics.drawable.DrawableWrapper) drawable)
                            .getWrappedDrawable());
        } else if (drawable instanceof android.support.v7.graphics.drawable.DrawableWrapper) {
            return canSafelyMutateDrawable(
                    ((android.support.v7.graphics.drawable.DrawableWrapper) drawable)
                            .getWrappedDrawable());
        } else if (drawable instanceof ScaleDrawable) {
            return canSafelyMutateDrawable(((ScaleDrawable) drawable).getDrawable());
        }

        return true;
    }
}
