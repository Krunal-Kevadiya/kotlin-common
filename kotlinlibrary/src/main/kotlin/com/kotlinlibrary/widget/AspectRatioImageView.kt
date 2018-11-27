package com.kotlinlibrary.widget

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageView
import com.kotlinlibrary.R

@Suppress("unused")
class AspectRatioImageView : ImageView {
    private var widthRatio: Int = 0
    private var heightRatio: Int = 0

    constructor(context: Context) : super(context) {
        init(context, null, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr, 0)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) {
            return
        }
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioLayout, defStyleAttr, defStyleRes)

        widthRatio = typedArray.getInteger(R.styleable.AspectRatioLayout_width_ratio, DEFAULT_ASPECT_RATIO)
        heightRatio = typedArray.getInteger(R.styleable.AspectRatioLayout_height_ratio, DEFAULT_ASPECT_RATIO)

        typedArray.recycle()

        validateRatio(widthRatio)
        validateRatio(heightRatio)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        val sizePerRatio = width.toFloat() / widthRatio.toFloat()
        val height = Math.round(sizePerRatio * heightRatio)

        setMeasuredDimension(width, height)
    }

    fun getWidthRatio(): Int {
        return widthRatio
    }

    fun setWidthRatio(widthRatio: Int) {
        validateRatio(widthRatio)
        this.widthRatio = widthRatio
    }

    fun getHeightRatio(): Int {
        return heightRatio
    }

    fun setHeightRatio(heightRatio: Int) {
        validateRatio(heightRatio)
        this.heightRatio = heightRatio
    }

    fun setWidthHeightRatio(widthRatio: Int, heightRatio: Int) {
        validateRatio(widthRatio)
        validateRatio(heightRatio)
        this.widthRatio = widthRatio
        this.heightRatio = heightRatio
    }

    private fun validateRatio(ratio: Int) {
        if (ratio <= 0) {
            throw IllegalArgumentException("ratio > 0")
        }
    }

    companion object {
        private val DEFAULT_ASPECT_RATIO = 1
    }
}