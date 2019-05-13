package com.kotlinlibrary.utils.ktx

import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.kotlinlibrary.utils.getContextFromSource

val Any.isRtl :Boolean
    get() = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        getContextFromSource(this).resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL
    } else {
        false
    }

val Any.isLtr :Boolean
    get() = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        getContextFromSource(this).resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_LTR
    } else {
        true
    }

fun Any.isLandscape() :Boolean
    = getContextFromSource(this).resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun Any.isPortrait() :Boolean
    = getContextFromSource(this).resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

/**
 * Converts px to dp
 * @receiver Context
 * @param px value in pixels that needs to be converted to dp
 * @return dp value
 */
fun Any.pxToDp(px: Float) = px / getContextFromSource(this).resources.displayMetrics.density

/**
 * Converts dp to px
 * @receiver Context
 * @param dp value in dp that needs to be converted to px
 * @return px value
 */
fun Any.dpToPx(dp: Float) = (dp * getContextFromSource(this).resources.displayMetrics.density).toInt()

/**
 * Converts sp to px
 * @receiver Context
 * @param sp value in sp that needs to be converted to px
 * @return px value
 */
fun Any.spToPx(sp: Float) = (sp * getContextFromSource(this).resources.displayMetrics.scaledDensity).toInt()

/**
 * Converts px to sp
 * @receiver Context
 * @param px value in pixels that needs to be converted to sp
 * @return sp value
 */
fun Any.pxToSp(px: Float) = px / getContextFromSource(this).resources.displayMetrics.scaledDensity

fun Any.isPackageInstalled(packageName :String) :Boolean = try {
    val context = getContextFromSource(this)
    context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
    true
} catch(e :PackageManager.NameNotFoundException) {
    logs(e)
    false
}

fun Any.hideKeyboard(window : Window, view :View?) {
    if(view?.windowToken != null)
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
}

fun Any.isShowKeyboard() :Boolean {
    return inputMethodManager?.isAcceptingText ?: false
}

fun Any.toggleKeyboard() {
    if(inputMethodManager?.isActive == true)
        inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
}
