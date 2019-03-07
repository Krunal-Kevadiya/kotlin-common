package com.kotlinlibrary.utils.imagepick

import android.annotation.TargetApi
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.exifinterface.media.ExifInterface
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset

fun Context.requestMediaScanner(url: String) {
    val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
    val contentUri = Uri.fromFile(File(url))
    mediaScanIntent.data = contentUri
    this.sendBroadcast(mediaScanIntent)
}

fun String.toFile() = File(this)

fun saveFile(fullPath: String, content: String): File = fullPath.toFile().apply {
    writeText(content, Charset.defaultCharset())
}

fun File.readFile(): String = this.readText(Charset.defaultCharset())

@TargetApi(Build.VERSION_CODES.KITKAT)
infix fun Uri.getRealPath(context: Context): String? {
    val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

    if (isKitKat && DocumentsContract.isDocumentUri(context, this)) {
        return checkAuthority(context)
    }

    if ("content".equals(this.scheme, ignoreCase = true)) {
        if ("com.google.android.apps.photos.content" == this.authority)
            return this.lastPathSegment

        return context.getDataColumns(this, null, null)
    }

    if ("file".equals(this.scheme, ignoreCase = true)) {
        return this.path
    }

    return this.path
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun Uri.checkAuthority(context: Context): String? {
    val docId = DocumentsContract.getDocumentId(this)
    val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

    if ("com.android.externalstorage.documents" == this.authority) {
        val type = split[0]

        if ("primary".equals(type, ignoreCase = true))
            return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
    } else if ("com.android.providers.downloads.documents" == this.authority) {
        return context.getDataColumns(
            ContentUris.withAppendedId(
                Uri.parse("content://downloads/public_downloads"),
                docId.toLong()
            ), null, null
        )
    } else if ("com.android.providers.media.documents" == this.authority) {
        val contentUri = when (split[0]) {
            "image" -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            "video" -> MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            "audio" -> MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            else -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        return context.getDataColumns(contentUri, "_id=?", arrayOf(split[1]))
    }

    return this.path
}

// get Path
@TargetApi(Build.VERSION_CODES.KITKAT)
infix fun Uri.getRealPathFromURI(context: Context): String? {
    val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

    if (isKitKat && DocumentsContract.isDocumentUri(context, this)) {
        if (isExternalStorageDocument(this)) {
            val docId = DocumentsContract.getDocumentId(this)
            val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val type = split[0]

            if ("primary".equals(type, ignoreCase = true)) {
                return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
            }
        } else if (isDownloadsDocument(this)) {
            val id = DocumentsContract.getDocumentId(this)
            val contentUri = ContentUris.withAppendedId(
                Uri.parse("content://downloads/public_downloads"), id.toLong()
            )

            return context.getDataColumns(contentUri, null, null)
        } else if (isMediaDocument(this)) {
            val docId = DocumentsContract.getDocumentId(this)
            val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val type = split[0]
            var contentUri: Uri? = null
            when (type) {
                "image" -> contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                "video" -> contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                "audio" -> contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }
            val selection = "_id=?"
            val selectionArgs = arrayOf(split[1])

            return context.getDataColumns(contentUri, selection, selectionArgs)
        }
    } else return if ("content".equals(scheme, ignoreCase = true)) {
        if (isGooglePhotosUri(this)) lastPathSegment else context.getDataColumns(this, null, null)
    } else if ("file".equals(scheme, ignoreCase = true)) {
        path
    } else
        context.getRealPathFromURIDB(this)
    return null
}

private fun Context.getRealPathFromURIDB(contentUri: Uri): String {
    val cursor = contentResolver.query(contentUri, null, null, null, null)
    return if (cursor == null) {
        contentUri.path!!
    } else {
        cursor.moveToFirst()
        val index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        val realPath = cursor.getString(index)
        cursor.close()
        realPath
    }
}

private fun Context.getDataColumns(uri: Uri?, selection: String?, selectionArgs: Array<String>?): String? {
    var cursor: Cursor? = null
    val column = "_data"
    val projection = arrayOf(column)

    try {
        uri?.let {
            cursor = contentResolver.query(it, projection, selection, selectionArgs, null)
            cursor?.let {
                it.moveToFirst()
                val columnIndex = it.getColumnIndexOrThrow(column)
                return if (it.isNull(columnIndex)) {
                    null
                } else {
                    it.getString(columnIndex)
                }
            }
        }
    } finally {
        cursor?.close()
    }
    return null
}

private fun isExternalStorageDocument(uri: Uri): Boolean = "com.android.externalstorage.documents" == uri.authority
private fun isDownloadsDocument(uri: Uri): Boolean = "com.android.providers.downloads.documents" == uri.authority
private fun isMediaDocument(uri: Uri): Boolean = "com.android.providers.media.documents" == uri.authority
private fun isGooglePhotosUri(uri: Uri): Boolean = "com.google.android.apps.photos.content" == uri.authority

// Save file
fun ifRequiredThenRotate(bitmap: Bitmap, photoPath: String): String {
    val ei = ExifInterface(photoPath)
    val orientation = ei.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_UNDEFINED
    )

    val rotatedBitmap: Bitmap = when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90f)
        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180f)
        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270f)
        ExifInterface.ORIENTATION_NORMAL -> bitmap
        else -> bitmap
    }

    return saveToInternalStorage(rotatedBitmap, photoPath)
}

fun rotateImage(source: Bitmap, angle: Float): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
}

fun saveToInternalStorage(bitmap: Bitmap, fileName: String): String {
    val myPath = File(fileName)
    if (myPath.exists())
        myPath.delete()
    var fos: FileOutputStream? = null
    try {
        fos = FileOutputStream(myPath)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        fos?.close()
    }
    return myPath.path
}
