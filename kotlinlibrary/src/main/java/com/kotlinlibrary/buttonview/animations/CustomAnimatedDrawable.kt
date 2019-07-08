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

    private var mAnimateDrawable: AnimatedVectorDrawableCompat? = null
    private val mCallback = object : Animatable2Compat.AnimationCallback() {
        override fun onAnimationEnd(drawable: Drawable?) {
            mAnimateDrawable?.start()
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

        mAnimateDrawable?.registerAnimationCallback(mCallback)
        mAnimateDrawable?.start()
    }

    override fun stop() {
        if (!isRunning) {
            return
        }

        mRunning = false
        mAnimateDrawable?.unregisterAnimationCallback(mCallback)
        mAnimateDrawable?.stop()
    }

    override fun isRunning(): Boolean {
        return mRunning
    }

    override fun draw(canvas: Canvas) {
        mAnimateDrawable?.draw(canvas)
    }

    override fun setAlpha(alpha: Int) {
        throw NotImplementedError()
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        throw NotImplementedError()
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    override fun setupAnimations() {
        mAnimateDrawable = AnimatedVectorDrawableCompat.create(view.context, resource)
        mAnimateDrawable?.run {
            DrawableCompat.setTint(this, color)
            mAnimateDrawable?.bounds = mBounds
        }
    }

    override fun dispose() {
        mAnimateDrawable = null
    }
}
