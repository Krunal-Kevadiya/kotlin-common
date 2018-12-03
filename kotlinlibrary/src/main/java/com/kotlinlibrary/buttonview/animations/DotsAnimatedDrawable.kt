package com.kotlinlibrary.buttonview.animations

import android.animation.ValueAnimator
import android.graphics.*
import android.view.View

import java.util.ArrayList

class DotsAnimatedDrawable @JvmOverloads constructor(
    private val mAnimatedView: View,
    color: Int,
    count: Int = DEFAULT_DOTS_COUNT,
    private val mLoopDuration: Int = DEFAULT_LOOP_DURATION,
    private val mJumpDuration: Int = DEFAULT_JUMP_DURATION
) : BaseAnimatedDrawable() {
    private val fBounds = RectF()
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
        fBounds.left = bounds.left.toFloat()
        fBounds.right = bounds.right.toFloat()
        fBounds.top = bounds.top.toFloat()
        fBounds.bottom = bounds.bottom.toFloat()

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
        val startOffset = (mLoopDuration - mJumpDuration) / (count - 1)

        mJumpHalfTime = mJumpDuration / 2
        val dotSize = fBounds.width() / count

        for (i in 0 until count) {
            val dot = mDots[i]
            dot.x = fBounds.left + dotSize / 2 + i * dotSize
            dot.y = fBounds.bottom - dotSize / 2
            dot.radius = dotSize / 2 - dotSize / 8
            dot.paint = Paint(mPaint)

            val startTime = startOffset * i
            dot.startTime = startTime
            dot.jumpUpEndTime = startTime + mJumpHalfTime
            dot.jumpDownEndTime = startTime + mJumpDuration
        }

        mJumpHeight = (fBounds.height() - dotSize / 2).toInt()

        mValueAnimator = ValueAnimator.ofInt(0, mLoopDuration)
        mValueAnimator?.duration = mLoopDuration.toLong()
        mValueAnimator?.repeatCount = ValueAnimator.INFINITE
        mValueAnimator?.addUpdateListener { animation ->
            val animationValue = animation.animatedValue as Int

            for (i in mDots.indices) {
                val dot = mDots[i]
                val dotStartTime = dot.startTime

                var animationFactor = 0f
                if (animationValue >= dotStartTime) {
                    if (animationValue < dot.jumpUpEndTime) {
                        animationFactor = (animationValue - dotStartTime).toFloat() / mJumpHalfTime
                    } else if (animationValue < dot.jumpDownEndTime) {
                        animationFactor = 1 - (animationValue - dotStartTime - mJumpHalfTime).toFloat() / mJumpHalfTime
                    }
                }

                val translationY = mJumpHeight * animationFactor
                dot.y = fBounds.bottom - dot.radius - translationY
                mAnimatedView.invalidate()
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
        internal var x: Float = 0f
        internal var y: Float = 0f
        internal var paint: Paint? = null
        internal var radius: Float = 0f
        internal var startTime: Int = 0
        internal var jumpUpEndTime: Int = 0
        internal var jumpDownEndTime: Int = 0

        internal fun draw(canvas: Canvas) {
            canvas.drawCircle(x, y, radius, paint!!)
        }
    }

    companion object {
        private const val DEFAULT_DOTS_COUNT = 3
        private const val DEFAULT_LOOP_DURATION = 600
        private const val DEFAULT_JUMP_DURATION = 400
    }
}
