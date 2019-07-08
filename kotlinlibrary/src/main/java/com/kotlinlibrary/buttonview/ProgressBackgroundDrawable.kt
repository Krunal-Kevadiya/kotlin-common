package com.kotlinlibrary.buttonview

import android.graphics.*
import android.graphics.drawable.Drawable
import com.kotlinlibrary.buttonview.utils.GradientType
import com.kotlinlibrary.buttonview.utils.ProgressProps
import com.kotlinlibrary.buttonview.utils.ResourceUtil
import kotlin.math.*

class ProgressBackgroundDrawable(
    val progressProps: ProgressProps,
    private val width: Int,
    private val height: Int
): Drawable() {
    private var centerInit = false
    private val shapePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val shapeBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val tileMode: Shader.TileMode = Shader.TileMode.CLAMP

    private val matrix: Matrix = Matrix()

    init {
        update()
    }

    private fun update() {
        val data = ResourceUtil.getLinearGradientPojo(
            progressProps.pbGradientName,
            progressProps.pbGradientAngle,
            progressProps.pbGradientColor,
            progressProps.pbGradientPositions,
            progressProps.pbNormalColor
        )
        shapePaint.isDither = true
        shapePaint.alpha = progressProps.pbGradientAngle?.toInt() ?: 255
        shapePaint.isAntiAlias = true

        if (!centerInit) {
            progressProps.centerX = width / 2.0f
            progressProps.centerY = height / 2.0f
            centerInit = true
        }

        matrix.setScale(progressProps.scaleX, progressProps.scaleY, progressProps.centerX, progressProps.centerY)
        val matrixWithoutRotate = Matrix(matrix)
        matrix.postRotate(data.angle.toFloat(), progressProps.centerX, progressProps.centerY)
        shapePaint.shader = when (progressProps.pbGradientType) {
            GradientType.LINEAR -> {
                val radians = Math.toRadians(data.angle)
                val hyp = sqrt((width*width + height*height).toDouble())

                var startX = 0f
                var startY = 0f
                var endX = (cos(radians) * hyp).toFloat()
                var endY = (sin(radians) * hyp).toFloat()

                if (endX < 0) {
                    startX = width.toFloat()
                    endX += width.toFloat()
                }
                if (endY < 0) {
                    startY = height.toFloat()
                    endY += height.toFloat()
                }

                val shader = LinearGradient(startX, startY, endX, endY, data.colors, data.positions, tileMode)
                shader.setLocalMatrix(matrixWithoutRotate)
                shader
            }
            GradientType.RADIAL -> {
                val r = if (progressProps.pbRadialRadius != null) progressProps.pbRadialRadius else max(width, height) / 2f
                val shader = RadialGradient(progressProps.centerX, progressProps.centerY, r!!, data.colors, data.positions, tileMode)
                shader.setLocalMatrix(matrix)
                shader
            }
            GradientType.SWEEP -> {
                val shader = SweepGradient(progressProps.centerX, progressProps.centerY, data.colors, data.positions)
                shader.setLocalMatrix(matrix)
                shader
            }
        }

        shapeBorderPaint.isDither = true
        shapeBorderPaint.style = Paint.Style.STROKE
        shapeBorderPaint.color = progressProps.pbStrokeColor
        shapeBorderPaint.strokeWidth = progressProps.pbStrokeWidth

        shapeBorderPaint.pathEffect = DashPathEffect(
            floatArrayOf(progressProps.pbStrokeDashWidth, progressProps.pbStrokeDashGap),
            0f
        )
        rebuild()
    }

    override fun draw(canvas: Canvas) {
        val strokeWidth = progressProps.pbStrokeWidth + (progressProps.pbStrokeWidth/2)

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

        canvas.drawPath(
            ResourceUtil.roundedRect(
                false, width, height,
                strokeWidth,
                strokeWidth,
                width.toFloat() - strokeWidth,
                height.toFloat() - strokeWidth,
                topLeft, topRight, bottomLeft, bottomRight
            ),
            shapePaint
        )

        canvas.drawPath(
            ResourceUtil.roundedRect(
                false, width, height,
                progressProps.pbStrokeWidth,
                progressProps.pbStrokeWidth,
                width - progressProps.pbStrokeWidth,
                height - progressProps.pbStrokeWidth,
                topLeft, topRight, bottomLeft, bottomRight
            ),
            shapeBorderPaint
        )
    }

    fun rebuild() {
        invalidateSelf()
    }

    fun center(x: Float, y: Float) {
        progressProps.centerX = x
        progressProps.centerY = y
        centerInit = true
        rebuild()
    }

    fun scale(x: Float, y: Float) {
        progressProps.scaleX = x
        progressProps.scaleY = y
        rebuild()
    }

    override fun setAlpha(alpha: Int) {
        progressProps.pbGradientAngle = alpha.toDouble()
        rebuild()
    }

    override fun getOpacity(): Int {
        return when (progressProps.pbGradientAngle?.toInt()) {
            1 -> PixelFormat.OPAQUE
            0 -> PixelFormat.TRANSPARENT
            else -> PixelFormat.TRANSLUCENT
        }
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        throw NotImplementedError()
    }
}