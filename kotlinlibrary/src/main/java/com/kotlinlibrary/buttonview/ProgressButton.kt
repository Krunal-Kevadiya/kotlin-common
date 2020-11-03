package com.kotlinlibrary.buttonview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.JELLY_BEAN_MR1
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.kotlinlibrary.R
import com.kotlinlibrary.buttonview.animations.*
import com.kotlinlibrary.buttonview.listener.ProgressButtonAnimationListener
import com.kotlinlibrary.buttonview.listener.ResultDrawableAnimationListener
import com.kotlinlibrary.buttonview.utils.*
import com.kotlinlibrary.buttonview.utils.ButtonProperty
import kotlin.math.min

class ProgressButton : AppCompatButton {
    private var mHeight: Int = 0
    private var mWidth: Int = 0
    private var mAnimHeight: Int = 0
    private var mAnimWidth: Int = 0
    private var progressProps: ProgressProps =
        ProgressProps()

    private val paintImage = Paint()
    private var filterImage: ColorFilter? = null

    private var animationState = AnimationState.IDLE
    private var resultState = ResultState.NONE

    private var morphingSet: AnimatorSet? = null
    private lateinit var property: ButtonProperty

    private var progressDrawable: BaseAnimatedDrawable? = null
    private var resultDrawable: ResultDrawable? = null

