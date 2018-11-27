package com.kotlinlibrary.utils

import android.os.Build
import androidx.annotation.IntDef

@IntDef(
    Build.VERSION_CODES.BASE,
    Build.VERSION_CODES.BASE_1_1,
    Build.VERSION_CODES.CUPCAKE,
    Build.VERSION_CODES.DONUT,
    Build.VERSION_CODES.ECLAIR,
    Build.VERSION_CODES.ECLAIR_0_1,
    Build.VERSION_CODES.ECLAIR_MR1,
    Build.VERSION_CODES.FROYO,
    Build.VERSION_CODES.GINGERBREAD,
    Build.VERSION_CODES.GINGERBREAD_MR1,
    Build.VERSION_CODES.HONEYCOMB,
    Build.VERSION_CODES.HONEYCOMB_MR1,
    Build.VERSION_CODES.HONEYCOMB_MR2,
    Build.VERSION_CODES.ICE_CREAM_SANDWICH,
    Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1,
    Build.VERSION_CODES.JELLY_BEAN,
    Build.VERSION_CODES.JELLY_BEAN_MR1,
    Build.VERSION_CODES.JELLY_BEAN_MR2,
    Build.VERSION_CODES.KITKAT,
    Build.VERSION_CODES.KITKAT_WATCH,
    Build.VERSION_CODES.LOLLIPOP,
    Build.VERSION_CODES.LOLLIPOP_MR1,
    Build.VERSION_CODES.M,
    Build.VERSION_CODES.N,
    Build.VERSION_CODES.N_MR1,
    Build.VERSION_CODES.O,
    Build.VERSION_CODES.O_MR1,
    Build.VERSION_CODES.P
)
annotation class SystemApi

inline fun doWithApi(@SystemApi sdkCode: Int, block: () -> Unit) {
    if (Build.VERSION.SDK_INT == sdkCode) {
        block()
    }
}

inline fun doWithAtLeastApi(@SystemApi sdkCode: Int, block: () -> Unit) {
    if (Build.VERSION.SDK_INT >= sdkCode) {
        block()
    }
}

inline fun doWithAtMostApi(@SystemApi sdkCode: Int, block: () -> Unit) {
    if (Build.VERSION.SDK_INT <= sdkCode) {
        block()
    }
}

inline fun doWithHigherApi(@SystemApi sdkCode: Int, block: () -> Unit) {
    if (Build.VERSION.SDK_INT > sdkCode) {
        block()
    }
}

inline fun doWithLowerApi(@SystemApi sdkCode: Int, block: () -> Unit) {
    if (Build.VERSION.SDK_INT < sdkCode) {
        block()
    }
}