package com.kotlinlibrary.utils.ktx

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

val Context.isRtl :Boolean
    get() = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL
    } else {
        false
    }

val Context.isLtr :Boolean
    get() = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_LTR
    } else {
        true
    }

fun Context.isLandscape() :Boolean
    = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun Context.isPortrait() :Boolean
    = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

fun Context.pxToDp(pixels :Int) :Int =
    (pixels * resources.displayMetrics.density).toInt()

fun Context.pxToSp(pixels :Int) :Int =
    (pixels * resources.displayMetrics.scaledDensity).toInt()

fun Context.isPackageInstalled(packageName :String) :Boolean = try {
    packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    true
} catch(e :PackageManager.NameNotFoundException) {
    false
}

fun Context.hideKeyboard(window : Window, view :View?) {
    if(view?.windowToken != null)
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
}

fun Context.isShowKeyboard() :Boolean {
    return inputMethodManager?.isAcceptingText ?: false
}

fun Context.toggleKeyboard() {
    if(inputMethodManager?.isActive == true)
        inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
}
