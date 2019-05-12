package com.kotlinlibrary.utils.ktx

import android.os.Build
import androidx.annotation.IntRange

inline fun toApi(
    @IntRange(from=Build.VERSION_CODES.BASE.toLong(), to=Build.VERSION_CODES.P.toLong()) toVersion: Int,
    inclusive: Boolean = false,
    action: () -> Unit
) {
    if (Build.VERSION.SDK_INT < toVersion || (inclusive && Build.VERSION.SDK_INT == toVersion)) action()
}

inline fun fromApi(
    @IntRange(from=Build.VERSION_CODES.BASE.toLong(), to=Build.VERSION_CODES.P.toLong()) fromVersion: Int,
    inclusive: Boolean = false,
    action: () -> Unit
) {
    if (Build.VERSION.SDK_INT > fromVersion || (inclusive && Build.VERSION.SDK_INT == fromVersion)) action()
}

inline fun doApi(
    @IntRange(from=Build.VERSION_CODES.BASE.toLong(), to=Build.VERSION_CODES.P.toLong()) version: Int,
    action: () -> Unit) {
    if (Build.VERSION.SDK_INT == version) action()
}

