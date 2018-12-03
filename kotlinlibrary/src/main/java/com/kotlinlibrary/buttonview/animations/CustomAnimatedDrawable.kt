package com.kotlinlibrary.buttonview.animations

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.DrawableCompat
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

class CustomAnimatedDrawable(
    private val view: View, @param:DrawableRes @field:DrawableRes
    private val resource: Int, @param:ColorInt @field:ColorInt
    private val color: Int
) : BaseAnimatedDrawable() {
    private var mBounds = Rect()

    private var mRunning: Boolean = false

    private var animateDrawable: AnimatedVectorDrawableCompat? = null
    private val callback = object : Animatable2Compat.AnimationCallback() {
        override fun onAnimationEnd(drawable: Drawable?) {
            animateDrawable?.start()
            view.invalidate()
        }
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        mBounds = bounds
        setupAnimations()
    }

    override fun start() {
        if (isRunning) {
            return
        }

        mRunning = true

        animateDrawable?.registerAnimationCallback(callback)
        animateDrawable?.start()
    }

    override fun stop() {
        if (!isRunning) {
            return
        }

        mRunning = false

        animateDrawable?.unregisterAnimationCallback(callback)
        animateDrawable?.stop()
    }

    override fun isRunning(): Boolean {
        return mRunning
    }

    override fun draw(canvas: Canvas) {
        animateDrawable?.draw(canvas)
    }

    override fun setAlpha(alpha: Int) {}

    override fun setColorFilter(colorFilter: ColorFilter?) {}

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    override fun setupAnimations() {
        animateDrawable = AnimatedVectorDrawableCompat.create(view.context, resource)
        if (animateDrawable != null) {
            DrawableCompat.setTint(animateDrawable!!, color)
            animateDrawable?.bounds = mBounds
        }
    }

    override fun dispose() {
        animateDrawable = null
    }
}
