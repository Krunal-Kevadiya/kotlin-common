package com.kotlinlibrary.skeletonlayout

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ComposeShader
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.Shader
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import com.kotlinlibrary.R
import com.kotlinlibrary.utils.ktx.logs

class ShimmerLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    FrameLayout(context, attrs, defStyle) {
    private var maskOffsetX: Int = 0
    private var maskRect: Rect? = null
    private var gradientTexturePaint: Paint? = null
    private var maskAnimator: ValueAnimator? = null

    private var localMaskBitmap: Bitmap? = null
    private var maskBitmap: Bitmap? = null
    private var canvasForShimmerMask: Canvas? = null

    private var isAnimationReversed: Boolean = false
    private var isAnimationStarted: Boolean = false
    private var autoStart: Boolean = false
    private var shimmerAnimationDuration: Int = 0
    private var shimmerColor: Int = 0
    private var shimmerAngle: Int = 0
    private var maskWidth: Float = 0.toFloat()
    private var gradientCenterColorWidth: Float = 0.toFloat()

    private var startAnimationPreDrawListener: ViewTreeObserver.OnPreDrawListener? = null

    private val shimmerAnimation: Animator
        get() {
            if (maskAnimator != null) {
                return maskAnimator as ValueAnimator
            }

            if (maskRect == null) {
                maskRect = calculateBitmapMaskRect()
            }

            val animationToX = width
            val animationFromX: Int

            animationFromX = if (width > maskRect!!.width()) {
                -animationToX
            } else {
                -maskRect!!.width()
            }

            val shimmerBitmapWidth = maskRect!!.width()
            val shimmerAnimationFullLength = animationToX - animationFromX

            maskAnimator = if (isAnimationReversed)
                ValueAnimator.ofInt(shimmerAnimationFullLength, 0)
            else
                ValueAnimator.ofInt(0, shimmerAnimationFullLength)
            maskAnimator?.duration = shimmerAnimationDuration.toLong()
            maskAnimator?.repeatCount = ObjectAnimator.INFINITE

            maskAnimator?.addUpdateListener { animation ->
                maskOffsetX = animationFromX + animation.animatedValue as Int

                if (maskOffsetX + shimmerBitmapWidth >= 0) {
                    invalidate()
                }
            }

            return maskAnimator as ValueAnimator
        }

    private val gradientColorDistribution: FloatArray
        get() {
            val colorDistribution = FloatArray(4)

            colorDistribution[0] = 0f
            colorDistribution[3] = 1f

            colorDistribution[1] = 0.5f - gradientCenterColorWidth / 2f
            colorDistribution[2] = 0.5f + gradientCenterColorWidth / 2f

            return colorDistribution
        }

    init {
        setWillNotDraw(false)

        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ShimmerLayout,
            0, 0
        )

        try {
            shimmerAngle = a.getInteger(R.styleable.ShimmerLayout_shimmer_angle, DEFAULT_ANGLE.toInt())
            shimmerAnimationDuration =
                a.getInteger(R.styleable.ShimmerLayout_shimmer_animation_duration, DEFAULT_ANIMATION_DURATION)
            shimmerColor = a.getColor(R.styleable.ShimmerLayout_shimmer_color, getColor(R.color.shimmer_color))
            autoStart = a.getBoolean(R.styleable.ShimmerLayout_shimmer_auto_start, false)
            maskWidth = a.getFloat(R.styleable.ShimmerLayout_shimmer_mask_width, 0.5f)
            gradientCenterColorWidth = a.getFloat(R.styleable.ShimmerLayout_shimmer_gradient_center_color_width, 0.1f)
            isAnimationReversed = a.getBoolean(R.styleable.ShimmerLayout_shimmer_reverse_animation, false)
        } finally {
            a.recycle()
        }

        setMaskWidth(maskWidth)
        setGradientCenterColorWidth(gradientCenterColorWidth)
        setShimmerAngle(shimmerAngle)

        enableForcedSoftwareLayerIfNeeded()

