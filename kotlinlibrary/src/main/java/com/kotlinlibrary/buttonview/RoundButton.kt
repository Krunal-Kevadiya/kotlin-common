package com.kotlinlibrary.buttonview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.StateSet
import android.widget.Button
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import com.kotlinlibrary.R
import com.kotlinlibrary.buttonview.animations.*

class RoundButton : AppCompatButton {
    private var progressDrawable: BaseAnimatedDrawable? = null
    private var resultDrawable: CircularRevealAnimatedDrawable? = null
    private var defaultDrawable: CircularRevealAnimatedDrawable? = null
    private var morpSet: AnimatorSet? = null
    private var mListener: RoundButtonAnimationListener? = null
    private var property: ButtonProperty? = null
    private var resultState = ResultState.NONE
    private var animationState = AnimationState.IDLE
    private var animationProgressStyle: AnimationProgressStyle? = AnimationProgressStyle.CIRCLE

    private var animationDurations: Int = 0
    private var animationCornerRadius: Int = 0
    private var animationProgressWidth: Int = 0

    @ColorInt
    private var animationProgressColor: Int = 0
    private var animationProgressPadding: Int = 0
    private var animationAlpha: Boolean = false

    @DrawableRes
    private var animationCustomResource: Int = 0
    @DrawableRes
    private var animationInnerResource: Int = 0
    @DrawableRes
    private var animationInnerResourceColor: Int = 0

    private var resultDrawableColor: Int = 0
    private var resultDrawableResource: Int = 0
    private var resultSuccessColor: Int = 0
    private var resultSuccessResource: Int = 0
    private var resultFailureColor: Int = 0
    private var resultFailureResource: Int = 0
    private var cornerRadius: Int = 0

    private var cornerWidth: Int = 0
    private var cornerColor: Int = 0
    private var cornerColorPressed: Int = 0
    private var cornerColorDisabled = -1
    private var backgroundColors: Int = 0
    private var backgroundColorPressed: Int = 0
    private var backgroundColorDisabled = -1
    private var textColors: Int = 0
    private var textColorPressed: Int = 0
    private var textColorDisabled = -1

    private var widthMeasureSpec: Int = 0
    private var heightMeasureSpec: Int = 0

    val isAnimating: Boolean
        get() = animationState != AnimationState.IDLE

