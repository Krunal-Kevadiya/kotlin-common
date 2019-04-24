package com.kotlinlibrary.buttonview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.core.content.ContextCompat
import android.graphics.drawable.LayerDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory


object RoundButtonHelper {

    fun getBitmap(context: Context, drawableId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context, drawableId)
        return if (drawable is BitmapDrawable) drawable.bitmap else getBitmap(drawable!!)
    }

    fun getBitmap(vectorDrawable: Drawable): Bitmap {
        val bitmap =
            Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return bitmap
    }

    fun manipulateColor(color: Int, @FloatRange(from = 0.0, to = 2.0) factor: Float): Int {
        val a = Color.alpha(color)
        val r = Math.round(Color.red(color) * factor)
        val g = Math.round(Color.green(color) * factor)
        val b = Math.round(Color.blue(color) * factor)
        return Color.argb(a, Math.min(r, 255), Math.min(g, 255), Math.min(b, 255))
    }

    fun createDrawable(color: Int, cornerColor: Int, cornerSize: Int, cornerRadius: Int): GradientDrawable {
        val out = GradientDrawable()
        out.setColor(color)
        out.setStroke(cornerSize, cornerColor)
        out.cornerRadius = cornerRadius.toFloat()
        return out
    }

    fun createDrawable(
        context: Context, isInEditMode: Boolean,
        color: Int, cornerColor: Int, cornerSize: Int, cornerRadius: Int,
        shadowWidth: Int, shadowHeight: Int, shadowCornerRadius: Float, shadowRadius: Float,
        dx: Float, dy: Float, shadowColor: Int, fillColor: Int = Color.TRANSPARENT
    ): RoundedBitmapDrawable {
        val out = GradientDrawable()
        out.setColor(color)
        out.setStroke(cornerSize, cornerColor)
        out.cornerRadius = cornerRadius.toFloat()

        val drawable = createShadowBitmap(context, isInEditMode, shadowWidth, shadowHeight, shadowCornerRadius, shadowRadius, dx, dy, shadowColor, fillColor)
        var top = shadowRadius
        var left = shadowRadius
        var right = shadowRadius
        var bottom = shadowRadius

        if (dy > 0) {
            top += dy
            bottom += dy
        } else if (dy < 0) {
            top -= dy
            bottom -= dy
        }

        if (dx > 0) {
            left += dx
            right += dx
        } else if (dx < 0) {
            left -= dx
            right -= dx
        }

        val layers = arrayOfNulls<Drawable>(2)
        layers[0] = drawable
        layers[1] = out
        val layerDrawable = LayerDrawable(layers)
        layerDrawable.setLayerInset(0, 0,0,0,0)
        layerDrawable.setLayerInset(1, left.toInt(), top.toInt(), right.toInt(), bottom.toInt())

        val imageBitmap = drawableToBitmap(layerDrawable)
        val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, imageBitmap)
        //roundedBitmapDrawable.cornerRadius = 50.0f
        roundedBitmapDrawable.setAntiAlias(true)
        return roundedBitmapDrawable
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }

        val bitmap: Bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    }

    private fun createShadowBitmap(
        context: Context, isInEditMode: Boolean,
        shadowWidth: Int, shadowHeight: Int, cornerRadius: Float, shadowRadius: Float,
        dx: Float, dy: Float, shadowColor: Int, fillColor: Int = Color.TRANSPARENT
    ): Drawable {
        val output = Bitmap.createBitmap(shadowWidth, shadowHeight, Bitmap.Config.ALPHA_8)
        val canvas = Canvas(output)

        val shadowRect = RectF(
            shadowRadius, shadowRadius,
            shadowWidth - shadowRadius, shadowHeight - shadowRadius
        )

        if (dy > 0) {
            shadowRect.top += dy
            shadowRect.bottom -= dy
        } else if (dy < 0) {
            shadowRect.top += Math.abs(dy)
            shadowRect.bottom -= Math.abs(dy)
        }

        if (dx > 0) {
            shadowRect.left += dx
            shadowRect.right -= dx
        } else if (dx < 0) {
            shadowRect.left += Math.abs(dx)
            shadowRect.right -= Math.abs(dx)
        }

        val shadowPaint = Paint()
        shadowPaint.isAntiAlias = true
        shadowPaint.color = fillColor
        shadowPaint.style = Paint.Style.FILL

        if (!isInEditMode) {
            shadowPaint.setShadowLayer(shadowRadius, dx, dy, shadowColor)
        }
        canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint)
        val drawable = BitmapDrawable(context.resources, output)
        if(output.isRecycled) {
            output.recycle()
        }
        return drawable
    }

    class Builder : Parcelable {
        internal var animationDurations: Int? = null
        internal var animationCornerRadius: Int? = null
        internal var animationAlpha: Boolean? = null
        internal var animationProgressWidth: Int? = null
        internal var animationProgressColor: Int? = null
        internal var animationProgressPadding: Int? = null
        internal var animationInnerResource: Int? = null
        internal var animationCustomResource: Int? = null
        internal var animationProgressStyle: RoundButton.AnimationProgressStyle? = null
        internal var resultDrawableColor: Int? = null
        internal var resultDrawableResource: Int? = null
        internal var resultSuccessColor: Int? = null
        internal var resultSuccessResource: Int? = null
        internal var resultFailureColor: Int? = null
        internal var resultFailureResource: Int? = null
        internal var cornerRadius: Int? = null
        internal var cornerWidth: Int? = null
        internal var cornerColor: Int? = null
        internal var cornerColorSelected: Int? = null
        internal var cornerColorDisabled: Int? = null
        internal var backgroundColor: Int? = null
        internal var backgroundColorSelected: Int? = null
        internal var backgroundColorDisabled: Int? = null
        internal var textColor: Int? = null
        internal var textColorSelected: Int? = null
        internal var textColorDisabled: Int? = null
        internal var text: String? = null
        internal var width: Int? = null
        internal var height: Int? = null

        internal constructor()

        internal constructor(parcel: Parcel) {
            this.animationDurations = parcel.readValue(Int::class.java.classLoader) as Int
            this.animationCornerRadius = parcel.readValue(Int::class.java.classLoader) as Int
            this.animationAlpha = parcel.readValue(Boolean::class.java.classLoader) as Boolean
            this.animationProgressWidth = parcel.readValue(Int::class.java.classLoader) as Int
            this.animationProgressColor = parcel.readValue(Int::class.java.classLoader) as Int
            this.animationProgressPadding = parcel.readValue(Int::class.java.classLoader) as Int
            this.animationInnerResource = parcel.readValue(Int::class.java.classLoader) as Int
            val tmpAnimationProgressStyle = parcel.readInt()
            this.animationProgressStyle = if (tmpAnimationProgressStyle == -1)
                null
            else
                RoundButton.AnimationProgressStyle.values()[tmpAnimationProgressStyle]
            this.resultDrawableColor = parcel.readValue(Int::class.java.classLoader) as Int
            this.resultDrawableResource = parcel.readValue(Int::class.java.classLoader) as Int
            this.resultSuccessColor = parcel.readValue(Int::class.java.classLoader) as Int
            this.resultSuccessResource = parcel.readValue(Int::class.java.classLoader) as Int
            this.resultFailureColor = parcel.readValue(Int::class.java.classLoader) as Int
            this.resultFailureResource = parcel.readValue(Int::class.java.classLoader) as Int
            this.cornerRadius = parcel.readValue(Int::class.java.classLoader) as Int
            this.cornerWidth = parcel.readValue(Int::class.java.classLoader) as Int
            this.cornerColor = parcel.readValue(Int::class.java.classLoader) as Int
            this.cornerColorSelected = parcel.readValue(Int::class.java.classLoader) as Int
            this.cornerColorDisabled = parcel.readValue(Int::class.java.classLoader) as Int
            this.backgroundColor = parcel.readValue(Int::class.java.classLoader) as Int
            this.backgroundColorSelected = parcel.readValue(Int::class.java.classLoader) as Int
            this.backgroundColorDisabled = parcel.readValue(Int::class.java.classLoader) as Int
            this.textColor = parcel.readValue(Int::class.java.classLoader) as Int
            this.textColorSelected = parcel.readValue(Int::class.java.classLoader) as Int
            this.textColorDisabled = parcel.readValue(Int::class.java.classLoader) as Int
            this.text = parcel.readString()
            this.width = parcel.readValue(Int::class.java.classLoader) as Int
            this.height = parcel.readValue(Int::class.java.classLoader) as Int
        }

        fun withAnimationCustomResource(@DrawableRes animationCustomResource: Int): Builder {
            this.animationCustomResource = animationCustomResource
            return this
        }

        fun withAnimationInnerResource(@DrawableRes animationInnerResource: Int): Builder {
            this.animationInnerResource = animationInnerResource
            return this
        }

        fun withAnimationDurations(animationDurations: Int): Builder {
            this.animationDurations = animationDurations
            return this
        }

        fun withAnimationCornerRadius(animationCornerRadius: Int): Builder {
            this.animationCornerRadius = animationCornerRadius
            return this
        }

        fun withAnimationAlpha(animationAlpha: Boolean): Builder {
            this.animationAlpha = animationAlpha
            return this
        }

        fun withAnimationProgressWidth(animationBarWidth: Int): Builder {
            this.animationProgressWidth = animationBarWidth
            return this
        }

        fun withAnimationProgressColor(animationBarColor: Int): Builder {
            this.animationProgressColor = animationBarColor
            return this
        }

        fun withAnimationProgressPadding(animationBarPadding: Int): Builder {
            this.animationProgressPadding = animationBarPadding
            return this
        }

        fun withAnimationProgressStyle(animationProgressStyle: RoundButton.AnimationProgressStyle): Builder {
            this.animationProgressStyle = animationProgressStyle
            return this
        }

        fun withResultDrawableColor(resultDrawableColor: Int): Builder {
            this.resultDrawableColor = resultDrawableColor
            return this
        }

        fun withResultDrawableResource(resultDrawableResource: Int): Builder {
            this.resultDrawableResource = resultDrawableResource
            return this
        }

        fun withResultSuccessColor(resultSuccessColor: Int): Builder {
            this.resultSuccessColor = resultSuccessColor
            return this
        }

        fun withResultSuccessResource(resultSuccessResource: Int): Builder {
            this.resultSuccessResource = resultSuccessResource
            return this
        }

        fun withResultFailureColor(resultFailureColor: Int): Builder {
            this.resultFailureColor = resultFailureColor
            return this
        }

        fun withResultFailureResource(resultFailureResource: Int): Builder {
            this.resultFailureResource = resultFailureResource
            return this
        }

        fun withCornerRadius(cornerRadius: Int): Builder {
            this.cornerRadius = cornerRadius
            return this
        }

        fun withCornerWidth(cornerWidth: Int): Builder {
            this.cornerWidth = cornerWidth
            return this
        }

        fun withCornerColor(cornerColor: Int): Builder {
            this.cornerColor = cornerColor
            return this
        }

        fun withCornerColorSelected(cornerColorSelected: Int): Builder {
            this.cornerColorSelected = cornerColorSelected
            return this
        }

        fun withCornerColorDisabled(cornerColorDisabled: Int): Builder {
            this.cornerColorDisabled = cornerColorDisabled
            return this
        }

        fun withBackgroundColor(backgroundColor: Int): Builder {
            this.backgroundColor = backgroundColor
            return this
        }

        fun withBackgroundColorSelected(backgroundColorSelected: Int): Builder {
            this.backgroundColorSelected = backgroundColorSelected
            return this
        }

        fun withBackgroundColorDisabled(backgroundColorDisabled: Int): Builder {
            this.backgroundColorDisabled = backgroundColorDisabled
            return this
        }

        fun withTextColor(textColor: Int): Builder {
            this.textColor = textColor
            return this
        }

        fun withTextColorSelected(textColorSelected: Int): Builder {
            this.textColorSelected = textColorSelected
            return this
        }

        fun withTextColorDisabled(textColorDisabled: Int): Builder {
            this.textColorDisabled = textColorDisabled
            return this
        }

        fun withText(text: String): Builder {
            this.text = text
            return this
        }

        fun withWidth(width: Int?): Builder {
            this.width = width
            return this
        }

        fun withHeight(height: Int?): Builder {
            this.height = height
            return this
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeValue(this.animationDurations)
            dest.writeValue(this.animationCornerRadius)
            dest.writeValue(this.animationAlpha)
            dest.writeValue(this.animationProgressWidth)
            dest.writeValue(this.animationProgressColor)
            dest.writeValue(this.animationProgressPadding)
            dest.writeValue(this.animationInnerResource)
            dest.writeInt(if (this.animationProgressStyle == null) -1 else this.animationProgressStyle!!.ordinal)
            dest.writeValue(this.resultDrawableColor)
            dest.writeValue(this.resultDrawableResource)
            dest.writeValue(this.resultSuccessColor)
            dest.writeValue(this.resultSuccessResource)
            dest.writeValue(this.resultFailureColor)
            dest.writeValue(this.resultFailureResource)
            dest.writeValue(this.cornerRadius)
            dest.writeValue(this.cornerWidth)
            dest.writeValue(this.cornerColor)
            dest.writeValue(this.cornerColorSelected)
            dest.writeValue(this.cornerColorDisabled)
            dest.writeValue(this.backgroundColor)
            dest.writeValue(this.backgroundColorSelected)
            dest.writeValue(this.backgroundColorDisabled)
            dest.writeValue(this.textColor)
            dest.writeValue(this.textColorSelected)
            dest.writeValue(this.textColorDisabled)
            dest.writeString(this.text)
            dest.writeValue(this.width)
            dest.writeValue(this.height)
        }

        companion object CREATOR : Parcelable.Creator<Builder> {
            override fun createFromParcel(parcel: Parcel): Builder {
                return Builder(parcel)
            }

            override fun newArray(size: Int): Array<Builder?> {
                return arrayOfNulls(size)
            }
        }
    }
}
