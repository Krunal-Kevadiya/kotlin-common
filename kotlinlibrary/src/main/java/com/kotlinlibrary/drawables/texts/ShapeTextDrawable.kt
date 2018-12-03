package com.kotlinlibrary.drawables.texts

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape

class ShapeTextDrawable : ShapeDrawable {
    val text: String
    val textSize: Int
    private val borderThickness: Int
    val radius: Float
    private val textPaint: Paint
    private val borderPaint: Paint

    constructor(
        shapeForm: ShapeForm,
        pColor: Int = Color.GRAY,
        radius: Float = 0f,
        text: String = "",
        textColor: Int = Color.WHITE,
        textBold: Boolean = false,
        textFont: Typeface = Typeface.create("sans-serif-light", Typeface.NORMAL),
        textSize: Int = -1,
        borderColor: Int = Color.TRANSPARENT,
        borderThickness: Int = 0
    ) {
        this.text = text
        this.textSize = textSize
        this.borderThickness = borderThickness
        this.radius = radius

        paint.color = pColor
        // text paint settings
        textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = textColor
            isFakeBoldText = textBold
            typeface = textFont
            textAlign = Paint.Align.CENTER
            strokeWidth = borderThickness.toFloat()
        }
        // border paint settings
        borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            color = borderColor
            strokeWidth = borderThickness.toFloat()
        }
        // paint shape
        when (shapeForm) {
            ShapeForm.ROUND -> this.shape = OvalShape()
            ShapeForm.SQUARE -> if (radius > 0) {
                val radii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
                this.shape = RoundRectShape(radii, null, null)
            } else {
                this.shape = RectShape()
            }
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        val r = bounds
        // draw border
        if (borderThickness > 0) {
            drawBorder(canvas)
        }
        val count = canvas.save()
        canvas.translate(r.left.toFloat(), r.top.toFloat())
        // draw text
        val fontSize = if (textSize < 0) Math.min(r.width(), r.height()) / 2 else textSize
        textPaint.textSize = fontSize.toFloat()

        canvas.drawText(text, (r.width() / 2).toFloat(), r.height() / 2 - (textPaint.descent() + textPaint.ascent()) / 2, textPaint)
        canvas.restoreToCount(count)
    }

    private fun drawBorder(canvas: Canvas) {
        val rect = RectF(bounds)
        rect.inset((borderThickness / 2).toFloat(), (borderThickness / 2).toFloat())

        when (shape) {
            is OvalShape -> canvas.drawOval(rect, borderPaint)
            is RoundRectShape -> canvas.drawRoundRect(rect, radius, radius, borderPaint)
            else -> canvas.drawRect(rect, borderPaint)
        }
    }

    override fun setAlpha(alpha: Int) {
        textPaint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        textPaint.colorFilter = cf
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun getIntrinsicWidth(): Int {
        return -1
    }

    override fun getIntrinsicHeight(): Int {
        return -1
    }
}