    val isAnimationDone: Boolean
        get() = animationState == AnimationState.DONE

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.RoundButton, defStyleAttr, 0)

        cornerRadius = a.getDimensionPixelSize(R.styleable.RoundButton_rb_corner_radius, 0)
        cornerWidth = a.getDimensionPixelSize(R.styleable.RoundButton_rb_corner_width, 0)

        cornerColor = a.getColor(R.styleable.RoundButton_rb_corner_color, Color.TRANSPARENT)
        cornerColorDisabled = a.getColor(R.styleable.RoundButton_rb_corner_color_disabled, cornerColorDisabled)
        cornerColorPressed = if (a.hasValue(R.styleable.RoundButton_rb_corner_color_pressed)) {
            a.getColor(R.styleable.RoundButton_rb_corner_color_pressed, cornerColor)
        } else {
            RoundButtonHelper.manipulateColor(cornerColor, .8f)
        }

        backgroundColors = a.getColor(R.styleable.RoundButton_rb_background_color, Color.TRANSPARENT)
        backgroundColorDisabled =
                a.getColor(R.styleable.RoundButton_rb_background_color_disabled, backgroundColorDisabled)
        backgroundColorPressed = if (a.hasValue(R.styleable.RoundButton_rb_background_color_pressed)) {
            a.getColor(R.styleable.RoundButton_rb_background_color_pressed, backgroundColors)
        } else {
            RoundButtonHelper.manipulateColor(backgroundColors, .8f)
        }

        textColors = a.getColor(R.styleable.RoundButton_rb_text_color, Color.TRANSPARENT)
        textColorDisabled = a.getColor(R.styleable.RoundButton_rb_text_color_disabled, textColorDisabled)
        textColorPressed = if (a.hasValue(R.styleable.RoundButton_rb_text_color_pressed)) {
            a.getColor(R.styleable.RoundButton_rb_text_color_pressed, textColors)
        } else {
            RoundButtonHelper.manipulateColor(textColors, .8f)
        }

        animationDurations = a.getInt(R.styleable.RoundButton_rb_animation_duration, 300)
        animationCornerRadius = a.getDimensionPixelSize(R.styleable.RoundButton_rb_animation_corner_radius, 0)
        animationAlpha = a.getBoolean(R.styleable.RoundButton_rb_animation_alpha, false)
        animationProgressWidth = a.getDimensionPixelSize(R.styleable.RoundButton_rb_animation_progress_width, 20)
        animationProgressColor = a.getColor(R.styleable.RoundButton_rb_animation_progress_color, backgroundColors)
        animationProgressPadding = a.getDimensionPixelSize(R.styleable.RoundButton_rb_animation_progress_padding, 10)
        if (a.hasValue(R.styleable.RoundButton_rb_animation_progress_style)) {
            animationProgressStyle =
                    AnimationProgressStyle.values()[a.getInt(R.styleable.RoundButton_rb_animation_progress_style, 0)]
        }
        animationCustomResource = a.getResourceId(R.styleable.RoundButton_rb_animation_custom_resource, 0)
        animationInnerResource = a.getResourceId(R.styleable.RoundButton_rb_animation_inner_resource, 0)
        if (a.hasValue(R.styleable.RoundButton_rb_animation_inner_resource_color)) {
            animationInnerResourceColor =
                    a.getColor(R.styleable.RoundButton_rb_animation_inner_resource_color, Color.BLACK)
        }

        resultDrawableColor = a.getColor(R.styleable.RoundButton_rb_drawable_color, Color.RED)
        resultDrawableResource = a.getResourceId(R.styleable.RoundButton_rb_drawable_resource, 0)
        resultSuccessColor = a.getColor(R.styleable.RoundButton_rb_success_color, Color.GREEN)
        resultSuccessResource = a.getResourceId(R.styleable.RoundButton_rb_success_resource, 0)
        resultFailureColor = a.getColor(R.styleable.RoundButton_rb_failure_color, Color.RED)
        resultFailureResource = a.getResourceId(R.styleable.RoundButton_rb_failure_resource, 0)

        val rbPadding = a.getDimensionPixelSize(R.styleable.RoundButton_rb_padding, 0)
        if (rbPadding >= 0) {
            setPadding(rbPadding, rbPadding, rbPadding, rbPadding)
        } else {
            val rbPaddingTop = a.getDimensionPixelSize(R.styleable.RoundButton_rb_padding_top, 0)
            val rbPaddingLeft = a.getDimensionPixelSize(R.styleable.RoundButton_rb_padding_left, 0)
            val rbPaddingRight = a.getDimensionPixelSize(R.styleable.RoundButton_rb_padding_right, 0)
            val rbPaddingBottom = a.getDimensionPixelSize(R.styleable.RoundButton_rb_padding_bottom, 0)
            setPadding(rbPaddingTop, rbPaddingLeft, rbPaddingRight, rbPaddingBottom)
        }

        a.recycle()
        update()
    }

    private fun update() {
        val background = StateListDrawable()
        background.addState(
            intArrayOf(android.R.attr.state_pressed),
            RoundButtonHelper.createDrawable(
                backgroundColorPressed, cornerColorPressed, cornerWidth, cornerRadius
            )
        )

        if (backgroundColorDisabled != -1) {
            background.addState(
                intArrayOf(-android.R.attr.state_enabled),
                RoundButtonHelper.createDrawable(
                    backgroundColorDisabled, cornerColorDisabled, cornerWidth, cornerRadius
                )
            )
        }

        background.addState(
            StateSet.WILD_CARD,
            RoundButtonHelper.createDrawable(
                backgroundColors, cornerColor, cornerWidth, cornerRadius
            )
        )
        setBackground(background)

        setTextColor(
            if (textColorDisabled != -1)
                ColorStateList(
                    arrayOf(
                        intArrayOf(android.R.attr.state_pressed),
                        intArrayOf(-android.R.attr.state_enabled),
                        intArrayOf()
                    ),
                    intArrayOf(textColorPressed, textColorDisabled, textColors)
                )
            else
                ColorStateList(
                    arrayOf(intArrayOf(android.R.attr.state_pressed), intArrayOf()),
                    intArrayOf(textColorPressed, textColors)
                )
        )
    }

    fun setButtonAnimationListener(listener: RoundButtonAnimationListener) {
        this.mListener = listener
    }

    fun setCustomizations(builder: RoundButtonHelper.Builder) {
        if (builder.cornerRadius != null) {
            cornerRadius = builder.cornerRadius!!
        }

        if (builder.cornerWidth != null) {
            cornerWidth = builder.cornerWidth!!
        }

        if (builder.cornerColor != null) {
            cornerColor = builder.cornerColor!!
        }

        if (builder.cornerColorSelected != null) {
            cornerColorPressed = builder.cornerColorSelected!!
        }

        if (builder.cornerColorDisabled != null) {
            cornerColorDisabled = builder.cornerColorDisabled!!
        }

        if (builder.backgroundColor != null) {
            backgroundColors = builder.backgroundColor!!
        }

        if (builder.backgroundColorSelected != null) {
            backgroundColorPressed = builder.backgroundColorSelected!!
        }

        if (builder.backgroundColorDisabled != null) {
            backgroundColorDisabled = builder.backgroundColorDisabled!!
        }

        if (builder.textColor != null) {
            textColors = builder.textColor!!
        }

        if (builder.textColorSelected != null) {
            textColorPressed = builder.textColorSelected!!
        }

        if (builder.textColorDisabled != null) {
            textColorDisabled = builder.textColorDisabled!!
        }

        if (builder.textColorDisabled != null) {
            textColorDisabled = builder.textColorDisabled!!
        }

        if (builder.animationInnerResource != null) {
            animationInnerResource = builder.animationInnerResource!!
        }

        if (builder.animationCustomResource != null) {
            animationCustomResource = builder.animationCustomResource!!
        }

        if (builder.animationDurations != null) {
            animationDurations = builder.animationDurations!!
        }

        if (builder.animationCornerRadius != null) {
            animationCornerRadius = builder.animationCornerRadius!!
        }

        if (builder.animationProgressWidth != null) {
            animationProgressWidth = builder.animationProgressWidth!!
        }

        if (builder.animationProgressColor != null) {
            animationProgressColor = builder.animationProgressColor!!
        }

        if (builder.animationProgressPadding != null) {
            animationProgressPadding = builder.animationProgressPadding!!
        }

        if (builder.animationAlpha != null) {
            animationAlpha = builder.animationAlpha!!
        }

        if (builder.animationProgressStyle != null) {
            animationProgressStyle = builder.animationProgressStyle
        }

        if (builder.resultDrawableColor != null) {
            resultDrawableColor = builder.resultDrawableColor!!
        }

        if (builder.resultDrawableResource != null) {
            resultDrawableResource = builder.resultDrawableResource!!
        }

        if (builder.resultSuccessColor != null) {
            resultSuccessColor = builder.resultSuccessColor!!
        }

        if (builder.resultSuccessResource != null) {
            resultSuccessResource = builder.resultSuccessResource!!
        }

        if (builder.resultFailureColor != null) {
            resultFailureColor = builder.resultFailureColor!!
        }

        if (builder.resultFailureResource != null) {
            resultFailureResource = builder.resultFailureResource!!
        }

        if (builder.text != null) {
            if (mListener != null) {
                mListener?.onSetText()
            } else {
                if (animationState != AnimationState.IDLE) {
                    property?.text = builder.text
                } else {
                    text = builder.text
                }
            }
        }

        if (builder.width != null) {
            if (animationState != AnimationState.IDLE) {
                property?.adjustWidth = builder.width
            } else {
                val layoutParams = layoutParams
                layoutParams.width = builder.width!!
                setLayoutParams(layoutParams)
            }
        }

        if (builder.height != null) {
            if (animationState != AnimationState.IDLE) {
                property?.adjustHeight = builder.height
            } else {
                val layoutParams = layoutParams
                layoutParams.height = builder.height!!
                setLayoutParams(layoutParams)
            }
        }
        update()
    }

    fun setResultState(resultState: ResultState) {
        this.resultState = resultState
        showResultState()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (animationState == AnimationState.PROGRESS) {
            if (!progressDrawable!!.isRunning) {
                progressDrawable?.start()
            }
            progressDrawable?.draw(canvas)
        } else if (animationState == AnimationState.DONE && resultState != ResultState.NONE) {
            resultDrawable?.draw(canvas)
        } else {
            if (resultDrawableResource != 0) {
                val paint = Paint()
                paint.isAntiAlias = true
                paint.isDither = true
                paint.isFilterBitmap = true
                val bitmap = ResourceUtil.getBitmap(context, resultDrawableResource)

                val centreX = (width - bitmap.width) / 2
                val centreY = (height - bitmap.height) / 2

                canvas.drawBitmap(bitmap, centreX.toFloat(), centreY.toFloat(), paint)
                bitmap.recycle()
            }
        }
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
        if (animationAlpha) {
            toAlpha = 0
        }
        morphTrasformations(property!!.width, property!!.height, property!!.height, property!!.height,
            cornerRadius, animationCornerRadius, fromAlpha, toAlpha, object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (mListener != null) {
                        mListener?.onApplyMorphingEnd()
                    }

                    when (animationProgressStyle) {
                        AnimationProgressStyle.CIRCLE -> {
                            progressDrawable = CircularAnimatedDrawable(
                                this@RoundButton,
                                animationProgressWidth.toFloat(),
                                animationProgressColor,
                                animationInnerResource,
                                animationInnerResourceColor
                            )
                            if (animationInnerResource != 0) {
                                isClickable = true
                            }
                        }
                        AnimationProgressStyle.DOTS -> progressDrawable =
                                DotsAnimatedDrawable(this@RoundButton, animationProgressColor)
                        AnimationProgressStyle.CUSTOM -> progressDrawable = CustomAnimatedDrawable(
                            this@RoundButton,
                            animationCustomResource,
                            animationProgressColor
                        )
                    }

                    val offset = (widthMeasureSpec - heightMeasureSpec) / 2
                    val left = offset + animationProgressPadding
                    val right = widthMeasureSpec - offset - animationProgressPadding
                    val bottom = heightMeasureSpec - animationProgressPadding
                    val top = animationProgressPadding

                    progressDrawable?.setBounds(left, top, right, bottom)
                    progressDrawable?.callback = this@RoundButton
                    progressDrawable?.start()

                    animationState = AnimationState.PROGRESS

                    mListener?.onShowProgress()
                    if (resultState != ResultState.NONE) {
                        postDelayed({ showResultState() }, 50)
                    }
                }
            })
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
        if (animationAlpha) {
            fromAlpha = 0
        }

        morphTrasformations(width, height, property!!.width, property!!.height,
            animationCornerRadius, cornerRadius, fromAlpha, toAlpha, object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (property!!.adjustWidth != null) {
                        val layoutParams = layoutParams
                        layoutParams.width = property?.adjustWidth!!
                        setLayoutParams(layoutParams)
                    }
                    if (property!!.adjustHeight != null) {
                        val layoutParams = layoutParams
                        layoutParams.height = property?.adjustHeight!!
                        setLayoutParams(layoutParams)
                    }
                    if (mListener != null) {
                        mListener?.onSetText()
                    } else {
                        text = property?.text
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        setCompoundDrawablesRelative(
                            property!!.getDrawables()[0], property!!.getDrawables()[1],
                            property!!.getDrawables()[2], property!!.getDrawables()[3]
                        )
                    } else {
                        setCompoundDrawables(
                            property!!.getDrawables()[0], property!!.getDrawables()[1],
                            property!!.getDrawables()[2], property!!.getDrawables()[3]
                        )
                    }
                    isClickable = true
                    animationState = AnimationState.IDLE
                    mListener?.onRevertMorphingEnd()
                }
            })
    }

    private fun showDefaultDrawable() {
        stopAnimation()
        defaultDrawable?.stop()
        defaultDrawable?.dispose()

        val bitmap = if (resultDrawableResource != 0)
            ResourceUtil.getBitmap(context, resultDrawableResource)
        else
            Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        defaultDrawable = CircularRevealAnimatedDrawable(this@RoundButton, resultDrawableColor, bitmap)
        val left = 0
        val right = width
        val bottom = height
        val top = 0

        defaultDrawable?.setBounds(left, top, right, bottom)
        defaultDrawable?.callback = this@RoundButton
        defaultDrawable?.start()
    }

    private fun showResultState() {
        if (animationState != AnimationState.PROGRESS) {
            return
        }

        stopAnimation()
        resultDrawable?.stop()
        resultDrawable?.dispose()

        resultDrawable = if (resultState == ResultState.SUCCESS) {
            val bitmap = if (resultSuccessResource != 0)
                ResourceUtil.getBitmap(context, resultSuccessResource)
            else
                Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            CircularRevealAnimatedDrawable(this@RoundButton, resultSuccessColor, bitmap)
        } else {
            val bitmap = if (resultFailureResource != 0)
                ResourceUtil.getBitmap(context, resultFailureResource)
            else
                Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            CircularRevealAnimatedDrawable(this@RoundButton, resultFailureColor, bitmap)
        }

        val left = 0
        val right = width
        val bottom = height
        val top = 0

        resultDrawable?.setBounds(left, top, right, bottom)
        resultDrawable?.callback = this@RoundButton
        resultDrawable?.start()

        mListener?.onShowResultState()
    }

    private fun stopMorphing() {
        if (morpSet != null && morpSet!!.isRunning) {
            morpSet?.cancel()
        }
    }

    fun stopAnimation() {
        if (animationState == AnimationState.PROGRESS && progressDrawable != null && progressDrawable!!.isRunning) {
            progressDrawable?.stop()
            animationState = AnimationState.DONE
        }
    }

    private fun morphTrasformations(
        fromWidth: Int, fromHeight: Int, toWidth: Int, toHeight: Int,
        fromCorner: Int, toCorner: Int, fromAlpha: Int, toAlpha: Int, listener: Animator.AnimatorListener
    ) {
        setCompoundDrawables(null, null, null, null)
        text = null
        isClickable = false

        val cornerAnimation = ValueAnimator.ofInt(fromCorner, toCorner)
        cornerAnimation.addUpdateListener { animation ->
            if (background.current is GradientDrawable) {
                (background.current as GradientDrawable).cornerRadius = (animation.animatedValue as Int).toFloat()
            }
        }

        val widthAnimation = ValueAnimator.ofInt(fromWidth, toWidth)
        widthAnimation.addUpdateListener { valueAnimator ->
            val valAnim = valueAnimator.animatedValue as Int
            val layoutParams = layoutParams
            layoutParams.width = valAnim
            widthMeasureSpec = valAnim
            setLayoutParams(layoutParams)
        }

        val heightAnimation = ValueAnimator.ofInt(fromHeight, toHeight)
        heightAnimation.addUpdateListener { valueAnimator ->
            val valAnim = valueAnimator.animatedValue as Int
            val layoutParams = layoutParams
            layoutParams.height = valAnim
            heightMeasureSpec = valAnim
            setLayoutParams(layoutParams)
        }

        val alphaAnimator = ValueAnimator.ofInt(fromAlpha, toAlpha)
        alphaAnimator.addUpdateListener { animation -> background.current.alpha = animation.animatedValue as Int }

        morpSet = AnimatorSet()
        morpSet?.duration = animationDurations.toLong()
        morpSet?.playTogether(cornerAnimation, widthAnimation, heightAnimation, alphaAnimator)
        morpSet?.addListener(listener)
        morpSet?.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        progressDrawable?.dispose()
        resultDrawable?.dispose()
    }

    private enum class AnimationState {
        IDLE, MORPHING, PROGRESS, DONE
    }

    enum class ResultState {
        NONE, SUCCESS, FAILURE
    }

    enum class AnimationProgressStyle {
        CIRCLE, DOTS, CUSTOM
    }

    interface RoundButtonAnimationListener {
        fun onRevertMorphingEnd()

        fun onApplyMorphingEnd()

        fun onShowProgress()

        fun onSetText()

        fun onShowResultState()
    }

    private inner class ButtonProperty(button: Button) {
        val height: Int = button.height
        val width: Int = button.width
        var text: String? = null

        private var drawables: Array<Drawable>? = null

        var adjustWidth: Int? = null
        var adjustHeight: Int? = null

        init {
            text = button.text.toString()
            drawables = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                button.compoundDrawablesRelative
            } else {
                button.compoundDrawables
            }
        }

        fun getDrawables(): Array<Drawable> {
            return drawables!!.clone()
        }
    }

    companion object {
        fun newBuilder(): RoundButtonHelper.Builder {
            return RoundButtonHelper.Builder()
        }
    }
}
