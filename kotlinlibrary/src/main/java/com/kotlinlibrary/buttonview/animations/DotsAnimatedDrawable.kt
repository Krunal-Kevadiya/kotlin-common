package com.kotlinlibrary.buttonview.animations

import android.animation.ValueAnimator
import android.graphics.*
import android.view.View

import java.util.ArrayList

class DotsAnimatedDrawable @JvmOverloads constructor(
    private val animatedView: View,
    color: Int,
    count: Int = DEFAULT_DOTS_COUNT,
    private val loopDuration: Int = DEFAULT_LOOP_DURATION,
    private val jumpDuration: Int = DEFAULT_JUMP_DURATION
) : BaseAnimatedDrawable() {
    private val mFBounds = RectF()
    private var mValueAnimator: ValueAnimator? = null
    private val mPaint: Paint = Paint()

    private var mRunning: Boolean = false
    private var mJumpHeight: Int = 0

    private var mJumpHalfTime: Int = 0
    private val mDots: MutableList<Dot>

    init {
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL
        mPaint.color = color

        mDots = ArrayList(count)
        for (i in 0 until count) {
            mDots.add(Dot())
        }
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        val centerY = (bounds.bottom.toFloat() - bounds.top.toFloat()) / 4
        mFBounds.left = bounds.left.toFloat()
        mFBounds.right = bounds.right.toFloat()
        mFBounds.top = bounds.top.toFloat()
        mFBounds.bottom = bounds.bottom.toFloat() - centerY

        setupAnimations()
    }

    override fun start() {
        if (isRunning) {
            return
        }

        mRunning = true
        mValueAnimator?.start()
    }

    override fun stop() {
        if (!isRunning) {
            return
        }

        mRunning = false
        mValueAnimator?.end()
    }

    override fun isRunning(): Boolean {
        return mRunning
    }

    override fun draw(canvas: Canvas) {
        for (dot in mDots) {
            dot.draw(canvas)
        }
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    override fun setupAnimations() {
        val count = mDots.size
        val startOffset = (loopDuration - jumpDuration) / (count - 1)

        mJumpHalfTime = jumpDuration / 2
        val dotSize = mFBounds.width() / count

        for (i in 0 until count) {
            val dot = mDots[i]
            dot.mX = mFBounds.left + dotSize / 2 + i * dotSize
            dot.mY = mFBounds.bottom - dotSize / 2
            dot.mRadius = dotSize / 2 - dotSize / 8
            dot.mPaint = Paint(mPaint)

            val startTime = startOffset * i
            dot.mStartTime = startTime
            dot.mJumpUpEndTime = startTime + mJumpHalfTime
            dot.mJumpDownEndTime = startTime + jumpDuration
        }

        mJumpHeight = (mFBounds.height() - dotSize / 2).toInt()

        mValueAnimator = ValueAnimator.ofInt(0, loopDuration)
        mValueAnimator?.duration = loopDuration.toLong()
        mValueAnimator?.repeatCount = ValueAnimator.INFINITE
        mValueAnimator?.addUpdateListener { animation ->
            val animationValue = animation.animatedValue as Int

            for (i in mDots.indices) {
                val dot = mDots[i]
                val dotStartTime = dot.mStartTime

                var animationFactor = 0f
                if (animationValue >= dotStartTime) {
                    if (animationValue < dot.mJumpUpEndTime) {
                        animationFactor = (animationValue - dotStartTime).toFloat() / mJumpHalfTime
                    } else if (animationValue < dot.mJumpDownEndTime) {
                        animationFactor = 1 - (animationValue - dotStartTime - mJumpHalfTime).toFloat() / mJumpHalfTime
                    }
                }

                val translationY = mJumpHeight * animationFactor
                dot.mY = mFBounds.bottom - dot.mRadius - translationY
                animatedView.invalidate()
            }
        }
    }

    override fun dispose() {
        mValueAnimator?.end()
        mValueAnimator?.removeAllUpdateListeners()
        mValueAnimator?.cancel()
        mValueAnimator = null
    }

    private inner class Dot {
        internal var mX: Float = 0f
        internal var mY: Float = 0f
        internal var mPaint: Paint? = null
        internal var mRadius: Float = 0f
        internal var mStartTime: Int = 0
        internal var mJumpUpEndTime: Int = 0
        internal var mJumpDownEndTime: Int = 0

        internal fun draw(canvas: Canvas) {
            canvas.drawCircle(mX, mY, mRadius, mPaint!!)
        }
    }

    companion object {
        const val DEFAULT_DOTS_COUNT = 3
        private const val DEFAULT_LOOP_DURATION = 600
        private const val DEFAULT_JUMP_DURATION = 400
    }
}
