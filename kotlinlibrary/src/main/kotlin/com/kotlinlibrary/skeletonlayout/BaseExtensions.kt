package com.kotlinlibrary.skeletonlayout

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.kotlinlibrary.utils.windowManager

internal fun Any.tag(): String = javaClass.simpleName

internal fun Context.refreshRateInSeconds(): Float = windowManager?.defaultDisplay?.refreshRate ?: 60f

internal fun ViewGroup.views(): List<View> = (0 until childCount).map { getChildAt(it) }

internal fun View.isAttachedToWindowCompat() = ViewCompat.isAttachedToWindow(this)

fun dpToPx(dp: String): Float {
    return (dp.toFloat() * Resources.getSystem().displayMetrics.density)
}