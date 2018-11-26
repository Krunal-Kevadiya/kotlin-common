package com.kotlinlibrary.utils.imagepick

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.kotlinlibrary.R

fun Context.isGranted(array: Array<String>): Array<String> {
    val notGranted: ArrayList<String> = arrayListOf()
    array.forEach {
        val result = ContextCompat.checkSelfPermission(this, it)
        if (result != PackageManager.PERMISSION_GRANTED)
            notGranted.add(it)
    }
    return notGranted.toTypedArray()
}

fun Activity.isRationale(array: Array<String>): Array<String> {
    val notGranted: ArrayList<String> = arrayListOf()
    array.forEach {
        val result = ActivityCompat.shouldShowRequestPermissionRationale(this, it)
        if (result)
            notGranted.add(it)
    }
    return notGranted.toTypedArray()
}

fun Context.settingIntent(): Intent {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + this.packageName))
    intent.addCategory(Intent.CATEGORY_DEFAULT)
    return intent
}

fun Context.alert(
    message: String,
    title: String = "",
    positiveButton: String? = null,
    cancelable: Boolean = true,
    callback: (DialogInterface, Int) -> Unit
) =
    AlertDialog.Builder(this).apply {
        setTheme(R.style.ThemeDialog_AppTheme)
        if (title.isEmpty().not())
            setTitle(title)
        setMessage(message)
        setNegativeButton(getString(android.R.string.cancel)) { dialog, which -> callback(dialog, which) }
        setPositiveButton(positiveButton
            ?: getString(android.R.string.ok)) { dialog, which -> callback(dialog, which) }
        setCancelable(cancelable)
        show()
    }

fun Fragment.toast(message: CharSequence): Toast = Toast
    .makeText(activity, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }
