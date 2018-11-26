package com.kotlinlibrary.buttonview.animations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.graphics.*
import android.view.View
import android.view.animation.DecelerateInterpolator

class CircularRevealAnimatedDrawable(
    private val mAnimatedView: View,
    fillColor: Int,
    private var mReadyImage: Bitmap?
) : BaseAnimatedDrawable() {
    var isFilled: Boolean = false
        private set
    private val mPaint: Paint
    private val mPaintImageReady: Paint
    private var mRadius: Float = 0f
    private var mFinalRadius: Float = 0f
    private var mRevealInAnimation: ValueAnimator? = null
    private var isRunning: Boolean = false
    private var mCenterWidth: Float = 0f
    private var mCenterHeith: Float = 0f
    private var mImageReadyAlpha: Int = 0
    private var bitMapXOffset: Float = 0f
    private var bitMapYOffset: Float = 0f

    init {
        isRunning = false

        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL
        mPaint.color = fillColor

        mPaintImageReady = Paint()
        mPaintImageReady.isAntiAlias = true
        mPaintImageReady.style = Paint.Style.FILL
        mPaintImageReady.color = Color.TRANSPARENT
        mImageReadyAlpha = 0

        mRadius = 0f
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)

        val bitMapWidth = ((bounds.right - bounds.left) * 0.6).toInt()
        val bitMapHeight = ((bounds.bottom - bounds.top) * 0.6).toInt()

        bitMapXOffset = ((bounds.right - bounds.left - bitMapWidth) / 2).toFloat()
        bitMapYOffset = ((bounds.bottom - bounds.top - bitMapHeight) / 2).toFloat()

        mReadyImage = Bitmap.createScaledBitmap(mReadyImage!!, bitMapWidth, bitMapHeight, false)

        mFinalRadius = ((bounds.right - bounds.left) / 2).toFloat()
        mCenterWidth = ((bounds.right + bounds.left) / 2).toFloat()
        mCenterHeith = ((bounds.bottom + bounds.top) / 2).toFloat()
    }

    override fun setupAnimations() {
        val alphaAnimator = ValueAnimator.ofInt(0, 255)
        alphaAnimator.duration = 100
        alphaAnimator.addUpdateListener { animation ->
            mImageReadyAlpha = animation.animatedValue as Int
            invalidateSelf()
            mAnimatedView.invalidate()
        }

        mRevealInAnimation = ValueAnimator.ofFloat(0f, mFinalRadius)
        mRevealInAnimation?.interpolator = DecelerateInterpolator()
        mRevealInAnimation?.duration = 120
        mRevealInAnimation?.addUpdateListener { animation ->
            mRadius = animation.animatedValue as Float
            invalidateSelf()
            mAnimatedView.invalidate()
        }
        mRevealInAnimation?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                isFilled = true

                alphaAnimator.start()
            }
        })
    }

    override fun start() {
        if (isRunning()) {
            return
        }

        setupAnimations()
        isRunning = true
        mRevealInAnimation?.start()
    }

    override fun stop() {
        if (!isRunning()) {
            return
        }

        isRunning = false
        mRevealInAnimation?.cancel()
    }

    override fun isRunning(): Boolean {
        return isRunning
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(mCenterWidth, mCenterHeith, mRadius, mPaint)

        if (isFilled) {
            mPaintImageReady.alpha = mImageReadyAlpha
            canvas.drawBitmap(mReadyImage!!, bitMapXOffset, bitMapYOffset, mPaintImageReady)
        }
    }

    override fun setAlpha(alpha: Int) {}

    override fun setColorFilter(colorFilter: ColorFilter?) {}

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun dispose() {
        mRevealInAnimation?.end()
        mRevealInAnimation?.removeAllUpdateListeners()
        mRevealInAnimation?.cancel()
        mRevealInAnimation = null
    }
}
