package com.wilin.multioperationedittext.textinput;

import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * copy from android.support.design
 */
public class AnimationUtils {

    public static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();
    public static final Interpolator FAST_OUT_LINEAR_IN_INTERPOLATOR = new FastOutLinearInInterpolator();
    public static final Interpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();
    public static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();

    /**
     * Linear interpolation between {@code startValue} and {@code endValue} by {@code fraction}.
     */
    static float lerp(float startValue, float endValue, float fraction) {
        return startValue + (fraction * (endValue - startValue));
    }

    static int lerp(int startValue, int endValue, float fraction) {
        return startValue + Math.round(fraction * (endValue - startValue));
    }

    static class AnimationListenerAdapter implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

}