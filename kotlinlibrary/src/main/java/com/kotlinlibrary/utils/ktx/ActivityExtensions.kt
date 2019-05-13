package com.kotlinlibrary.utils.ktx

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.kotlinlibrary.R
import com.kotlinlibrary.snackbar.util.getRootView
import com.kotlinlibrary.utils.getActivityFromSource
import com.kotlinlibrary.utils.getAppCompatActivityFromSource
import com.kotlinlibrary.utils.getContextFromSource

fun Any.setFullScreen() {
    getActivityFromSource(this).window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Any.showToolbar() {
    getActivityFromSource(this).actionBar?.show()
}

fun Any.hideToolbar() {
    getActivityFromSource(this).actionBar?.hide()
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Any.makeTranslucentStatusBar() {
    getActivityFromSource(this).window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Any.makeNormalStatusBar(statusBarColor: Int = -1) {
    val activity = getActivityFromSource(this)
    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    activity.window.decorView.rootView.systemUiVisibility = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        activity.window.statusBarColor = if (statusBarColor == -1) Color.BLACK else statusBarColor
    }
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Any.makeTranslucentNavigationBar() {
    getActivityFromSource(this).window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Any.makeNormalNavigationBar(navigationBarColor: Int = -1) {
    val activity = getActivityFromSource(this)
    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    activity.window.decorView.rootView.systemUiVisibility = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        activity.window.navigationBarColor = if (navigationBarColor == -1) Color.BLACK else navigationBarColor
    }
}

@RequiresApi(Build.VERSION_CODES.M)
fun Any.lightStatusBar(statusBarColor: Int = -1) {
    val activity = getActivityFromSource(this)
    when (activity.window.decorView.rootView.systemUiVisibility) {
        0 -> activity.window.decorView.rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                activity.window.decorView.rootView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR + View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                activity.window.decorView.rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
    activity.window.statusBarColor = if (statusBarColor == -1) Color.WHITE else statusBarColor
}

@RequiresApi(Build.VERSION_CODES.O)
fun Any.lightNavigation(navigationBarColor: Int = -1) {
    val activity = getActivityFromSource(this)
    when (activity.window.decorView.rootView.systemUiVisibility) {
        0 -> activity.window.decorView.rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR -> {
             activity.window.decorView.rootView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR + View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }
    activity.window.navigationBarColor = if (navigationBarColor == -1) Color.WHITE else navigationBarColor
}

fun Any.lockCurrentScreenOrientation() {
    val activity = getActivityFromSource(this)
    activity.requestedOrientation = when (activity.resources.configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        else -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
    }
}

fun Any.unlockScreenOrientation() {
    getActivityFromSource(this).requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
}

/**
 * Shortcut for [InputMethodManager.showSoftInput]
 */
fun Any.showSoftInput(editText: EditText) {
    inputMethodManager?.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
}

/**
 * Shortcut for [InputMethodManager.hideSoftInputFromWindow]
 */
fun Any.hideSoftInput() {
    val activity = getActivityFromSource(this)
    inputMethodManager?.hideSoftInputFromWindow((activity.currentFocus ?: activity.getRootView())?.windowToken, 0)
}

/**
 * Shortcut for [InputMethodManager.toggleSoftInput]
 */
fun Any.toggleSoftInput(
    showFlags: Int = InputMethodManager.SHOW_FORCED,
    hideFlags: Int = 0
) {
    inputMethodManager?.toggleSoftInput(showFlags, hideFlags)
}

/**
 * Shortcut for [android.view.Window.setSoftInputMode] which uses
 * enums instead of raw int flags
 */
fun Any.setSoftInputMode(
    adjustment: SoftInputAdjustment = SoftInputAdjustment.SOFT_INPUT_ADJUST_UNSPECIFIED,
    visibility: SoftInputVisibility = SoftInputVisibility.SOFT_INPUT_STATE_UNSPECIFIED
) {
    getActivityFromSource(this).setSoftInputMode(adjustment.flag, visibility.flag)
}

/**
 * Shortcut for [android.view.Window.setSoftInputMode] which uses
 * [WindowManager.LayoutParams] flags to specify adjustment and visibility
 */
fun Any.setSoftInputMode(
    adjustmentFlag: Int = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING,
    visibilityFlag: Int = WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED
) {
    getActivityFromSource(this).window.setSoftInputMode(adjustmentFlag or visibilityFlag)
}

enum class SoftInputAdjustment(val flag: Int) {
    SOFT_INPUT_ADJUST_UNSPECIFIED(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED),
    SOFT_INPUT_ADJUST_RESIZE(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE),
    SOFT_INPUT_ADJUST_PAN(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
}

enum class SoftInputVisibility(val flag: Int) {
    SOFT_INPUT_STATE_UNSPECIFIED(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED),
    SOFT_INPUT_STATE_UNCHANGED(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED),
    SOFT_INPUT_STATE_HIDDEN(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN),
    SOFT_INPUT_STATE_VISIBLE(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE),
    SOFT_INPUT_STATE_ALWAYS_VISIBLE(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
}

fun Any.circularRevealedAtCenter(view: View) {
    val cx = (view.left + view.right) / 2
    val cy = (view.top + view.bottom) / 2
    val finalRadius = Math.max(view.width, view.height)

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && view.isAttachedToWindow) {
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())
        view.visible()
        view.setBackgroundColor(ContextCompat.getColor(getContextFromSource(this), R.color.color_type_default))
        anim.duration = 550
        anim.start()
    }
}

fun Any.simpleToolbarWithHome(toolbar: Toolbar, title_: String = "", icon_: Int = 0) {
    val activity = getAppCompatActivityFromSource(this)
    activity.setSupportActionBar(toolbar)
    activity.supportActionBar?.run {
        setDisplayHomeAsUpEnabled(true)
        if(icon_ != 0) setHomeAsUpIndicator(icon_)
        title = title_
    }
}

fun Any.applyToolbarMargin(toolbar: Toolbar) {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        toolbar.layoutParams = (toolbar.layoutParams as CollapsingToolbarLayout.LayoutParams).apply {
            topMargin = getStatusBarSize()
        }
    }
}

private fun Any.getStatusBarSize(): Int {
    val context = getContextFromSource(this)
    val idStatusBarHeight = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (idStatusBarHeight > 0) {
        context.resources.getDimensionPixelSize(idStatusBarHeight)
    } else {
        0
    }
}
