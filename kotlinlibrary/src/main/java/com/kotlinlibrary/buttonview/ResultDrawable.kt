package com.kotlinlibrary.buttonview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.graphics.*
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.kotlinlibrary.buttonview.animations.BaseAnimatedDrawable
import com.kotlinlibrary.buttonview.listener.ResultDrawableAnimationListener
import com.kotlinlibrary.buttonview.utils.ProgressProps
import com.kotlinlibrary.buttonview.utils.ResourceUtil
import com.kotlinlibrary.buttonview.utils.ResultState
import kotlin.math.max

class ResultDrawable(
    private val animatedView: View,
    private var readyImage: Bitmap?,
    resultState: ResultState,
    private var progressProps: ProgressProps
) : BaseAnimatedDrawable() {
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mIsFilled: Boolean = false

    private val mShapePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mShapeBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaintImageReady: Paint = Paint()

    private var mRadius: Float = 0f
    private var mImageReadyAlpha: Int = 0
    private var mIsRunning: Boolean = false
    private var mRevealInAnimation: ValueAnimator? = null

    private var mBitMapXOffset: Float = 0f
    private var mBitMapYOffset: Float = 0f
    private var mCenterWidth: Float = 0f
    private var mCenterHeight: Float = 0f

    private var mListener: ResultDrawableAnimationListener? = null

    init {
        mShapePaint.isDither = true
        mShapePaint.isAntiAlias = true
        mShapePaint.style = Paint.Style.FILL
        mShapePaint.color = if(resultState == ResultState.SUCCESS)
                progressProps.pbStateSuccessBackgroundColor
            else
                progressProps.pbStateFailureBackgroundColor

        mShapeBorderPaint.isDither = true
        mShapeBorderPaint.style = Paint.Style.STROKE
        mShapeBorderPaint.color = progressProps.pbStrokeColor
        mShapeBorderPaint.strokeWidth = progressProps.pbStrokeWidth

        mShapeBorderPaint.pathEffect = DashPathEffect(
            floatArrayOf(progressProps.pbStrokeDashWidth, progressProps.pbStrokeDashGap),
            0f
        )

        mPaintImageReady.isAntiAlias = true
        mPaintImageReady.style = Paint.Style.FILL
        mPaintImageReady.color = Color.TRANSPARENT
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)

        val bitMapWidth = ((bounds.right - bounds.left) * 0.6).toInt()
        val bitMapHeight = ((bounds.bottom - bounds.top) * 0.6).toInt()

        mBitMapXOffset = ((bounds.right - bounds.left - bitMapWidth) / 2).toFloat()
        mBitMapYOffset = ((bounds.bottom - bounds.top - bitMapHeight) / 2).toFloat()

        readyImage = Bitmap.createScaledBitmap(readyImage!!, bitMapWidth, bitMapHeight, false)

        mCenterWidth = ((bounds.right + bounds.left) / 2).toFloat()
        mCenterHeight = ((bounds.bottom + bounds.top) / 2).toFloat()
        mHeight = bounds.bottom - bounds.top
        mWidth = bounds.right - bounds.left
    }

    override fun setupAnimations() {
        val alphaAnimator = ValueAnimator.ofInt(0, 255)
        alphaAnimator.duration = 100
        alphaAnimator.addUpdateListener { animation ->
            mImageReadyAlpha = animation.animatedValue as Int
            rebuild()
        }
        alphaAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                mListener?.onResultAnimationEnd()
            }
        })

        val radius = max(mCenterWidth, mCenterHeight)
        mRevealInAnimation = ValueAnimator.ofFloat(radius/2, radius)
        mRevealInAnimation?.interpolator = DecelerateInterpolator()
        mRevealInAnimation?.duration = 120
        mRevealInAnimation?.addUpdateListener { animation ->
            mRadius = animation.animatedValue as Float
            rebuild()
        }
        mRevealInAnimation?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                mIsFilled = true

                alphaAnimator.start()
            }
        })
    }

    private fun rebuild() {
        invalidateSelf()
        animatedView.invalidate()
    }

    fun setResultAnimationListener(listener: ResultDrawableAnimationListener) {
        this.mListener = listener
    }

    override fun start() {
        if (isRunning) {
            return
        }

        setupAnimations()
        mIsRunning = true
        mRevealInAnimation?.start()
    }

    override fun stop() {
        if (!isRunning) {
            return
        }

        mIsRunning = false
        mRevealInAnimation?.cancel()
    }

    override fun isRunning(): Boolean {
        return mIsRunning
    }

    override fun draw(canvas: Canvas) {
        val topLeft = if(progressProps.pbCornerTopLeftRadius > progressProps.animRadius)
            progressProps.pbCornerTopLeftRadius
        else
            progressProps.animRadius
        val topRight = if(progressProps.pbCornerTopRightRadius > progressProps.animRadius)
            progressProps.pbCornerTopRightRadius
        else
            progressProps.animRadius
        val bottomLeft = if(progressProps.pbCornerBottomLeftRadius > progressProps.animRadius)
            progressProps.pbCornerBottomLeftRadius
        else
            progressProps.animRadius
        val bottomRight = if(progressProps.pbCornerBottomRightRadius > progressProps.animRadius)
            progressProps.pbCornerBottomRightRadius
        else
            progressProps.animRadius

        if(progressProps.pbStateWithStroke) {
            val strokeWidth = progressProps.pbStrokeWidth + (progressProps.pbStrokeWidth/2)

            canvas.drawPath(
                ResourceUtil.roundedRect(
                    false, mWidth, mHeight,
                    strokeWidth + mCenterWidth - mRadius,
                    strokeWidth + mCenterHeight - mRadius,
                    mCenterWidth + mRadius - strokeWidth,
                    mCenterHeight + mRadius - strokeWidth,
                    topLeft, topRight, bottomLeft, bottomRight
                ),
                mShapePaint
            )

            canvas.drawPath(
                ResourceUtil.roundedRect(
                    false, mWidth, mHeight,
                    progressProps.pbStrokeWidth + mCenterWidth - mRadius,
                    progressProps.pbStrokeWidth + mCenterHeight - mRadius,
                    mCenterWidth + mRadius - progressProps.pbStrokeWidth,
                    mCenterHeight + mRadius - progressProps.pbStrokeWidth,
                    topLeft, topRight, bottomLeft, bottomRight
                ),
                mShapeBorderPaint
            )
        } else {
            canvas.drawPath(
                ResourceUtil.roundedRect(
                    false, mWidth, mHeight,
                    mCenterWidth - mRadius, mCenterHeight - mRadius,
                    mCenterWidth + mRadius, mCenterHeight + mRadius,
                    topLeft, topRight, bottomLeft, bottomRight
                ),
                mShapePaint
            )
        }

        if (mIsFilled) {
            mPaintImageReady.alpha = mImageReadyAlpha
            canvas.drawBitmap(readyImage!!, mBitMapXOffset, mBitMapYOffset, mPaintImageReady)
        }
    }

    override fun setAlpha(alpha: Int) {}

    override fun setColorFilter(colorFilter: ColorFilter?) {}

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun dispose() {
        if (mRevealInAnimation != null) {
            mRevealInAnimation?.end()
            mRevealInAnimation?.removeAllUpdateListeners()
            mRevealInAnimation?.cancel()
        }

        mRevealInAnimation = null
    }
}
