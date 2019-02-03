package com.kotlinlibrary.swiperefreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

public class DropBounceInterpolator implements Interpolator {

    @Override
    public float getInterpolation(float v) {
        if (v < 0.25f) {
            return -38.4f * (float) Math.pow(v - 0.125, 2) + 0.6f;
        } else if (v >= 0.5 && v < 0.75) {
            return -19.2f * (float) Math.pow(v - 0.625, 2) + 0.3f;
        } else {
            return 0;
        }
    }
}
