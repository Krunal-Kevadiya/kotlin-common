package com.kotlinlibrary.swiperefreshlayout;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

class DisplayUtil {

    static boolean isOver600dp(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels / displayMetrics.density >= 600;
    }
}
