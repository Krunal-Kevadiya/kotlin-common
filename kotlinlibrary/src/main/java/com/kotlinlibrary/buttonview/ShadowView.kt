package com.kotlinlibrary.buttonview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.kotlinlibrary.R

open class ShadowView : ViewGroup {
    companion object {
        private const val DEFAULT_CHILD_GRAVITY = Gravity.TOP or Gravity.START
        private const val SIZE_UNSET = -1
        private const val SIZE_DEFAULT = 0
    }
    private var foregroundDraw: Drawable? = null
    private val selfBounds = Rect()
    private val overlayBounds = Rect()
    private var foregroundDrawGravity = Gravity.FILL
    private var foregroundDrawInPadding = true
    private var foregroundDrawBoundsChanged = false

    private val bgPaint = Paint()
    private var shadowColor: Int = 0
        set(value) {
            field = value
            updatePaintShadow(shadowRadius, shadowDx, shadowDy, value)
        }
    private var foregroundColor: Int = 0
        set(value) {
            field = value
            updateForegroundColor()
        }
    /*private var backgroundClr: Int = 0
        set(value) {
            field = value
            invalidate()
        }*/

    private var shadowRadius = 0f
        set(value) {
            var v = value
            if (v > getShadowMarginMax() && getShadowMarginMax() != 0f) {
                v = getShadowMarginMax()
            }
            field = value
            updatePaintShadow(v, shadowDx, shadowDy, shadowColor)
        }
        get() {
            return if (field > getShadowMarginMax() && getShadowMarginMax() != 0f) {
                getShadowMarginMax()
            } else {
                field
            }
        }
    private var shadowDx = 0f
        set(value) {
            field = value
            updatePaintShadow(shadowRadius, value, shadowDy, shadowColor)
        }
    private var shadowDy = 0f
        set(value) {
            field = value
            updatePaintShadow(shadowRadius, shadowDx, value, shadowColor)
        }
    private var cornerRadius: Float = 0f
    private var shadowMarginTop: Int = 0
        set(value) {
            field = value
            updatePaintShadow()
        }
    private var shadowMarginLeft: Int = 0
        set(value) {
            field = value
            updatePaintShadow()
        }
    private var shadowMarginRight: Int = 0
        set(value) {
            field = value
            updatePaintShadow()
        }
    private var shadowMarginBottom: Int = 0
        set(value) {
            field = value
            updatePaintShadow()
        }

    private var button: RoundButton

    constructor(context: Context) : super(context) {
        button = RoundButton(context)
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        button = RoundButton(context, attrs)
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        button = RoundButton(context, attrs, defStyleAttr)
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val a = getContext().obtainStyledAttributes(attrs, R.styleable.RoundButton, defStyleAttr, 0)
        shadowColor = a.getColor(R.styleable.RoundButton_rb_shadow_color
            , ContextCompat.getColor(context, R.color.shadow_view_default_shadow_color))
        foregroundColor = a.getColor(R.styleable.RoundButton_rb_foreground_color
            , ContextCompat.getColor(context, R.color.shadow_view_foreground_color_dark))
        //backgroundClr = a.getColor(R.styleable.RoundButton_backgroundColor, Color.WHITE)
        shadowDx = a.getFloat(R.styleable.RoundButton_rb_shadow_dx, 0f)
        shadowDy = a.getFloat(R.styleable.RoundButton_rb_shadow_dy, 1f)
        shadowRadius = a.getDimensionPixelSize(R.styleable.RoundButton_rb_shadow_radius, SIZE_DEFAULT).toFloat()
        val d = a.getDrawable(R.styleable.RoundButton_android_foreground)
        if (d != null) {
            setForeground(d)
        }
        val shadowMargin = a.getDimensionPixelSize(R.styleable.RoundButton_rb_shadow_margin, SIZE_UNSET)
        if (shadowMargin >= 0) {
            shadowMarginTop = shadowMargin
            shadowMarginLeft = shadowMargin
            shadowMarginRight = shadowMargin
            shadowMarginBottom = shadowMargin
        } else {
            shadowMarginTop = a.getDimensionPixelSize(R.styleable.RoundButton_rb_shadow_margin_top, SIZE_DEFAULT)
            shadowMarginLeft = a.getDimensionPixelSize(R.styleable.RoundButton_rb_shadow_margin_left, SIZE_DEFAULT)
            shadowMarginRight = a.getDimensionPixelSize(R.styleable.RoundButton_rb_shadow_margin_right, SIZE_DEFAULT)
            shadowMarginBottom = a.getDimensionPixelSize(R.styleable.RoundButton_rb_shadow_margin_bottom, SIZE_DEFAULT)
        }

        cornerRadius = a.getDimensionPixelSize(R.styleable.RoundButton_rb_corner_radius, SIZE_UNSET).toFloat()
        a.recycle()
        bgPaint.color = Color.WHITE//backgroundClr
        bgPaint.isAntiAlias = true
        bgPaint.style = Paint.Style.FILL
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        setWillNotDraw(false)
        background = null
        addView(button)
    }

