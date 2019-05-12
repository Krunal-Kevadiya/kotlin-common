package com.kotlinlibrary.utils.ktx

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.view.View
import androidx.core.view.ViewCompat

fun View.visible() = run { visibility = View.VISIBLE }

fun View.invisible() = run { visibility = View.INVISIBLE }

fun View.gone() = run { visibility = View.GONE }

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) = if (value) visible() else gone()

var View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) = if (value) invisible() else visible()

var View.isGone: Boolean
    get() = visibility == View.GONE
    set(value) = if (value) gone() else visible()

/**
 * Toggle's view's visibility. If View is visible, then sets to gone. Else sets Visible
 * Previously knows as toggle()
 */
inline fun View.toggleVisibility() {
    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

/**
 * Hides all the views passed in the arguments
 */
fun hideViews(vararg views: View) = views.forEach { it.visibility = View.GONE }

/**
 * Shows all the views passed in the arguments
 */
fun showViews(vararg views: View) = views.forEach { it.visibility = View.VISIBLE }

var View.backgroundTintStateList: ColorStateList
    get() = ViewCompat.getBackgroundTintList(this)
    set(value) = ViewCompat.setBackgroundTintList(this, value)

var View.axisZ: Float
    get() = ViewCompat.getZ(this)
    set(value) = ViewCompat.setZ(this, value)

var View.translationAxisZ: Float
    get() = ViewCompat.getTranslationZ(this)
    set(value) = ViewCompat.setTranslationZ(this, value)

fun View.toBitmap(): Bitmap {
    this.isDrawingCacheEnabled = true
    this.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
    this.layout(0, 0, this.measuredWidth, this.measuredHeight)
    this.buildDrawingCache(true)

    return this.drawingCache
}
