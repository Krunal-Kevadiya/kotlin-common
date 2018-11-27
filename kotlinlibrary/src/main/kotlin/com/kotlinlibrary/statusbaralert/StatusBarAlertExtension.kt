package com.kotlinlibrary.statusbaralert

import android.app.Activity
import android.graphics.Typeface
import androidx.annotation.ColorRes
import com.kotlinlibrary.R

fun Activity.progressMessage(
    autoHide: Boolean = false,
    showProgress: Boolean = false,
    textAnim: Boolean = false,
    time: Long = 10,
    msg: String,
    font: Typeface? = null,
    @ColorRes alertColor: Int = R.color.color_type_default
): StatusBarAlertView? {
    val statusBarAlert = StatusBarAlert.Builder(this)
        .autoHide(autoHide)
        .showProgress(showProgress)
        .showTextAnimation(textAnim)
        .withDuration(time)
        .withText(msg)
        .withAlertColor(alertColor)
    font?.let { _font ->
        statusBarAlert.withTypeface(_font)
    }
    return statusBarAlert.build()
}
