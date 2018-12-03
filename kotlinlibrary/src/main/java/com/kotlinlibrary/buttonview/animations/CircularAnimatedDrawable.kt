package com.kotlinlibrary.buttonview.animations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.graphics.*
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.kotlinlibrary.buttonview.RoundButtonHelper

class CircularAnimatedDrawable @JvmOverloads constructor(
    private val mAnimatedView: View, private val mBorderWidth: Float, arcColor: Int,
    @DrawableRes innerResource: Int = 0, @ColorInt innerResourceColorFilter: Int = Color.BLACK
) : BaseAnimatedDrawable() {
    private val fBounds = RectF()
    private var mValueAnimatorAngle: ValueAnimator? = null
    private var mValueAnimatorSweep: ValueAnimator? = null
    private val mAnimatorSet: AnimatorSet?
    private val mPaint: Paint
    private var mCurrentGlobalAngle: Float = 0f
    private var mCurrentSweepAngle: Float = 0f
    private var mCurrentGlobalAngleOffset: Float = 0f

    private var mModeAppearing: Boolean = false
    private var mRunning: Boolean = false

    private var shouldDraw: Boolean = false

    private val innerBounds = RectF()
    private var innerPaint: Paint? = null
    private var innerImage: Bitmap? = null

    init {

        if (innerResource != 0) {
            setInnerResource(innerResource)
            setInnerResourceColorFilter(innerResourceColorFilter)
        }

        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mBorderWidth
        mPaint.color = arcColor

        setupAnimations()

        shouldDraw = true
        mAnimatorSet = AnimatorSet()
    }

    fun setInnerResource(@DrawableRes innerResource: Int) {
        innerImage = RoundButtonHelper.getBitmap(ContextCompat.getDrawable(mAnimatedView.context, innerResource)!!)
    }

    fun setInnerResourceColorFilter(@ColorInt resourceColorFilter: Int) {
        innerPaint = Paint()
        innerPaint?.isAntiAlias = true
        innerPaint?.colorFilter = PorterDuffColorFilter(resourceColorFilter, PorterDuff.Mode.SRC_IN)
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        fBounds.left = bounds.left.toFloat() + mBorderWidth / 2f + .5f
        fBounds.right = bounds.right.toFloat() - mBorderWidth / 2f - .5f
        fBounds.top = bounds.top.toFloat() + mBorderWidth / 2f + .5f
        fBounds.bottom = bounds.bottom.toFloat() - mBorderWidth / 2f - .5f

        innerBounds.left = fBounds.left + 2.5f
        innerBounds.right = fBounds.right - 2.5f
        innerBounds.top = fBounds.top + 2.5f
        innerBounds.bottom = fBounds.bottom - 2.5f

        if (innerImage != null) {
            val bitMapWidth = (innerBounds.right - innerBounds.left).toInt()
            val bitMapHeight = (innerBounds.bottom - innerBounds.top).toInt()
            innerImage = Bitmap.createScaledBitmap(innerImage!!, bitMapWidth, bitMapHeight, false)
        }
    }

    override fun start() {
        if (isRunning) {
            return
        }

        mRunning = true

        mAnimatorSet?.playTogether(mValueAnimatorAngle, mValueAnimatorSweep)
        mAnimatorSet?.start()
    }

    override fun stop() {
        if (!isRunning) {
            return
        }

        mRunning = false
        mAnimatorSet?.cancel()
    }

    override fun isRunning(): Boolean {
        return mRunning
    }

    override fun draw(canvas: Canvas) {
        var startAngle = mCurrentGlobalAngle - mCurrentGlobalAngleOffset
        var sweepAngle = mCurrentSweepAngle

        if (!mModeAppearing) {
            startAngle += sweepAngle
            sweepAngle = 360f - sweepAngle - MIN_SWEEP_ANGLE
        } else {
            sweepAngle += MIN_SWEEP_ANGLE
        }

        canvas.drawArc(fBounds, startAngle, sweepAngle, false, mPaint)

        if (innerImage != null) {
            canvas.drawBitmap(innerImage!!, null, innerBounds, innerPaint)
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
        mValueAnimatorAngle = ValueAnimator.ofFloat(0f, 360f)
        mValueAnimatorAngle?.interpolator = ANGLE_INTERPOLATOR
        mValueAnimatorAngle?.duration = ANGLE_ANIMATOR_DURATION.toLong()
        mValueAnimatorAngle?.repeatCount = ValueAnimator.INFINITE
        mValueAnimatorAngle?.addUpdateListener { animation -> mCurrentGlobalAngle = animation.animatedValue as Float }

        mValueAnimatorSweep = ValueAnimator.ofFloat(0f, 360f - 2 * MIN_SWEEP_ANGLE)
        mValueAnimatorSweep?.interpolator = SWEEP_INTERPOLATOR
        mValueAnimatorSweep?.duration = SWEEP_ANIMATOR_DURATION.toLong()
        mValueAnimatorSweep?.repeatCount = ValueAnimator.INFINITE
        mValueAnimatorSweep?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationRepeat(animation: Animator) {
                toggleAppearingMode()
                shouldDraw = false
            }
        })

        mValueAnimatorSweep?.addUpdateListener { animation ->
            mCurrentSweepAngle = animation.animatedValue as Float

            if (mCurrentSweepAngle < 5) {
                shouldDraw = true
            }

            if (shouldDraw) {
                mAnimatedView.invalidate()
            }
        }
    }

    private fun toggleAppearingMode() {
        mModeAppearing = !mModeAppearing

        if (mModeAppearing) {
            mCurrentGlobalAngleOffset = (mCurrentGlobalAngleOffset + MIN_SWEEP_ANGLE * 2) % 360
        }
    }

    override fun dispose() {
        mValueAnimatorAngle?.end()
        mValueAnimatorAngle?.removeAllUpdateListeners()
        mValueAnimatorAngle?.cancel()

        mValueAnimatorAngle = null
        mValueAnimatorSweep?.end()
        mValueAnimatorSweep?.removeAllUpdateListeners()
        mValueAnimatorSweep?.cancel()

        mValueAnimatorSweep = null
        if (mAnimatorSet != null) {
            mAnimatorSet.end()
            mAnimatorSet.cancel()
        }
    }

    companion object {

        private val ANGLE_INTERPOLATOR = LinearInterpolator()
        private val SWEEP_INTERPOLATOR = AccelerateDecelerateInterpolator()
        private const val ANGLE_ANIMATOR_DURATION = 2000
        private const val SWEEP_ANIMATOR_DURATION = 700
        private const val MIN_SWEEP_ANGLE = 50f
    }
}
