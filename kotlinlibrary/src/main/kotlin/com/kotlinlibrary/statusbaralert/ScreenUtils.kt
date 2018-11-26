package com.kotlinlibrary.statusbaralert

import android.app.Activity
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView

private var titleBarHeigh = 0
private val dipsMap: MutableMap<Float, Int> = mutableMapOf()
internal fun Activity.convertDpToPixel(dp: Float): Int {
    if (dipsMap.containsKey(dp)) {
        return dipsMap[dp]!!
    }
    val resources = this.resources
    val metrics = resources.displayMetrics
    val value = (dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    dipsMap[dp] = value

    return value
}

internal fun Activity.getStatusBarHeight(): Int {
    if (titleBarHeigh > 0) {
        return titleBarHeigh
    }
    val resourceId = this.resources.getIdentifier("status_bar_height", "dimen", "android")
    titleBarHeigh = if (resourceId > 0) {
        this.resources.getDimensionPixelSize(resourceId)
    } else {
        convertDpToPixel(25f)
    }

    return titleBarHeigh
}

internal fun Activity.isTranslucentStatusBar(): Boolean {
    val w = this.window
    val lp = w.attributes
    val flags = lp.flags
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS == WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
    } else {
        return false
    }
}

internal fun TextView.startProgressAnimation(duration: Long) {
    let {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = duration
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        it.startAnimation(anim)
        it.visibility = View.VISIBLE
    }
}

internal fun TextView.stopProgressAnimation() {
    let {
        it.visibility = View.GONE
        it.clearAnimation()
        it.alpha = 1.0f
    }
}
