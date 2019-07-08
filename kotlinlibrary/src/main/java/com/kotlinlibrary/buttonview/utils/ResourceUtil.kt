package com.kotlinlibrary.buttonview.utils

import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import kotlin.math.abs
import kotlin.math.min

object ResourceUtil {

    fun getBitmap(vectorDrawable: Drawable): Bitmap {
        val bitmap =
            Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return bitmap
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getBitmap(vectorDrawable: VectorDrawable): Bitmap {
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return bitmap
    }

    private fun getBitmap(vectorDrawable: VectorDrawableCompat): Bitmap {
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return bitmap
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun getBitmap(context: Context, @DrawableRes drawableResId: Int): Bitmap {
        return when (val drawable = ContextCompat.getDrawable(context, drawableResId)) {
            is BitmapDrawable -> drawable.bitmap
            is VectorDrawableCompat -> getBitmap(drawable)
            is VectorDrawable -> getBitmap(drawable)
            else -> throw IllegalArgumentException("Unsupported drawable type")
        }
    }

    fun getScaleToFitBitmap(height: Int, width: Int, context: Context,  @DrawableRes drawableResId: Int, padding: Int): Bitmap {
        val b: Bitmap = if(drawableResId != 0) getBitmap(
            context,
            drawableResId
        ) else Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        return if (min(height, width) == width) {
            scaleToFitWidth(b, padding, width)
        } else {
            scaleToFitHeight(b, padding, height)
        }
    }

    private fun scaleToFitWidth(b: Bitmap, padding: Int, width: Int): Bitmap {
        val mWidth = width - padding
        val factor = mWidth / b.width.toFloat()
        return Bitmap.createScaledBitmap(b, mWidth, (b.height * factor).toInt(), true)
    }

    private fun scaleToFitHeight(b: Bitmap, padding: Int, height: Int): Bitmap {
        val mHeight = height - padding
        val factor = mHeight / b.height.toFloat()
        return Bitmap.createScaledBitmap(b, (b.width * factor).toInt(), mHeight, true)
    }

    fun tintImage(bitmap: Bitmap, color: Int): Bitmap {
        val paintImage = Paint()
        if(color != Color.TRANSPARENT)
            paintImage.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
        paintImage.isAntiAlias = true
        paintImage.isDither = true
        paintImage.isFilterBitmap = true

        val bitmapResult = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmapResult)
        canvas.drawBitmap(bitmap, 0f, 0f, paintImage)
        bitmap.recycle()
        return bitmapResult
    }

    fun roundedRect(useBezier: Boolean, width: Int, height: Int,
                            left: Float, top: Float, right: Float, bottom: Float,
                            topLeftRadius: Float, topRightRadius: Float, bottomLeftRadius: Float, bottomRightRadius: Float
    ): Path {
        val path = Path()
        val maxSize = min(width / 2f, height / 2f)

        var topLeftRadiusAbs = abs(topLeftRadius)
        var topRightRadiusAbs = abs(topRightRadius)
        var bottomLeftRadiusAbs = abs(bottomLeftRadius)
        var bottomRightRadiusAbs = abs(bottomRightRadius)

        if (topLeftRadiusAbs > maxSize) {
            topLeftRadiusAbs = maxSize
        }
        if (topRightRadiusAbs > maxSize) {
            topRightRadiusAbs = maxSize
        }
        if (bottomLeftRadiusAbs > maxSize) {
            bottomLeftRadiusAbs = maxSize
        }
        if (bottomRightRadiusAbs > maxSize) {
            bottomRightRadiusAbs = maxSize
        }

        path.moveTo(left + topLeftRadiusAbs, top)
        path.lineTo(right - topRightRadiusAbs, top)

        if (useBezier) {
            path.quadTo(right, top, right, top + topRightRadiusAbs)
        } else {
            val arc = (if (topRightRadius > 0) 90 else -270).toFloat()
            path.arcTo(RectF(right - topRightRadiusAbs * 2f, top, right, top + topRightRadiusAbs * 2f), -90f, arc)
        }
        path.lineTo(right, bottom - bottomRightRadiusAbs)
        if (useBezier) {
            path.quadTo(right, bottom, right - bottomRightRadiusAbs, bottom)
        } else {
            val arc = (if (bottomRightRadiusAbs > 0) 90 else -270).toFloat()
            path.arcTo(
                RectF(right - bottomRightRadiusAbs * 2f, bottom - bottomRightRadiusAbs * 2f, right, bottom),
                0f,
                arc
            )
        }
        path.lineTo(left + bottomLeftRadiusAbs, bottom)
        if (useBezier) {
            path.quadTo(left, bottom, left, bottom - bottomLeftRadiusAbs)
        } else {
            val arc = (if (bottomLeftRadiusAbs > 0) 90 else -270).toFloat()
            path.arcTo(RectF(left, bottom - bottomLeftRadiusAbs * 2f, left + bottomLeftRadiusAbs * 2f, bottom), 90f, arc)
        }
        path.lineTo(left, top + topLeftRadiusAbs)
        if (useBezier) {
            path.quadTo(left, top, left + topLeftRadiusAbs, top)
        } else {
            val arc = (if (topLeftRadiusAbs > 0) 90 else -270).toFloat()
            path.arcTo(RectF(left, top, left + topLeftRadiusAbs * 2f, top + topLeftRadiusAbs * 2f), 180f, arc)
        }
        path.close()
        return path
    }

    fun getLinearGradientPojo(
        type: DefaultGradientName?,
        angle: Double?,
        colors: IntArray?,
        positions: FloatArray?,
        color: Int?
    ): LinearGradientPojo {
        return when (type) {
            DefaultGradientName.WARM_FLAME -> {
                LinearGradientPojo(
                    angle ?: -45.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ff9a9e"),
                        Color.parseColor("#fad0c4"),
                        Color.parseColor("#fad0c4")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.99f, 1.0f)
                )
            }
            DefaultGradientName.NIGHT_FADE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#a18cd1"),
                        Color.parseColor("#fbc2eb")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SPRING_WARMTH -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#fad0c4"),
                        Color.parseColor("#fad0c4"),
                        Color.parseColor("#ffd1ff")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.01f, 1.0f)
                )
            }
            DefaultGradientName.JUICY_PEACH -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ffecd2"),
                        Color.parseColor("#fcb69f")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.YOUNG_PASSION -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ff8177"),
                        Color.parseColor("#ff867a"),
                        Color.parseColor("#ff8c7f"),
                        Color.parseColor("#f99185"),
                        Color.parseColor("#cf556c"),
                        Color.parseColor("#b12a5b")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.0f, 0.21f, 0.52f, 0.78f, 1.0f)
                )
            }
            DefaultGradientName.LADY_LIPS -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ff9a9e"),
                        Color.parseColor("#fecfef"),
                        Color.parseColor("#fecfef")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.99f, 1.0f)
                )
            }
            DefaultGradientName.SUNNY_MORNING -> {
                LinearGradientPojo(
                    angle ?: 30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#f6d365"),
                        Color.parseColor("#fda085")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.RAINY_ASHVILLE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#fbc2eb"),
                        Color.parseColor("#a6c1ee")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.FROZEN_DREAMS -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#fdcbf1"),
                        Color.parseColor("#fdcbf1"),
                        Color.parseColor("#e6dee9")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.01f, 1.0f)
                )
            }
            DefaultGradientName.WINTER_NEVA -> {
                LinearGradientPojo(
                    angle ?: 30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#a1c4fd"),
                        Color.parseColor("#c2e9fb")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.DUSTY_GRASS -> {
                LinearGradientPojo(
                    angle ?: 30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#d4fc79"),
                        Color.parseColor("#96e6a1")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.TEMPTING_AZURE -> {
                LinearGradientPojo(
                    angle ?: 30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#84fab0"),
                        Color.parseColor("#8fd3f4")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.HEAVY_RAIN -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#cfd9df"),
                        Color.parseColor("#e2ebf0")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.AMY_CRISP -> {
                LinearGradientPojo(
                    angle ?: 30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#a6c0fe"),
                        Color.parseColor("#f68084")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.MEAN_FRUIT -> {
                LinearGradientPojo(
                    angle ?: 30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#fccb90"),
                        Color.parseColor("#d57eeb")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.DEEP_BLUE -> {
                LinearGradientPojo(
                    angle ?: 30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#e0c3fc"),
                        Color.parseColor("#8ec5fc")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.RIPE_MALINKA -> {
                LinearGradientPojo(
                    angle ?: 30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#f093fb"),
                        Color.parseColor("#f5576c")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.CLOUDY_KNOXVILLE -> {
                LinearGradientPojo(
                    angle ?: 30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#fdfbfb"),
                        Color.parseColor("#ebedee")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.MALIBU_BEACH -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#4facfe"),
                        Color.parseColor("#00f2fe")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.NEW_LIFE -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#43e97b"),
                        Color.parseColor("#38f9d7")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.TRUE_SUNSET -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#fa709a"),
                        Color.parseColor("#fee140")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.MORPHEUS_DEN -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#30cfd0"),
                        Color.parseColor("#330867")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.RARE_WIND -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#a8edea"),
                        Color.parseColor("#fed6e3")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.NEAR_MOON -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#5ee7df"),
                        Color.parseColor("#b490ca")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.WILD_APPLE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#d299c2"),
                        Color.parseColor("#fef9d7")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SAINT_PETERSBURG -> {
                LinearGradientPojo(
                    angle ?: 45.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#f5f7fa"),
                        Color.parseColor("#c3cfe2")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.PLUM_PLATE -> {
                LinearGradientPojo(
                    angle ?: 45.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#667eea"),
                        Color.parseColor("#764ba2")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.EVERLASTING_SKY -> {
                LinearGradientPojo(
                    angle ?: 45.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#fdfcfb"),
                        Color.parseColor("#e2d1c3")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.HAPPY_FISHER -> {
                LinearGradientPojo(
                    angle ?: 30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#89f7fe"),
                        Color.parseColor("#66a6ff")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.BLESSING -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#fddb92"),
                        Color.parseColor("#d1fdff")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SHARPEYE_EAGLE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#9890e3"),
                        Color.parseColor("#b1f4cf")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.LADOGA_BOTTOM -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ebc0fd"),
                        Color.parseColor("#d9ded8")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.LEMON_GATE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#96fbc4"),
                        Color.parseColor("#f9f586")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.ITMEO_BRANDING -> {
                LinearGradientPojo(
                    angle ?: 90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#2af598"),
                        Color.parseColor("#009efd")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.ZEUS_MIRACLE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#cd9cf2"),
                        Color.parseColor("#f6f3ff")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.OLD_HAT -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#e4afcb"),
                        Color.parseColor("#b8cbb8"),
                        Color.parseColor("#b8cbb8"),
                        Color.parseColor("#e2c58b"),
                        Color.parseColor("#c2ce9c"),
                        Color.parseColor("#7edbdc")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.0f, 0.0f, 0.3f, 0.64f, 1.0f)
                )
            }
            DefaultGradientName.STAR_WINE -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#b8cbb8"),
                        Color.parseColor("#b8cbb8"),
                        Color.parseColor("#b465da"),
                        Color.parseColor("#cf6cc9"),
                        Color.parseColor("#ee609c"),
                        Color.parseColor("#ee609c")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.0f, 0.0f, 0.33f, 0.66f, 1.0f)
                )
            }
            DefaultGradientName.DEEP_BLUE2 -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#6a11cb"),
                        Color.parseColor("#2575fc")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.HAPPY_ACID -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#37ecba"),
                        Color.parseColor("#72afd3")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.AWESOME_PINE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ebbba7"),
                        Color.parseColor("#cfc7f8")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.NEW_YORK -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#fff1eb"),
                        Color.parseColor("#ace0f9")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SHY_RAINBOW -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#eea2a2"),
                        Color.parseColor("#bbc1bf"),
                        Color.parseColor("#57c6e1"),
                        Color.parseColor("#b49fda"),
                        Color.parseColor("#7ac5d8")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.19f, 0.42f, 0.79f, 1.0f)
                )
            }
            DefaultGradientName.MIXED_HOPES -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#c471f5"),
                        Color.parseColor("#fa71cd")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.FLY_HIGH -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#48c6ef"),
                        Color.parseColor("#6f86d6")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.STRONG_BLISS -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#f78ca0"),
                        Color.parseColor("#f9748f"),
                        Color.parseColor("#fd868c"),
                        Color.parseColor("#fe9a8b")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.19f, 0.6f, 1.0f)
                )
            }
            DefaultGradientName.FRESH_MILK -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#feada6"),
                        Color.parseColor("#f5efef")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SNOW_AGAIN -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#e6e9f0"),
                        Color.parseColor("#eef1f5")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.FEBRUARY_INK -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#accbee"),
                        Color.parseColor("#e7f0fd")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.KIND_STEEL -> {
                LinearGradientPojo(
                    angle ?: -110.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#e9defa"),
                        Color.parseColor("#fbfcdb")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SOFT_GRASS -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#c1dfc4"),
                        Color.parseColor("#deecdd")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.GROWN_EARLY -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#0ba360"),
                        Color.parseColor("#3cba92")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SHARP_BLUES -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#00c6fb"),
                        Color.parseColor("#005bea")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SHADY_WATER -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#74ebd5"),
                        Color.parseColor("#9face6")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.DIRTY_BEAUTY -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#6a85b6"),
                        Color.parseColor("#bac8e0")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.GREAT_WHALE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#a3bded"),
                        Color.parseColor("#6991c7")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.TEEN_NOTEBOOK -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#9795f0"),
                        Color.parseColor("#fbc8d4")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.POLITE_RUMORS -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#a7a6cb"),
                        Color.parseColor("#8989ba"),
                        Color.parseColor("#8989ba")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.52f, 1.0f)
                )
            }
            DefaultGradientName.SWEET_PERIOD -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#3f51b1"),
                        Color.parseColor("#5a55ae"),
                        Color.parseColor("#7b5fac"),
                        Color.parseColor("#8f6aae"),
                        Color.parseColor("#a86aa4"),
                        Color.parseColor("#cc6b8e"),
                        Color.parseColor("#f18271"),
                        Color.parseColor("#f3a469"),
                        Color.parseColor("#f7c978")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.13f, 0.25f, 0.38f, 0.5f, 0.62f, 0.75f, 0.87f, 1.0f)
                )
            }
            DefaultGradientName.WIDE_MATRIX -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#fcc5e4"),
                        Color.parseColor("#fda34b"),
                        Color.parseColor("#ff7882"),
                        Color.parseColor("#c8699e"),
                        Color.parseColor("#7046aa"),
                        Color.parseColor("#0c1db8"),
                        Color.parseColor("#020f75")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.15f, 0.35f, 0.52f, 0.71f, 0.87f, 1.0f)
                )
            }
            DefaultGradientName.SOFT_CHERISH -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#dbdcd7"),
                        Color.parseColor("#dddcd7"),
                        Color.parseColor("#e2c9cc"),
                        Color.parseColor("#e7627d"),
                        Color.parseColor("#b8235a"),
                        Color.parseColor("#801357"),
                        Color.parseColor("#3d1635"),
                        Color.parseColor("#1c1a27")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.24f, 0.3f, 0.46f, 0.59f, 0.71f, 0.84f, 1.0f)
                )
            }
            DefaultGradientName.RED_SALVATION -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#f43b47"),
                        Color.parseColor("#453a94")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.BURNING_SPRING -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#4fb576"),
                        Color.parseColor("#44c489"),
                        Color.parseColor("#28a9ae"),
                        Color.parseColor("#28a2b7"),
                        Color.parseColor("#4c7788"),
                        Color.parseColor("#6c4f63"),
                        Color.parseColor("#432c39")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.3f, 0.46f, 0.59f, 0.71f, 0.86f, 1.0f)
                )
            }
            DefaultGradientName.NIGHT_PARTY -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#0250c5"),
                        Color.parseColor("#d43f8d")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SKY_GLIDER -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#88d3ce"),
                        Color.parseColor("#6e45e2")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.HEAVEN_PEACH -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#d9afd9"),
                        Color.parseColor("#97d9e1")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.PURPLE_DIVISION -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#7028e4"),
                        Color.parseColor("#e5b2ca")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.AQUA_SPLASH -> {
                LinearGradientPojo(
                    angle ?: -75.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#13547a"),
                        Color.parseColor("#80d0c7")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SPIKY_NAGA -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#505285"),
                        Color.parseColor("#585e92"),
                        Color.parseColor("#65689f"),
                        Color.parseColor("#7474b0"),
                        Color.parseColor("#7e7ebb"),
                        Color.parseColor("#8389c7"),
                        Color.parseColor("#9795d4"),
                        Color.parseColor("#a2a1dc"),
                        Color.parseColor("#b5aee4")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.12f, 0.25f, 0.37f, 0.5f, 0.62f, 0.75f, 0.87f, 1.0f)
                )
            }
            DefaultGradientName.LOVE_KISS -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ff0844"),
                        Color.parseColor("#ffb199")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.CLEAN_MIRROR -> {
                LinearGradientPojo(
                    angle ?: -45.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#93a5cf"),
                        Color.parseColor("#e4efe9")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.PREMIUM_DARK -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#434343"),
                        Color.parseColor("black")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.COLD_EVENING -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#0c3483"),
                        Color.parseColor("#a2b6df"),
                        Color.parseColor("#6b8cce"),
                        Color.parseColor("#a2b6df")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f, 1.0f, 1.0f)
                )
            }
            DefaultGradientName.COCHITI_LAKE -> {
                LinearGradientPojo(
                    angle ?: -45.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#93a5cf"),
                        Color.parseColor("#e4efe9")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SUMMER_GAMES -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#92fe9d"),
                        Color.parseColor("#00c9ff")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.PASSIONATE_BED -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ff758c"),
                        Color.parseColor("#ff7eb3")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.MOUNTAIN_ROCK -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#868f96"),
                        Color.parseColor("#596164")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.DESERT_HUMP -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#c79081"),
                        Color.parseColor("#dfa579")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.JUNGLE_DAY -> {
                LinearGradientPojo(
                    angle ?: -45.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#8baaaa"),
                        Color.parseColor("#ae8b9c")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.PHOENIX_START -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#f83600"),
                        Color.parseColor("#f9d423")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.OCTOBER_SILENCE -> {
                LinearGradientPojo(
                    angle ?: -110.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#b721ff"),
                        Color.parseColor("#21d4fd")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.FARAWAY_RIVER -> {
                LinearGradientPojo(
                    angle ?: -110.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#6e45e2"),
                        Color.parseColor("#88d3ce")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.ALCHEMIST_LAB -> {
                LinearGradientPojo(
                    angle ?: -110.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#d558c8"),
                        Color.parseColor("#24d292")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.OVER_SUN -> {
                LinearGradientPojo(
                    angle ?: -30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#abecd6"),
                        Color.parseColor("#fbed96")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.PREMIUM_WHITE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#d5d4d0"),
                        Color.parseColor("#d5d4d0"),
                        Color.parseColor("#eeeeec"),
                        Color.parseColor("#efeeec"),
                        Color.parseColor("#e9e9e7")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.01f, 0.31f, 0.75f, 1.0f)
                )
            }
            DefaultGradientName.MARS_PARTY -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#5f72bd"),
                        Color.parseColor("#9b23ea")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.ETERNAL_CONSTANCE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#09203f"),
                        Color.parseColor("#537895")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.JAPAN_BLUSH -> {
                LinearGradientPojo(
                    angle ?: -110.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ddd6f3"),
                        Color.parseColor("#faaca8"),
                        Color.parseColor("#faaca8")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f, 1.0f)
                )
            }
            DefaultGradientName.SMILING_RAIN -> {
                LinearGradientPojo(
                    angle ?: -110.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#dcb0ed"),
                        Color.parseColor("#99c99c")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.CLOUDY_APPLE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#f3e7e9"),
                        Color.parseColor("#e3eeff"),
                        Color.parseColor("#e3eeff")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.99f, 1.0f)
                )
            }
            DefaultGradientName.BIG_MANGO -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#c71d6f"),
                        Color.parseColor("#d09693")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.HEALTHY_WATER -> {
                LinearGradientPojo(
                    angle ?: -30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#96deda"),
                        Color.parseColor("#50c9c3")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.AMOUR_AMOUR -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#f77062"),
                        Color.parseColor("#fe5196")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.RISKY_CONCRETE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#c4c5c7"),
                        Color.parseColor("#dcdddf"),
                        Color.parseColor("#ebebeb")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.52f, 1.0f)
                )
            }
            DefaultGradientName.STRONG_STICK -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#a8caba"),
                        Color.parseColor("#5d4157")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.VICIOUS_STANCE -> {
                LinearGradientPojo(
                    angle ?: -30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#29323c"),
                        Color.parseColor("#485563")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.PALO_ALTO -> {
                LinearGradientPojo(
                    angle ?: -150.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#16a085"),
                        Color.parseColor("#f4d03f")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.HAPPY_MEMORIES -> {
                LinearGradientPojo(
                    angle ?: -150.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ff5858"),
                        Color.parseColor("#f09819")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.MIDNIGHT_BLOOM -> {
                LinearGradientPojo(
                    angle ?: -110.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#2b5876"),
                        Color.parseColor("#4e4376")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.CRYSTALLINE -> {
                LinearGradientPojo(
                    angle ?: -110.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#00cdac"),
                        Color.parseColor("#8ddad5")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.PARTY_BLISS -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#4481eb"),
                        Color.parseColor("#04befe")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.CONFIDENT_CLOUD -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#dad4ec"),
                        Color.parseColor("#dad4ec"),
                        Color.parseColor("#f3e7e9")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.01f, 1.0f)
                )
            }
            DefaultGradientName.LE_COCKTAIL -> {
                LinearGradientPojo(
                    angle ?: -45.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#874da2"),
                        Color.parseColor("#c43a30")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.RIVER_CITY -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#4481eb"),
                        Color.parseColor("#04befe")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.FROZEN_BERRY -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#e8198b"),
                        Color.parseColor("#c7eafd")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.CHILD_CARE -> {
                LinearGradientPojo(
                    angle ?: -110.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#f794a4"),
                        Color.parseColor("#fdd6bd")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.FLYING_LEMON -> {
                LinearGradientPojo(
                    angle ?: -30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#64b3f4"),
                        Color.parseColor("#c2e59c")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.NEW_RETROWAVE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#3b41c5"),
                        Color.parseColor("#a981bb"),
                        Color.parseColor("#ffc8a9")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.49f, 1.0f)
                )
            }
            DefaultGradientName.HIDDEN_JAGUAR -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#0fd850"),
                        Color.parseColor("#f9f047")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.ABOVE_THE_SKY -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("lightgrey"),
                        Color.parseColor("lightgrey"),
                        Color.parseColor("#e0e0e0"),
                        Color.parseColor("#efefef"),
                        Color.parseColor("#d9d9d9"),
                        Color.parseColor("#bcbcbc")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.01f, 0.26f, 0.48f, 0.75f, 1.0f)
                )
            }
            DefaultGradientName.NEGA -> {
                LinearGradientPojo(
                    angle ?: -45.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ee9ca7"),
                        Color.parseColor("#ffdde1")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.DENSE_WATER -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#3ab5b0"),
                        Color.parseColor("#3d99be"),
                        Color.parseColor("#56317a")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.31f, 1.0f)
                )
            }
            DefaultGradientName.SEASHORE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#209cff"),
                        Color.parseColor("#68e0cf")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.MARBLE_WALL -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#bdc2e8"),
                        Color.parseColor("#bdc2e8"),
                        Color.parseColor("#e6dee9")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.01f, 1.0f)
                )
            }
            DefaultGradientName.CHEERFUL_CARAMEL -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#e6b980"),
                        Color.parseColor("#eacda3")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.NIGHT_SKY -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#1e3c72"),
                        Color.parseColor("#1e3c72"),
                        Color.parseColor("#2a5298")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.01f, 1.0f)
                )
            }
            DefaultGradientName.MAGIC_LAKE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#d5dee7"),
                        Color.parseColor("#ffafbd"),
                        Color.parseColor("#c9ffbf")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.0f, 1.0f)
                )
            }
            DefaultGradientName.YOUNG_GRASS -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#9be15d"),
                        Color.parseColor("#00e3ae")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.COLORFUL_PEACH -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ed6ea0"),
                        Color.parseColor("#ec8c69")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.GENTLE_CARE -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ffc3a0"),
                        Color.parseColor("#ffafbd")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.PLUM_BATH -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#cc208e"),
                        Color.parseColor("#6713d2")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.HAPPY_UNICORN -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#b3ffab"),
                        Color.parseColor("#12fff7")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.AFRICAN_FIELD -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#65bd60"),
                        Color.parseColor("#5ac1a8"),
                        Color.parseColor("#3ec6ed"),
                        Color.parseColor("#b7ddb7"),
                        Color.parseColor("#fef381")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.25f, 0.5f, 0.75f, 1.0f)
                )
            }
            DefaultGradientName.SOLID_STONE -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#243949"),
                        Color.parseColor("#517fa4")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.ORANGE_JUICE -> {
                LinearGradientPojo(
                    angle ?: -110.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#fc6076"),
                        Color.parseColor("#ff9a44")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.GLASS_WATER -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#dfe9f3"),
                        Color.parseColor("white")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.NORTH_MIRACLE -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#00dbde"),
                        Color.parseColor("#fc00ff")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.FRUIT_BLEND -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#f9d423"),
                        Color.parseColor("#ff4e50")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.MILLENNIUM_PINE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#50cc7f"),
                        Color.parseColor("#f5d100")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.HIGH_FLIGHT -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#0acffe"),
                        Color.parseColor("#495aff")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.MOLE_HALL -> {
                LinearGradientPojo(
                    angle ?: -110.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#616161"),
                        Color.parseColor("#9bc5c3")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SPACE_SHIFT -> {
                LinearGradientPojo(
                    angle ?: -30.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#3d3393"),
                        Color.parseColor("#2b76b9"),
                        Color.parseColor("#2cacd1"),
                        Color.parseColor("#35eb93")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.37f, 0.65f, 1.0f)
                )
            }
            DefaultGradientName.FOREST_INEI -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#df89b5"),
                        Color.parseColor("#bfd9fe")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.ROYAL_GARDEN -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ed6ea0"),
                        Color.parseColor("#ec8c69")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.RICH_METAL -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#d7d2cc"),
                        Color.parseColor("#304352")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.JUICY_CAKE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#e14fad"),
                        Color.parseColor("#f9d423")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SMART_INDIGO -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#b224ef"),
                        Color.parseColor("#7579ff")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SAND_STRIKE -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#c1c161"),
                        Color.parseColor("#c1c161"),
                        Color.parseColor("#d4d4b1")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.0f, 1.0f)
                )
            }
            DefaultGradientName.NORSE_BEAUTY -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#ec77ab"),
                        Color.parseColor("#7873f5")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.AQUA_GUIDANCE -> {
                LinearGradientPojo(
                    angle ?: -90.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#007adf"),
                        Color.parseColor("#00ecbc")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SUN_VEGGIE -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#20E2D7"),
                        Color.parseColor("#F9FEA5")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SEA_LORD -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#2CD8D5"),
                        Color.parseColor("#C5C1FF"),
                        Color.parseColor("#FFBAC3")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.56f, 1.0f)
                )
            }
            DefaultGradientName.BLACK_SEA -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#2CD8D5"),
                        Color.parseColor("#6B8DD6"),
                        Color.parseColor("#8E37D7")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            DefaultGradientName.GRASS_SHAMPOO -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#DFFFCD"),
                        Color.parseColor("#90F9C4"),
                        Color.parseColor("#39F3BB")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            DefaultGradientName.LANDING_AIRCRAFT -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#5D9FFF"),
                        Color.parseColor("#B8DCFF"),
                        Color.parseColor("#6BBBFF")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            DefaultGradientName.WITCH_DANCE -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#A8BFFF"),
                        Color.parseColor("#884D80")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SLEEPLESS_NIGHT -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#5271C4"),
                        Color.parseColor("#B19FFF"),
                        Color.parseColor("#ECA1FE")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            DefaultGradientName.ANGEL_CARE -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#FFE29F"),
                        Color.parseColor("#FFA99F"),
                        Color.parseColor("#FF719A")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            DefaultGradientName.CRYSTAL_RIVER -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#22E1FF"),
                        Color.parseColor("#1D8FE1"),
                        Color.parseColor("#625EB1")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            DefaultGradientName.SOFT_LIPSTICK -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#B6CEE8"),
                        Color.parseColor("#F578DC")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.SALT_MOUNTAIN -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#FFFEFF"),
                        Color.parseColor("#D7FFFE")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.PERFECT_WHITE -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#E3FDF5"),
                        Color.parseColor("#FFE6FA")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.FRESH_OASIS -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#7DE2FC"),
                        Color.parseColor("#B9B6E5")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.STRICT_NOVEMBER -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#CBBACC"),
                        Color.parseColor("#2580B3")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.MORNING_SALAD -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#B7F8DB"),
                        Color.parseColor("#50A7C2")
                    ),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
            DefaultGradientName.DEEP_RELIEF -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#7085B6"),
                        Color.parseColor("#87A7D9"),
                        Color.parseColor("#DEF3F8")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.5f, 1.0f)
                )
            }
            DefaultGradientName.SEA_STRIKE -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#77FFD2"),
                        Color.parseColor("#6297DB"),
                        Color.parseColor("#1EECFF")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            DefaultGradientName.NIGHT_CALL -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#AC32E4"),
                        Color.parseColor("#7918F2"),
                        Color.parseColor("#4801FF")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            DefaultGradientName.SUPREME_SKY -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#D4FFEC"),
                        Color.parseColor("#57F2CC"),
                        Color.parseColor("#4596FB")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            DefaultGradientName.LIGHT_BLUE -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#9EFBD3"),
                        Color.parseColor("#57E9F2"),
                        Color.parseColor("#45D4FB")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            DefaultGradientName.MIND_CRAWL -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#473B7B"),
                        Color.parseColor("#3584A7"),
                        Color.parseColor("#30D2BE")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.51f, 1.0f)
                )
            }
            DefaultGradientName.LILY_MEADOW -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#65379B"),
                        Color.parseColor("#886AEA"),
                        Color.parseColor("#6457C6")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.53f, 1.0f)
                )
            }
            DefaultGradientName.SUGAR_LOLLIPOP -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#A445B2"),
                        Color.parseColor("#D41872"),
                        Color.parseColor("#FF0066")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.52f, 1.0f)
                )
            }
            DefaultGradientName.SWEET_DESSERT -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#7742B2"),
                        Color.parseColor("#F180FF"),
                        Color.parseColor("#FD8BD9")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.52f, 1.0f)
                )
            }
            DefaultGradientName.MAGIC_RAY -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#FF3CAC"),
                        Color.parseColor("#562B7C"),
                        Color.parseColor("#2B86C5")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.52f, 1.0f)
                )
            }
            DefaultGradientName.TEEN_PARTY -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#FF057C"),
                        Color.parseColor("#8D0B93"),
                        Color.parseColor("#321575")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.5f, 1.0f)
                )
            }
            DefaultGradientName.FROZEN_HEAT -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#FF057C"),
                        Color.parseColor("#7C64D5"),
                        Color.parseColor("#4CC3FF")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            DefaultGradientName.GAGARIN_VIEW -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#69EACB"),
                        Color.parseColor("#EACCF8"),
                        Color.parseColor("#6654F1")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            DefaultGradientName.FABLED_SUNSET -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#231557"),
                        Color.parseColor("#44107A"),
                        Color.parseColor("#FF1361"),
                        Color.parseColor("#FFF800")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.29f, 0.67f, 1.0f)
                )
            }
            DefaultGradientName.PERFECT_BLUE -> {
                LinearGradientPojo(
                    angle ?: -315.0,
                    colors ?: intArrayOf(
                        Color.parseColor("#3D4E81"),
                        Color.parseColor("#5753C9"),
                        Color.parseColor("#6E7FF3")
                    ),
                    positions ?: floatArrayOf(0.0f, 0.48f, 1.0f)
                )
            }
            else -> {
                LinearGradientPojo(
                    angle ?: 0.0,
                    colors ?: intArrayOf(color ?: Color.TRANSPARENT, color ?: Color.TRANSPARENT),
                    positions ?: floatArrayOf(0.0f, 1.0f)
                )
            }
        }
    }
}