    private fun updatePaintShadow() {
        updatePaintShadow(shadowRadius, shadowDx, shadowDy, shadowColor)
    }

    private fun updatePaintShadow(radius: Float, dx: Float, dy: Float, color: Int) {
        bgPaint.setShadowLayer(radius, dx, dy, color)
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var maxHeight = 0
        var maxWidth = 0
        var childState = 0

        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec))
        val shadowMeasureWidthMatchParent = layoutParams.width == ViewGroup.LayoutParams.MATCH_PARENT
        val shadowMeasureHeightMatchParent = layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT
        var widthSpec = widthMeasureSpec
        if (shadowMeasureWidthMatchParent) {
            val childWidthSize = measuredWidth - shadowMarginRight - shadowMarginLeft
            widthSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY)
        }
        var heightSpec = heightMeasureSpec
        if (shadowMeasureHeightMatchParent) {
            val childHeightSize = measuredHeight - shadowMarginTop - shadowMarginBottom
            heightSpec = MeasureSpec.makeMeasureSpec(childHeightSize, MeasureSpec.EXACTLY)
        }
        if(childCount > 0) {
            val child = getChildAt(0)
            if (child.visibility !== View.GONE) {
                measureChildWithMargins(child, widthSpec, 0, heightSpec, 0)
                val lp = child.layoutParams as LayoutParams
                maxWidth =
                    if (shadowMeasureWidthMatchParent)
                        Math.max(
                            maxWidth,
                            child.measuredWidth + lp.leftMargin + lp.rightMargin
                        )
                    else
                        Math.max(
                            maxWidth,
                            child.measuredWidth + shadowMarginLeft + shadowMarginRight + lp.leftMargin + lp.rightMargin
                        )
                maxHeight =
                    if (shadowMeasureHeightMatchParent)
                        Math.max(
                            maxHeight,
                            child.measuredHeight + lp.topMargin + lp.bottomMargin
                        )
                    else
                        Math.max(
                            maxHeight,
                            child.measuredHeight + shadowMarginTop + shadowMarginBottom + lp.topMargin + lp.bottomMargin
                        )

                childState = View.combineMeasuredStates(childState, child.measuredState)
            }
        }
        maxWidth += paddingLeft + paddingRight
        maxHeight += paddingTop + paddingBottom
        maxHeight = Math.max(maxHeight, suggestedMinimumHeight)
        maxWidth = Math.max(maxWidth, suggestedMinimumWidth)
        val drawable = foreground
        if (drawable != null) {
            maxHeight = Math.max(maxHeight, drawable.minimumHeight)
            maxWidth = Math.max(maxWidth, drawable.minimumWidth)
        }
        setMeasuredDimension(View.resolveSizeAndState(maxWidth
                , if (shadowMeasureWidthMatchParent) widthMeasureSpec else widthSpec, childState),
                View.resolveSizeAndState(maxHeight
                        , if (shadowMeasureHeightMatchParent) heightMeasureSpec else heightSpec,
                        childState shl View.MEASURED_HEIGHT_STATE_SHIFT))
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        layoutChildren(left, top, right, bottom, false)
        if (changed)
            foregroundDrawBoundsChanged = changed
    }

    private fun layoutChildren(left: Int, top: Int, right: Int, bottom: Int, forceLeftGravity: Boolean) {
        val count = childCount

        val parentLeft = getPaddingLeftWithForeground()
        val parentRight = right - left - getPaddingRightWithForeground()

        val parentTop = getPaddingTopWithForeground()
        val parentBottom = bottom - top - getPaddingBottomWithForeground()

        for (i in 0 until count) {
            val child = getChildAt(i)
            if (child.visibility != View.GONE) {
                val lp = child.layoutParams as LayoutParams

                val width = child.measuredWidth
                val height = child.measuredHeight

                var childLeft = 0
                var childTop: Int

                var gravity = lp.gravity
                if (gravity == -1) {
                    gravity = DEFAULT_CHILD_GRAVITY
                }

                val layoutDirection = layoutDirection
                val absoluteGravity = Gravity.getAbsoluteGravity(gravity, layoutDirection)
                val verticalGravity = gravity and Gravity.VERTICAL_GRAVITY_MASK

                when (absoluteGravity and Gravity.HORIZONTAL_GRAVITY_MASK) {
                    Gravity.CENTER_HORIZONTAL -> childLeft = parentLeft + (parentRight - parentLeft - width) / 2 +
                            lp.leftMargin - lp.rightMargin + shadowMarginLeft - shadowMarginRight
                    Gravity.RIGHT -> {
                        if (!forceLeftGravity) {
                            childLeft = parentRight - width - lp.rightMargin - shadowMarginRight
                        }
                    }
                    Gravity.LEFT -> {
                        childLeft = parentLeft + lp.leftMargin + shadowMarginLeft
                    }
                    else -> childLeft = parentLeft + lp.leftMargin + shadowMarginLeft
                }
                childTop = when (verticalGravity) {
                    Gravity.TOP -> parentTop + lp.topMargin + shadowMarginTop
                    Gravity.CENTER_VERTICAL -> parentTop + (parentBottom - parentTop - height) / 2 +
                            lp.topMargin - lp.bottomMargin + shadowMarginTop - shadowMarginBottom
                    Gravity.BOTTOM -> parentBottom - height - lp.bottomMargin - shadowMarginBottom
                    else -> parentTop + lp.topMargin + shadowMarginTop
                }
                child.layout(childLeft, childTop, childLeft + width, childTop + height)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {
            val w = measuredWidth
            val h = measuredHeight
            val path = ShapeUtils.roundedRect(shadowMarginLeft.toFloat(), shadowMarginTop.toFloat(), (w - shadowMarginRight).toFloat()
                    , (h - shadowMarginBottom).toFloat()
                    , cornerRadius
                    , cornerRadius
                    , cornerRadius
                    , cornerRadius)
            it.drawPath(path, bgPaint)
            canvas.clipPath(path)
        }
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        canvas?.let {
            canvas.save()
            val w = measuredWidth
            val h = measuredHeight
            val path = ShapeUtils.roundedRect(shadowMarginLeft.toFloat(), shadowMarginTop.toFloat(), (w - shadowMarginRight).toFloat()
                    , (h - shadowMarginBottom).toFloat()
                    , cornerRadius
                    , cornerRadius
                    , cornerRadius
                    , cornerRadius)
            canvas.clipPath(path)
            drawForeground(canvas)
            canvas.restore()
        }
    }

    private fun getShadowMarginMax() = intArrayOf(shadowMarginLeft, shadowMarginTop, shadowMarginRight, shadowMarginBottom).max()?.toFloat() ?: 0f

    private fun drawForeground(canvas: Canvas?) {
        foregroundDraw?.let {
            if (foregroundDrawBoundsChanged) {
                foregroundDrawBoundsChanged = false
                val w = right - left
                val h = bottom - top
                if (foregroundDrawInPadding) {
                    selfBounds.set(0, 0, w, h)
                } else {
                    selfBounds.set(paddingLeft, paddingTop,
                            w - paddingRight, h - paddingBottom)
                }
                Gravity.apply(foregroundDrawGravity, it.intrinsicWidth,
                        it.intrinsicHeight, selfBounds, overlayBounds)
                it.bounds = overlayBounds
            }
            it.draw(canvas)
        }
    }

    override fun getForeground(): Drawable? {
        return foregroundDraw
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        foregroundDrawBoundsChanged = true
    }

    override fun getForegroundGravity(): Int {
        return foregroundDrawGravity
    }

    override fun setForegroundGravity(foregroundGravity: Int) {
        var foregroundGravity = foregroundGravity
        if (foregroundDrawGravity != foregroundGravity) {
            if (foregroundGravity and Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK == 0) {
                foregroundGravity = foregroundGravity or Gravity.START
            }
            if (foregroundGravity and Gravity.VERTICAL_GRAVITY_MASK == 0) {
                foregroundGravity = foregroundGravity or Gravity.TOP
            }
            foregroundDrawGravity = foregroundGravity
            if (foregroundDrawGravity == Gravity.FILL && foregroundDraw != null) {
                val padding = Rect()
                foregroundDraw?.getPadding(padding)
            }
            requestLayout()
        }
    }

    override fun verifyDrawable(who: Drawable): Boolean {
        return super.verifyDrawable(who) || who === foregroundDraw
    }

    override fun jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState()
        foregroundDraw?.let { it.jumpToCurrentState() }
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        foregroundDraw?.takeIf { it.isStateful }?.let { it.state = drawableState }
    }

    override fun setForeground(drawable: Drawable?) {
        if (foregroundDraw != null) {
            foregroundDraw?.callback = null
            unscheduleDrawable(foregroundDraw)
        }
        foregroundDraw = drawable

        updateForegroundColor()

        if (drawable != null) {
            setWillNotDraw(false)
            drawable.callback = this
            if (drawable.isStateful) {
                drawable.state = drawableState
            }
            if (foregroundDrawGravity == Gravity.FILL) {
                val padding = Rect()
                drawable.getPadding(padding)
            }
        }
        requestLayout()
        invalidate()
    }

    private fun updateForegroundColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            (foregroundDraw as RippleDrawable?)?.setColor(ColorStateList.valueOf(foregroundColor))
        } else {
            foregroundDraw?.setColorFilter(foregroundColor, PorterDuff.Mode.SRC_ATOP)
        }
    }

    override fun drawableHotspotChanged(x: Float, y: Float) {
        super.drawableHotspotChanged(x, y)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            foregroundDraw?.let { it.setHotspot(x, y) }
    }

    fun setShadowMargin(left: Int, top: Int, right: Int, bottom: Int) {
        shadowMarginLeft = left
        shadowMarginTop = top
        shadowMarginRight = right
        shadowMarginBottom = bottom
        requestLayout()
        invalidate()
    }

    fun setCornerRadius(cornerRadius: Float) {
        this.cornerRadius = cornerRadius
        invalidate()
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return LayoutParams(context, attrs)
    }

    override fun shouldDelayChildPressedState(): Boolean {
        return false
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams): Boolean {
        return p is LayoutParams
    }

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams): ViewGroup.LayoutParams {
        return LayoutParams(lp)
    }

    override fun getAccessibilityClassName(): CharSequence {
        return FrameLayout::class.java.name
    }

    private fun getPaddingLeftWithForeground(): Int {
        return paddingLeft
    }

    private fun getPaddingRightWithForeground(): Int {
        return paddingRight
    }

    private fun getPaddingTopWithForeground(): Int {
        return paddingTop
    }

    private fun getPaddingBottomWithForeground(): Int {
        return paddingBottom
    }

    class LayoutParams : MarginLayoutParams {
        var gravity = UNSPECIFIED_GRAVITY

        constructor(c: Context, attrs: AttributeSet?) : super(c, attrs) {
            val a = c.obtainStyledAttributes(attrs, R.styleable.ShadowView_Layout)
            gravity = a.getInt(R.styleable.ShadowView_Layout_layout_gravity, UNSPECIFIED_GRAVITY)
            a.recycle()
        }

        constructor(source: ViewGroup.LayoutParams) : super(source)

        companion object {
            const val UNSPECIFIED_GRAVITY = -1
        }
    }
}
