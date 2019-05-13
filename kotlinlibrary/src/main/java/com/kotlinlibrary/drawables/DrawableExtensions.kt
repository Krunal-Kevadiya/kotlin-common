package com.kotlinlibrary.drawables

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import com.kotlinlibrary.R
import com.kotlinlibrary.drawables.badge.BadgePosition
import com.kotlinlibrary.drawables.badge.DrawableBadge
import com.kotlinlibrary.utils.getContextFromSource
import java.util.*

fun buildTextDrawable(text: String, width: Int = 500, height: Int = 500, argColor: Int = 0): Bitmap {
    var mColor = argColor
    if (mColor == 0) {
        val rnd = Random()
        mColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bmp)
    canvas.drawColor(mColor)

    val paint = Paint().apply {
        color = Color.WHITE
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        // draw text
        val fontSize = Math.min(width, height) / 2
        textSize = fontSize.toFloat()
    }

    val mText = if (text.length > 2) {
        text.toUpperCase().substring(IntRange(0, 1)).trim()
    } else {
        text.toUpperCase().trim()
    }
    canvas.drawText(
        mText,
        (width / 2).toFloat(),
        (height / 2).toFloat() - (paint.descent() + paint.ascent()) / 2,
        paint)
    return bmp
}

fun Any.buildCounterDrawable(
    count: Int,
    @DrawableRes bgImageId: Int = R.drawable.ic_person,
    @ColorRes bgColor: Int = R.color.color_type_warning,
    @DimenRes badgeSize: Int = R.dimen._16sdp,
    position: BadgePosition = BadgePosition.TOP_RIGHT,
    @ColorRes textColor: Int = android.R.color.white,
    isBoarder:Boolean = false,
    @ColorRes borderColor: Int = android.R.color.black,
    @DimenRes borderSize: Int = R.dimen.default_badge_border_size,
    maxCounter: Int = 99
): Drawable {
    return DrawableBadge.Builder(getContextFromSource(this))
        .drawableResId(bgImageId)
        .badgeColor(bgColor)
        .badgeSize(badgeSize)
        .badgePosition(position)
        .textColor(textColor)
        .showBorder(isBoarder)
        .badgeBorderColor(borderColor)
        .badgeBorderSize(borderSize)
        .maximumCounter(maxCounter)
        .build()
        .get(count)
}