    private var mListener: ProgressButtonAnimationListener? = null

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        //Measure Width
        mWidth = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize //Must be this size
            MeasureSpec.AT_MOST -> min(desiredWidth, widthSize) //Can't be bigger than...
            MeasureSpec.UNSPECIFIED -> measuredWidth
            else -> desiredWidth //Be whatever you want
        }

        //Measure Height
        mHeight = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize //Must be this size
            MeasureSpec.AT_MOST -> min(desiredHeight, heightSize) //Can't be bigger than...
            MeasureSpec.UNSPECIFIED -> measuredHeight
            else -> desiredHeight //Be whatever you want
        }

        //MUST CALL THIS
        setMeasuredDimension(mWidth, mHeight)
        update()
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressButton, defStyleAttr, 0)
        with(progressProps) {
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_gradient_name)) {
                typedArray.getInt(R.styleable.ProgressButton_pb_gradient_name, -1).let {
                    if (it != -1) {
                        pbGradientName = DefaultGradientName.values()[it]
                    }
                }
            }
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_gradient_type)) {
                typedArray.getInt(R.styleable.ProgressButton_pb_gradient_type, -1).let {
                    if (it != -1) {
                        pbGradientType = GradientType.values()[it]
                    }
                }
            }
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_radial_radius)) {
                pbRadialRadius =
                    typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_radial_radius, 0).toFloat()
            }

            if (typedArray.hasValue(R.styleable.ProgressButton_pb_gradient_angle)) {
                pbGradientAngle = typedArray.getFloat(R.styleable.ProgressButton_pb_gradient_angle, 0f).toDouble()
            }
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_gradient_colors)) {
                typedArray.getResourceId(R.styleable.ProgressButton_pb_gradient_colors, 0).let {
                    if (it != 0) {
                        pbGradientColor = context.resources.getIntArray(it)
                    }
                }
            }
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_gradient_positions)) {
                typedArray.getResourceId(R.styleable.ProgressButton_pb_gradient_positions, -1).let { positions ->
                    if (positions != -1) {
                        pbGradientPositions = context.resources.getStringArray(positions).map { it.toFloat() }.toFloatArray()
                    }
                }
            }
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_normal_color)) {
                pbNormalColor = typedArray.getColor(R.styleable.ProgressButton_pb_normal_color, Color.TRANSPARENT)
            }

            typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_corner_radius, 0).toFloat().let {
                pbCornerTopLeftRadius = it
                pbCornerTopRightRadius = it
                pbCornerBottomLeftRadius = it
                pbCornerBottomRightRadius = it
            }
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_corner_topLeftRadius)) {
                pbCornerTopLeftRadius =
                    typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_corner_topLeftRadius, 0).toFloat()
            }
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_corner_topRightRadius)) {
                pbCornerTopRightRadius =
                    typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_corner_topRightRadius, 0).toFloat()
            }
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_corner_bottomLeftRadius)) {
                pbCornerBottomLeftRadius =
                    typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_corner_bottomLeftRadius, 0).toFloat()
            }
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_corner_bottomRightRadius)) {
                pbCornerBottomRightRadius =
                    typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_corner_bottomRightRadius, 0)
                        .toFloat()
            }

            if (typedArray.hasValue(R.styleable.ProgressButton_pb_state_success_backgroundColor)) {
                pbStateSuccessBackgroundColor =
                    typedArray.getColor(R.styleable.ProgressButton_pb_state_success_backgroundColor, Color.TRANSPARENT)
            }
            pbStateSuccessDrawable = typedArray.getResourceId(R.styleable.ProgressButton_pb_state_success_drawable, 0)
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_state_success_drawableTint)) {
                pbStateSuccessDrawableTint =
                    typedArray.getColor(R.styleable.ProgressButton_pb_state_success_drawableTint, Color.TRANSPARENT)
            }
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_state_failure_backgroundColor)) {
                pbStateFailureBackgroundColor =
                    typedArray.getColor(R.styleable.ProgressButton_pb_state_failure_backgroundColor, Color.TRANSPARENT)
            }
            pbStateFailureDrawable = typedArray.getResourceId(R.styleable.ProgressButton_pb_state_failure_drawable, 0)
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_state_failure_drawableTint)) {
                pbStateFailureDrawableTint =
                    typedArray.getColor(R.styleable.ProgressButton_pb_state_failure_drawableTint, Color.TRANSPARENT)
            }
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_state_with_stroke)) {
                pbStateWithStroke = typedArray.getBoolean(R.styleable.ProgressButton_pb_state_with_stroke, false)
            }
            pbStateRevertDelay = typedArray.getInt(R.styleable.ProgressButton_pb_state_revert_delay, 0).toLong()

            pbImageDrawable = typedArray.getResourceId(R.styleable.ProgressButton_pb_image_drawable, 0)
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_image_drawableTint)) {
                pbImageDrawableTint =
                    typedArray.getColor(R.styleable.ProgressButton_pb_image_drawableTint, Color.TRANSPARENT)
            }
            pbDrawablePadding = typedArray.getDimensionPixelSize(R.styleable.ProgressButton_android_drawablePadding, 25)

            pbAnimDuration = typedArray.getInt(R.styleable.ProgressButton_pb_anim_duration, 300)
            pbAnimCornerRadius =
                typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_anim_corner_radius, 0).toFloat()
            pbAnimAlphaMorphing = typedArray.getBoolean(R.styleable.ProgressButton_pb_anim_alpha_morphing, false)

            pbProgressStyle = ProgressStyle.values()[typedArray.getInt(R.styleable.ProgressButton_pb_progress_style, 0)]
            pbProgressColor = typedArray.getColor(R.styleable.ProgressButton_pb_progress_color, Color.WHITE)
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_progress_dots_count)) {
                pbProgressDotsCount = typedArray.getInt(R.styleable.ProgressButton_pb_progress_dots_count, 0)
            }
            pbProgressPadding =
                typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_progress_padding, 15).toFloat()
            pbProgressWidth =
                typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_progress_width, 5).toFloat()
            pbProgressDrawable = typedArray.getResourceId(R.styleable.ProgressButton_pb_progress_drawable, 0)
            pbProgressInnerDrawable = typedArray.getResourceId(R.styleable.ProgressButton_pb_progress_inner_drawable, 0)
            if (typedArray.hasValue(R.styleable.ProgressButton_pb_progress_inner_drawableTint)) {
                pbProgressInnerDrawableTint =
                    typedArray.getColor(R.styleable.ProgressButton_pb_progress_inner_drawableTint, Color.TRANSPARENT)
            }

            pbStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_stroke_width, 0).toFloat()
            pbStrokeDashWidth =
                typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_stroke_dash_width, 0).toFloat()
            pbStrokeDashGap =
                typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_stroke_dash_gap, 0).toFloat()
            pbStrokeColor = typedArray.getColor(R.styleable.ProgressButton_pb_stroke_color, Color.TRANSPARENT)
        }
        typedArray.recycle()

        filterImage = PorterDuffColorFilter(progressProps.pbImageDrawableTint, PorterDuff.Mode.SRC_IN)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (animationState == AnimationState.PROGRESS) {
            if (progressDrawable?.isRunning == false)
                progressDrawable?.start()
            progressDrawable?.draw(canvas)
        } else if (animationState == AnimationState.DONE && resultState != ResultState.NONE) {
            resultDrawable?.draw(canvas)
        } else if (progressProps.pbImageDrawable != 0) {
            paintImage.isAntiAlias = true
            paintImage.isDither = true
            paintImage.isFilterBitmap = true
            val bitmap = ResourceUtil.getScaleToFitBitmap(
                mHeight, mWidth, context, progressProps.pbImageDrawable, progressProps.pbDrawablePadding
            )
            if (progressProps.pbImageDrawableTint != Color.TRANSPARENT) {
                paintImage.colorFilter = filterImage
            }

            val centreX = (mWidth - bitmap.width) / 2
            val centreY = (mHeight - bitmap.height) / 2

            canvas.drawBitmap(bitmap, centreX.toFloat(), centreY.toFloat(), paintImage)
            bitmap.recycle()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        progressDrawable?.dispose()
        resultDrawable?.dispose()
    }

    private fun update() {
        background = ProgressBackgroundDrawable(progressProps, mWidth, mHeight)
    }

    fun startAnimation() {
        if (animationState != AnimationState.IDLE) {
            return
        }

        stopAnimation()
        stopMorphing()

        property = ButtonProperty(this)
        resultState = ResultState.NONE
        animationState = AnimationState.MORPHING

        val fromAlpha = 255
        var toAlpha = 255
        if (progressProps.pbAnimAlphaMorphing) {
            toAlpha = 0
        }

        val fromCorner = min(
            progressProps.pbCornerTopLeftRadius,
            min(
                progressProps.pbCornerTopRightRadius,
                min(progressProps.pbCornerBottomLeftRadius, progressProps.pbCornerBottomRightRadius)
            )
        )
        morphingTrasformations(property.width, property.height, property.height, property.height,
            fromCorner, progressProps.pbAnimCornerRadius,
            fromAlpha, toAlpha, object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mListener?.onApplyMorphingEnd()

                    when (progressProps.pbProgressStyle) {
                        ProgressStyle.CIRCLE -> {
                            progressDrawable = CircularAnimatedDrawable(
                                this@ProgressButton,
                                progressProps.pbProgressWidth,
                                progressProps.pbProgressColor,
                                progressProps.pbProgressInnerDrawable,
                                progressProps.pbProgressInnerDrawableTint,
                                progressProps.pbDrawablePadding
                            )
                            if (progressProps.pbProgressInnerDrawable != 0) {
                                isClickable = true
                            }
                        }
                        ProgressStyle.DOTS -> progressDrawable =
                            DotsAnimatedDrawable(
                                this@ProgressButton,
                                progressProps.pbProgressColor,
                                progressProps.pbProgressDotsCount ?: DotsAnimatedDrawable.DEFAULT_DOTS_COUNT
                            )
                        ProgressStyle.CUSTOM -> progressDrawable =
                            CustomAnimatedDrawable(
                                this@ProgressButton,
                                progressProps.pbProgressDrawable,
                                progressProps.pbProgressColor
                            )
                        else -> {
                        }
                    }

                    val mOffset: Int = (mAnimWidth - mAnimHeight) / 2
                    val mLeft = (mOffset + progressProps.pbProgressPadding).toInt()
                    val mRight = (mAnimWidth - mOffset - progressProps.pbProgressPadding).toInt()
                    val mBottom = (mAnimHeight - progressProps.pbProgressPadding).toInt()
                    val mTop = (progressProps.pbProgressPadding).toInt()

                    progressDrawable?.setBounds(mLeft, mTop, mRight, mBottom)
                    progressDrawable?.callback = this@ProgressButton
                    progressDrawable?.start()

                    animationState = AnimationState.PROGRESS

                    mListener?.onShowProgress()
                    if (resultState != ResultState.NONE) {
                        postDelayed({
                            showResultState()
                        }, 50)
                    }
                }
            })
    }

    fun stopAnimation() {
        if (animationState == AnimationState.PROGRESS && progressDrawable?.isRunning == true) {
            progressDrawable?.stop()
            animationState = AnimationState.DONE
        }
    }

    fun revertAnimation() {
        if (animationState == AnimationState.IDLE) {
            return
        }

        stopAnimation()
        stopMorphing()

        resultState = ResultState.NONE
        animationState = AnimationState.MORPHING

        var fromAlpha = 255
        val toAlpha = 255
        if (progressProps.pbAnimAlphaMorphing) {
            fromAlpha = 0
        }

        val fromCorner = min(
            progressProps.pbCornerTopLeftRadius,
            min(
                progressProps.pbCornerTopRightRadius,
                min(progressProps.pbCornerBottomLeftRadius, progressProps.pbCornerBottomRightRadius)
            )
        )
        morphingTrasformations(mWidth, mHeight, property.width, property.height,
            progressProps.pbAnimCornerRadius, fromCorner,
            fromAlpha, toAlpha, object : AnimatorListenerAdapter() {
                @SuppressLint("ObsoleteSdkInt")
                override fun onAnimationEnd(animation: Animator) {
                    if (property.adjustWidth != null) {
                        val layoutParams = layoutParams
                        layoutParams.width = property.adjustWidth!!
                        setLayoutParams(layoutParams)
                    }
                    if (property.adjustHeight != null) {
                        val layoutParams = layoutParams
                        layoutParams.height = property.adjustHeight!!
                        setLayoutParams(layoutParams)
                    }
                    text = property.text
                    if (SDK_INT >= JELLY_BEAN_MR1) {
                        setCompoundDrawablesRelative(
                            property.getDrawables()[0],
                            property.getDrawables()[1],
                            property.getDrawables()[2],
                            property.getDrawables()[3]
                        )
                    } else {
                        setCompoundDrawables(
                            property.getDrawables()[0],
                            property.getDrawables()[1],
                            property.getDrawables()[2],
                            property.getDrawables()[3]
                        )
                    }
                    isClickable = true
                    animationState = AnimationState.IDLE
                    mListener?.onRevertMorphingEnd()
                }
            })
    }

    private fun showResultState() {
        if (animationState !== AnimationState.PROGRESS) {
            return
        }

        stopAnimation()
        resultDrawable?.stop()
        resultDrawable?.dispose()

        val bitmap = if (resultState == ResultState.SUCCESS) {
            val bitmap = ResourceUtil.getScaleToFitBitmap(
                mHeight, mWidth, context, progressProps.pbStateSuccessDrawable, progressProps.pbDrawablePadding
            )
            ResourceUtil.tintImage(bitmap, progressProps.pbStateSuccessDrawableTint)
        } else {
            val bitmap = ResourceUtil.getScaleToFitBitmap(
                mHeight, mWidth, context, progressProps.pbStateFailureDrawable, progressProps.pbDrawablePadding
            )
            ResourceUtil.tintImage(bitmap, progressProps.pbStateFailureDrawableTint)
        }
        resultDrawable =
            ResultDrawable(this@ProgressButton, bitmap, resultState, progressProps)

        val left = 0
        val right = mWidth
        val bottom = mHeight
        val top = 0

        resultDrawable?.setBounds(left, top, right, bottom)
        resultDrawable?.callback = this@ProgressButton
        resultDrawable?.start()
        resultDrawable?.setResultAnimationListener(object : ResultDrawableAnimationListener {
            override fun onResultAnimationEnd() {
                if(progressProps.pbStateRevertDelay > 0 ) {
                    postDelayed({
                        revertAnimation()
                    }, progressProps.pbStateRevertDelay)
                }
            }
        })
        mListener?.onShowResultState()
    }

    private fun stopMorphing() {
        if (morphingSet?.isRunning == true) {
            morphingSet?.cancel()
        }
    }

    fun setButtonAnimationListener(listener: ProgressButtonAnimationListener) {
        this.mListener = listener
    }

    fun setCustomizations(func: ProgressProps.() -> Unit = {}) {
        func.invoke(progressProps)
        update()
    }

    fun setResultState(resultState: ResultState) {
        this.resultState = resultState
        showResultState()
    }

    private fun morphingTrasformations(
        fromWidth: Int, fromHeight: Int, toWidth: Int, toHeight: Int,
        fromCorner: Float, toCorner: Float,
        fromAlpha: Int, toAlpha: Int, listener: Animator.AnimatorListener
    ) {
        setCompoundDrawables(null, null, null, null)
        isClickable = false
        text = null

        val cornerAnimation = ValueAnimator.ofFloat(fromCorner, toCorner)
        cornerAnimation.addUpdateListener { animation ->
            if(background is ProgressBackgroundDrawable) {
                (background as ProgressBackgroundDrawable).progressProps.animRadius = animation.animatedValue.toString().toFloat()
                (background as ProgressBackgroundDrawable).rebuild()
            }
        }

        val widthAnimation = ValueAnimator.ofInt(fromWidth, toWidth)
        widthAnimation.addUpdateListener { valueAnimator ->
            val valAnim = valueAnimator.animatedValue as Int
            val layoutParams = layoutParams
            layoutParams.width = valAnim
            mAnimWidth = valAnim
            setLayoutParams(layoutParams)
        }

        val heightAnimation = ValueAnimator.ofInt(fromHeight, toHeight)
        heightAnimation.addUpdateListener { valueAnimator ->
            val valAnim = valueAnimator.animatedValue as Int
            val layoutParams = layoutParams
            layoutParams.height = valAnim
            mAnimHeight = valAnim
            setLayoutParams(layoutParams)
        }

        val alphaAnimator = ValueAnimator.ofInt(fromAlpha, toAlpha)
        alphaAnimator.addUpdateListener { animation -> background.current.alpha = animation.animatedValue as Int }

        morphingSet = AnimatorSet()
        morphingSet?.duration = progressProps.pbAnimDuration.toLong()
        morphingSet?.playTogether(cornerAnimation, widthAnimation, heightAnimation, alphaAnimator)
        morphingSet?.addListener(listener)
        morphingSet?.start()
    }
}