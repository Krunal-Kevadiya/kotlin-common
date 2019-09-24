package com.kotlinlibrary.utils.ktx

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.net.HttpURLConnection
import java.net.URL
import java.util.UUID

inline fun <R> Bitmap.use(block :(Bitmap) -> R) :R {
    try {
        return block(this)
    } finally {
        if(!isRecycled) {
            recycle()
        }
    }
}

fun Bitmap.base64() :String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun Bitmap.resize(newWidth :Int, newHeight :Int) :Bitmap {
    val width = this.width
    val height = this.height
    val scaleWidth = newWidth.toFloat() / width
    val scaleHeight = newHeight.toFloat() / height
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)
    val resizedBitmap = Bitmap.createBitmap(
        this, 0, 0, width, height, matrix, false)
    resizedBitmap.recycle()
    return resizedBitmap
}

fun Bitmap.toRoundCorner(radius :Float) :Bitmap? {
    val width = this.width
    val height = this.height
    val bitmap = Bitmap.createBitmap(width, height, this.config)
    val paint = Paint()
    val canvas = Canvas(bitmap)
    val rect = Rect(0, 0, width, height)

    paint.isAntiAlias = true
    canvas.drawRoundRect(RectF(rect), radius, radius, paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(this, rect, rect, paint)

    this.recycle()
    return bitmap
}

fun Context.saveBitmapToFile(bitmap :Bitmap) :File? {
    val file = getOutputMediaFile()
    file.outputStream().use {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
    }
    return file
}

fun File.saveBitmapToFile(bitmap :Bitmap) :File? {
    this.outputStream().use {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
    }
    return this
}

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
private fun Context.getOutputMediaFile() :File {
    val picName = UUID.randomUUID().toString().replace("-".toRegex(), "") + ".jpg"
    val folder = this.getExternalFilesDir(null)
    if(folder != null && !folder.isDirectory) {
        folder.mkdirs()
    }

    return File(folder, picName)
}

fun Bitmap.toDrawable(context: Context): Drawable {
    return BitmapDrawable(context.resources, this)
}

infix fun Bitmap.rotate(degree: Int): Bitmap {
    val w = width
    val h = height

    val mtx = Matrix()
    mtx.postRotate(degree.toFloat())

    return Bitmap.createBitmap(this, 0, 0, w, h, mtx, true)
}

fun Bitmap.toUriJpeg(context: Context, title: String, description: String): Uri {
    val bytes = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(context.contentResolver, this, title, description)
    return Uri.parse(path)
}

fun Bitmap.toUriPng(context: Context, title: String, description: String): Uri {
    val bytes = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(context.contentResolver, this, title, description)
    return Uri.parse(path)
}

fun Bitmap.toUriWebp(context: Context, title: String, description: String): Uri {
    val bytes = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.WEBP, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(context.contentResolver, this, title, description)
    return Uri.parse(path)
}

fun Bitmap.makeCircle(): Bitmap? {
    val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(output)

    val color = -0xbdbdbe
    val paint = Paint()
    val rect = Rect(0, 0, width, height)

    paint.isAntiAlias = true
    canvas.drawARGB(0, 0, 0, 0)
    paint.color = color

    canvas.drawCircle(width.div(2f), height.div(2f), width.div(2f), paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(this, rect, rect, paint)

    return output
}

@Throws(FileNotFoundException::class)
fun Uri.toBitmap(context: Context): Bitmap {
    return MediaStore.Images.Media.getBitmap(context.contentResolver, this)
}

fun Context.requestMediaScanner(url :String) {
    val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
    val contentUri = Uri.fromFile(File(url))
    mediaScanIntent.data = contentUri
    this.sendBroadcast(mediaScanIntent)
}

fun downloadBitmap(imageUrl :String) :Bitmap? {
    var bitmap :Bitmap? = null
    val url = URL(imageUrl)
    val conn = url.openConnection() as HttpURLConnection

    if(conn.responseCode == HttpURLConnection.HTTP_OK) {
        bitmap = conn.inputStream.outAsBitmap()
    }
    conn.disconnect()

    return bitmap
}

fun Bitmap.resize(
    width :Int,
    height :Int,
    mode : ResizeMode = ResizeMode.AUTOMATIC,
    isExcludeAlpha :Boolean = false
) :Bitmap {
    var mWidth = width
    var mHeight = height
    var mMode = mode
    val sourceWidth = this.width
    val sourceHeight = this.height

    if(mode == ResizeMode.AUTOMATIC) {
        mMode = calculateResizeMode(sourceWidth, sourceHeight)
    }

    if(mMode == ResizeMode.FIT_TO_WIDTH) {
        mHeight = calculateHeight(sourceWidth, sourceHeight, width)
    } else if(mMode == ResizeMode.FIT_TO_HEIGHT) {
        mWidth = calculateWidth(sourceWidth, sourceHeight, height)
    }
    val config = if(isExcludeAlpha) Bitmap.Config.RGB_565 else Bitmap.Config.ARGB_8888
    return Bitmap.createScaledBitmap(this, mWidth, mHeight, true).copy(config, true)
}

private fun calculateResizeMode(width :Int, height :Int) : ResizeMode =
    if(ImageOrientation.getOrientation(width, height) === ImageOrientation.LANDSCAPE) {
        ResizeMode.FIT_TO_WIDTH
    } else {
        ResizeMode.FIT_TO_HEIGHT
    }

private fun calculateWidth(originalWidth :Int, originalHeight :Int, height :Int) :Int =
    Math.ceil(originalWidth / (originalHeight.toDouble() / height)).toInt()

private fun calculateHeight(originalWidth :Int, originalHeight :Int, width :Int) :Int =
    Math.ceil(originalHeight / (originalWidth.toDouble() / width)).toInt()

enum class ResizeMode {
    AUTOMATIC, FIT_TO_WIDTH, FIT_TO_HEIGHT, FIT_EXACT
}

private enum class ImageOrientation {
    PORTRAIT, LANDSCAPE;

    companion object {
        fun getOrientation(width :Int, height :Int) : ImageOrientation =
            if(width >= height) LANDSCAPE else PORTRAIT
    }
}