package com.kotlinlibrary.utils.ktx

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.content.ContextCompat
import com.kotlinlibrary.utils.getContextFromSource
import java.io.FileNotFoundException

fun Drawable.toBitmap(): Bitmap? {
    if (this is BitmapDrawable) {
        if (this.bitmap != null) {
            return this.bitmap
        }
    }

    val bitmap: Bitmap = if (this.intrinsicWidth <= 0 || this.intrinsicHeight <= 0) {
        Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Single color bitmap will be created of 1x1 pixel
    } else {
        Bitmap.createBitmap(this.intrinsicWidth, this.intrinsicHeight, Bitmap.Config.ARGB_8888)
    }

    val canvas = Canvas(bitmap)
    this.bounds.set(0, 0, canvas.width, canvas.height)
    this.draw(canvas)
    return bitmap
}

@Throws(FileNotFoundException::class)
fun Uri.toDrawable(context: Context): Drawable {
    val inputStream = context.contentResolver.openInputStream(this)
    return Drawable.createFromStream(inputStream, this.toString())
}

fun Any.getDrawableResId(name: String): Int {
    val context = getContextFromSource(this)
    return context.resources.getIdentifier(name, "drawable", context.packageName)
}

fun Any.getDrawable(name: String):Drawable? {
    val context = getContextFromSource(this)
    val resourceId = context.resources.getIdentifier(name, "drawable", context.packageName)
    return ContextCompat.getDrawable(context, resourceId)
}

fun Any.bitmapToDrawable(bitmap: Bitmap?): Drawable? {
    val resources = getContextFromSource(this).resources
    return BitmapDrawable(resources, bitmap)
}