        if (autoStart && visibility == View.VISIBLE) {
            startShimmerAnimation()
        }
    }

    override fun onDetachedFromWindow() {
        resetShimmering()
        super.onDetachedFromWindow()
    }

    override fun dispatchDraw(canvas: Canvas) {
        if (!isAnimationStarted || width <= 0 || height <= 0) {
            super.dispatchDraw(canvas)
        } else {
            dispatchDrawShimmer(canvas)
        }
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == View.VISIBLE) {
            if (autoStart) {
                startShimmerAnimation()
            }
        } else {
            stopShimmerAnimation()
        }
    }

    fun startShimmerAnimation() {
        if (isAnimationStarted) {
            return
        }

        if (width == 0) {
            startAnimationPreDrawListener = object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    viewTreeObserver.removeOnPreDrawListener(this)
                    startShimmerAnimation()

                    return true
                }
            }

            viewTreeObserver.addOnPreDrawListener(startAnimationPreDrawListener)
            return
        }

        val animator = shimmerAnimation
        animator.start()
        isAnimationStarted = true
    }

    fun stopShimmerAnimation() {
        if (startAnimationPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(startAnimationPreDrawListener)
        }

        resetShimmering()
    }

    fun setShimmerColor(shimmerColor: Int) {
        this.shimmerColor = shimmerColor
        resetIfStarted()
    }

    fun setShimmerAnimationDuration(durationMillis: Int) {
        this.shimmerAnimationDuration = durationMillis
        resetIfStarted()
    }

    fun setAnimationReversed(animationReversed: Boolean) {
        this.isAnimationReversed = animationReversed
        resetIfStarted()
    }

    /**
     * Set the angle of the shimmer effect in clockwise direction in degrees.
     * The angle must be between {@value #MIN_ANGLE_VALUE} and {@value #MAX_ANGLE_VALUE}.
     *
     * @param angle The angle to be set
     */
    fun setShimmerAngle(angle: Int) {
        if (angle < MIN_ANGLE_VALUE || MAX_ANGLE_VALUE < angle) {
            throw IllegalArgumentException(
                String.format(
                    "shimmerAngle value must be between %d and %d",
                    MIN_ANGLE_VALUE,
                    MAX_ANGLE_VALUE
                )
            )
        }
        this.shimmerAngle = angle
        resetIfStarted()
    }

    /**
     * Sets the width of the shimmer line to a value higher than 0 to less or equal to 1.
     * 1 means the width of the shimmer line is equal to half of the width of the ShimmerLayout.
     * The default value is 0.5.
     *
     * @param maskWidth The width of the shimmer line.
     */
    fun setMaskWidth(maskWidth: Float) {
        if (maskWidth <= MIN_MASK_WIDTH_VALUE || MAX_MASK_WIDTH_VALUE < maskWidth) {
            throw IllegalArgumentException(
                String.format(
                    "maskWidth value must be higher than %d and less or equal to %d",
                    MIN_MASK_WIDTH_VALUE, MAX_MASK_WIDTH_VALUE
                )
            )
        }

        this.maskWidth = maskWidth
        resetIfStarted()
    }

    /**
     * Sets the width of the center gradient color to a value higher than 0 to less than 1.
     * 0.99 means that the whole shimmer line will have this color with a little transparent edges.
     * The default value is 0.1.
     *
     * @param gradientCenterColorWidth The width of the center gradient color.
     */
    fun setGradientCenterColorWidth(gradientCenterColorWidth: Float) {
        if (gradientCenterColorWidth <= MIN_GRADIENT_CENTER_COLOR_WIDTH_VALUE || MAX_GRADIENT_CENTER_COLOR_WIDTH_VALUE <= gradientCenterColorWidth) {
            throw IllegalArgumentException(
                String.format(
                    "gradientCenterColorWidth value must be higher than %d and less than %d",
                    MIN_GRADIENT_CENTER_COLOR_WIDTH_VALUE, MAX_GRADIENT_CENTER_COLOR_WIDTH_VALUE
                )
            )
        }

        this.gradientCenterColorWidth = gradientCenterColorWidth
        resetIfStarted()
    }

    private fun resetIfStarted() {
        if (isAnimationStarted) {
            resetShimmering()
            startShimmerAnimation()
        }
    }

    private fun dispatchDrawShimmer(canvas: Canvas) {
        super.dispatchDraw(canvas)

        localMaskBitmap = getMaskBitmap()
        if (localMaskBitmap == null) {
            return
        }

        if (canvasForShimmerMask == null) {
            canvasForShimmerMask = Canvas(localMaskBitmap!!)
        }

        canvasForShimmerMask?.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)

        canvasForShimmerMask?.save()
        canvasForShimmerMask?.translate((-maskOffsetX).toFloat(), 0f)

        super.dispatchDraw(canvasForShimmerMask)

        canvasForShimmerMask?.restore()
        drawShimmer(canvas)
        localMaskBitmap = null
    }

    private fun drawShimmer(destinationCanvas: Canvas) {
        createShimmerPaint()
        destinationCanvas.save()

        destinationCanvas.translate(maskOffsetX.toFloat(), 0f)
        destinationCanvas.drawRect(
            maskRect!!.left.toFloat(),
            0f,
            maskRect!!.width().toFloat(),
            maskRect!!.height().toFloat(),
            gradientTexturePaint!!
        )

        destinationCanvas.restore()
    }

    private fun resetShimmering() {
        if (maskAnimator != null) {
            maskAnimator?.end()
            maskAnimator?.removeAllUpdateListeners()
        }

        maskAnimator = null
        gradientTexturePaint = null
        isAnimationStarted = false

        releaseBitMaps()
    }

    private fun releaseBitMaps() {
        canvasForShimmerMask = null

        if (maskBitmap != null) {
            maskBitmap?.recycle()
            maskBitmap = null
        }
    }

    private fun getMaskBitmap(): Bitmap {
        if (maskBitmap == null) {
            maskBitmap = createBitmap(maskRect!!.width(), height)
        }

        return maskBitmap!!
    }

    private fun createShimmerPaint() {
        if (gradientTexturePaint != null) {
            return
        }

        val edgeColor = reduceColorAlphaValueToZero(shimmerColor)
        val shimmerLineWidth = width / 2 * maskWidth
        val yPosition = (if (0 <= shimmerAngle) height else 0).toFloat()

        val gradient = LinearGradient(
            0f, yPosition,
            Math.cos(Math.toRadians(shimmerAngle.toDouble())).toFloat() * shimmerLineWidth,
            yPosition + Math.sin(Math.toRadians(shimmerAngle.toDouble())).toFloat() * shimmerLineWidth,
            intArrayOf(edgeColor, shimmerColor, shimmerColor, edgeColor),
            gradientColorDistribution,
            Shader.TileMode.CLAMP
        )

        val maskBitmapShader = BitmapShader(localMaskBitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        val composeShader = ComposeShader(gradient, maskBitmapShader, PorterDuff.Mode.DST_IN)

        gradientTexturePaint = Paint()
        gradientTexturePaint?.isAntiAlias = true
        gradientTexturePaint?.isDither = true
        gradientTexturePaint?.isFilterBitmap = true
        gradientTexturePaint?.shader = composeShader
    }

    private fun createBitmap(width: Int, height: Int): Bitmap? {
        return try {
            Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8)
        } catch (e: OutOfMemoryError) {
            logs(e)
            System.gc()
            null
        }
    }

    private fun getColor(id: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getColor(id)
        } else {
            resources.getColor(id)
        }
    }

    private fun reduceColorAlphaValueToZero(actualColor: Int): Int {
        return Color.argb(0, Color.red(actualColor), Color.green(actualColor), Color.blue(actualColor))
    }

    private fun calculateBitmapMaskRect(): Rect {
        return Rect(0, 0, calculateMaskWidth(), height)
    }

    private fun calculateMaskWidth(): Int {
        val shimmerLineBottomWidth = width / 2 * maskWidth / Math.cos(Math.toRadians(Math.abs(shimmerAngle).toDouble()))
        val shimmerLineRemainingTopWidth = height * Math.tan(Math.toRadians(Math.abs(shimmerAngle).toDouble()))

        return (shimmerLineBottomWidth + shimmerLineRemainingTopWidth).toInt()
    }

    /**
     * in ShimmerLayout is used ComposeShader, which contains bug in android 4.1.1 with layer hardware acceleration
     * @see [StackOverflow](https://stackoverflow.com/questions/12445583/issue-with-composeshader-on-android-4-1-1)
     */
    private fun enableForcedSoftwareLayerIfNeeded() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }
    }

    companion object {
        private const val DEFAULT_ANIMATION_DURATION = 1500

        private const val DEFAULT_ANGLE: Byte = 20

        private const val MIN_ANGLE_VALUE: Byte = -45
        private const val MAX_ANGLE_VALUE: Byte = 45
        private const val MIN_MASK_WIDTH_VALUE: Byte = 0
        private const val MAX_MASK_WIDTH_VALUE: Byte = 1

        private const val MIN_GRADIENT_CENTER_COLOR_WIDTH_VALUE: Byte = 0
        private const val MAX_GRADIENT_CENTER_COLOR_WIDTH_VALUE: Byte = 1
    }
}