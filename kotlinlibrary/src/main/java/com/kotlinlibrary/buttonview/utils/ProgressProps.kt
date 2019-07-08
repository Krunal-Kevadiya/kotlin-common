package com.kotlinlibrary.buttonview.utils

import android.graphics.Color
import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Suppress("ArrayInDataClass")
@Parcelize
data class ProgressProps(
    var pbGradientName: DefaultGradientName? = null,
    var pbGradientType: GradientType = GradientType.LINEAR,
    var pbRadialRadius: Float? = null,
    var pbGradientAngle: Double? = null,
    var pbGradientColor: IntArray? = null,
    var pbGradientPositions: FloatArray? = null,
    @ColorInt var pbNormalColor: Int? = null,

    var pbCornerTopLeftRadius: Float = 0f,
    var pbCornerTopRightRadius: Float = 0f,
    var pbCornerBottomLeftRadius: Float = 0f,
    var pbCornerBottomRightRadius: Float = 0f,

    @ColorInt var pbStateSuccessBackgroundColor: Int = 0,
    @DrawableRes var pbStateSuccessDrawable: Int = 0,
    @ColorInt var pbStateSuccessDrawableTint: Int = Color.TRANSPARENT,
    @ColorInt var pbStateFailureBackgroundColor: Int = 0,
    @DrawableRes var pbStateFailureDrawable: Int = 0,
    @ColorInt var pbStateFailureDrawableTint: Int = Color.TRANSPARENT,
    var pbStateWithStroke: Boolean = false,
    var pbStateRevertDelay: Long = 0,

    var pbDrawablePadding: Int = 0,
    @DrawableRes var pbImageDrawable: Int = 0,
    @ColorInt var pbImageDrawableTint: Int = Color.TRANSPARENT,

    var animRadius: Float = 0f,
    var pbAnimDuration: Int = 0,
    var pbAnimCornerRadius: Float = 0f,
    var pbAnimAlphaMorphing: Boolean = false,

    var pbProgressStyle: ProgressStyle? = null,
    @DrawableRes var pbProgressDrawable: Int = 0,
    @ColorInt var pbProgressColor: Int = Color.WHITE,
    var pbProgressPadding: Float = 0f,
    var pbProgressWidth: Float = 0f,
    var pbProgressDotsCount: Int? = null,
    @DrawableRes var pbProgressInnerDrawable: Int = 0,
    @ColorInt var pbProgressInnerDrawableTint: Int = Color.TRANSPARENT,

    var pbStrokeWidth: Float = 0f,
    var pbStrokeDashWidth: Float = 0f,
    var pbStrokeDashGap: Float = 0f,
    @ColorInt var pbStrokeColor: Int = Color.TRANSPARENT,

    var centerX: Float = 0.0f,
    var centerY: Float = 0.0f,
    var scaleX: Float = 1.0f,
    var scaleY: Float = 1.0f
) : Parcelable


