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
import com.kotlinlibrary.buttonview.utils.ResourceUtil

class CircularAnimatedDrawable @JvmOverloads constructor(
    private val animatedView: View,
    private val borderWidth: Float, arcColor: Int,
    @DrawableRes innerResource: Int = 0,
    @ColorInt innerResourceColorFilter: Int = Color.TRANSPARENT,
    private val drawablePadding: Int
) : BaseAnimatedDrawable() {
    private val mFBounds = RectF()
    private var mValueAnimatorAngle: ValueAnimator? = null
    private var mValueAnimatorSweep: ValueAnimator? = null
    private val mAnimatorSet: AnimatorSet?

    private val mPaint: Paint
    private var mCurrentGlobalAngle: Float = 0f
    private var mCurrentSweepAngle: Float = 0f
    private var mCurrentGlobalAngleOffset: Float = 0f

    private var mRunning: Boolean = false
    private var mShouldDraw: Boolean = false
    private var mModeAppearing: Boolean = false

    private val mInnerBounds = RectF()
    private var mInnerPaint: Paint? = null
    private var mInnerImage: Bitmap? = null

    init {
        if (innerResource != 0) {
            setInnerResource(innerResource)
            setInnerResourceColorFilter(innerResourceColorFilter)
        }

        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = borderWidth
        mPaint.color = arcColor

        setupAnimations()
        mShouldDraw = true
        mAnimatorSet = AnimatorSet()
    }

    private fun setInnerResource(@DrawableRes innerResource: Int) {
        ContextCompat.getDrawable(animatedView.context, innerResource)?.run {
            mInnerImage = ResourceUtil.getBitmap(this)
        }
    }

    private fun setInnerResourceColorFilter(@ColorInt resourceColorFilter: Int) {
        mInnerPaint = Paint()
        mInnerPaint?.isAntiAlias = true
        if(resourceColorFilter != Color.TRANSPARENT) {
            mInnerPaint?.colorFilter = PorterDuffColorFilter(resourceColorFilter, PorterDuff.Mode.SRC_IN)
        }
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        mFBounds.left = bounds.left.toFloat() + borderWidth / 2f + .5f
        mFBounds.right = bounds.right.toFloat() - borderWidth / 2f - .5f
        mFBounds.top = bounds.top.toFloat() + borderWidth / 2f + .5f
        mFBounds.bottom = bounds.bottom.toFloat() - borderWidth / 2f - .5f

        mInnerBounds.left = mFBounds.left + 2.5f + drawablePadding
        mInnerBounds.right = mFBounds.right - 2.5f - drawablePadding
        mInnerBounds.top = mFBounds.top + 2.5f + drawablePadding
        mInnerBounds.bottom = mFBounds.bottom - 2.5f - drawablePadding

        mInnerImage?.let {
            val bitMapWidth = (mInnerBounds.right - mInnerBounds.left).toInt()
            val bitMapHeight = (mInnerBounds.bottom - mInnerBounds.top).toInt()
            mInnerImage = Bitmap.createScaledBitmap(it, bitMapWidth, bitMapHeight, false)
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

        canvas.drawArc(mFBounds, startAngle, sweepAngle, false, mPaint)
        mInnerImage?.run {
            canvas.drawBitmap(this, null, mInnerBounds, mInnerPaint)
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
                mShouldDraw = false
            }
        })

        mValueAnimatorSweep?.addUpdateListener { animation ->
            mCurrentSweepAngle = animation.animatedValue as Float

            if (mCurrentSweepAngle < 5) {
                mShouldDraw = true
            }

            if (mShouldDraw) {
                animatedView.invalidate()
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
