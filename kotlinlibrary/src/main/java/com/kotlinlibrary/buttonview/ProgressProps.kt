package com.kotlinlibrary.buttonview

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

data class ProgressProps(
    var pbGradientAngle: Float? = null,
    var pbGradientColor: IntArray? = null,
    var pbGradientPositions: FloatArray? = null,
    @ColorInt var pbNormalColor: Int? = null,
    var pbCornerTopLeftRadius: Int? = null,
    var pbCornerTopRightRadius: Int? = null,
    var pbCornerBottomLeftRadius: Int? = null,
    var pbCornerBottomRightRadius: Int? = null,
    @ColorInt var pbStateSuccessBackgroundColor: Int? = null,
    @DrawableRes var pbStateSuccessDrawable: Int? = null,
    @ColorInt var pbStateSuccessDrawableTint: Int? = null,
    @ColorInt var pbStateFailureBackgroundColor: Int? = null,
    @DrawableRes var pbStateFailureDrawable: Int? = null,
    @ColorInt var pbStateFailureDrawableTint: Int? = null,
    @DrawableRes var pbImageDrawable: Int? = null,
    @ColorInt var pbImageDrawableTint: Int? = null,
    var pbAnimDuration: Int? = null,
    var pbAnimCornerRadius: Int? = null,
    var pbAnimAlphaMorphing: Boolean? = null,
    var pbProgressStyle: ProgressStyle? = null,
    @ColorInt var pbProgressColor: Int? = null,
    var pbButtonStyle: ButtonStyle? = null,
    var pbStrokeWidth: Int? = null,
    @ColorInt var pbStrokeColor: Int? = null,
    var pbStateWithStroke: Boolean? = null,
    var pbStateWithRevert: Boolean? = null,
    var pbStateRevertDuration: Int? = null,
    var pbButtonShape: ButtonShape? = null,
    var pbProgressDotsCount: Int? = null
)

enum class ButtonShape {
    OVAL, RECT, ROUNDED_RECT, CIRCLE, UNDEFINED
}

enum class ProgressStyle {
    CIRCLE, DOTS, CUSTOM
}

enum class ButtonStyle {
    SOLID, STROKE, BOTH
}