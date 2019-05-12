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

/**
 * Converts px to dp
 * @receiver Context
 * @param px value in pixels that needs to be converted to dp
 * @return dp value
 */
fun Context.pxToDp(px: Float) = px / this.resources.displayMetrics.density

/**
 * Converts dp to px
 * @receiver Context
 * @param dp value in dp that needs to be converted to px
 * @return px value
 */
fun Context.dpToPx(dp: Float) = (dp * this.resources.displayMetrics.density).toInt()

/**
 * Converts sp to px
 * @receiver Context
 * @param sp value in sp that needs to be converted to px
 * @return px value
 */
fun Context.spToPx(sp: Float) = (sp * this.resources.displayMetrics.scaledDensity).toInt()

/**
 * Converts px to sp
 * @receiver Context
 * @param px value in pixels that needs to be converted to sp
 * @return sp value
 */
fun Context.pxToSp(px: Float) = px / this.resources.displayMetrics.scaledDensity

fun Context.isPackageInstalled(packageName :String) :Boolean = try {
    packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    true
} catch(e :PackageManager.NameNotFoundException) {
    logs(e)
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
