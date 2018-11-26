package com.kotlinlibrary.snackbar.util

import android.app.Activity
import android.graphics.Typeface
import androidx.annotation.IntDef
import com.kotlinlibrary.snackbar.Snackbar
import com.kotlinlibrary.R

object SnackBatType {
    const val INFO = 1
    const val HELP = 2
    const val ERROR = 3
    const val WARNING = 4
    const val SUCCESS = 5
    const val DEFAULT = 6
}

@IntDef(
    SnackBatType.INFO,
    SnackBatType.HELP,
    SnackBatType.ERROR,
    SnackBatType.WARNING,
    SnackBatType.SUCCESS,
    SnackBatType.DEFAULT
)
annotation class SnackBatTypes

fun Activity.snackBarMessage(
    @SnackBatTypes types: Int,
    msg: String,
    font: Typeface? = null,
    title: String = "",
    time: Long = 4000,
    fontSize: Float = 18f,
    gravity: Snackbar.Gravity = Snackbar.Gravity.TOP
): Snackbar.Builder {
    val snackbar =  Snackbar.Builder(this)
        .gravity(gravity)
        .duration(time)
        .title(title)
        .message(msg)
        .messageSizeInSp(fontSize)
        .backgroundColorRes(
            when (types) {
                SnackBatType.INFO -> R.color.color_type_info
                SnackBatType.HELP -> R.color.color_type_help
                SnackBatType.ERROR -> R.color.color_type_wrong
                SnackBatType.WARNING -> R.color.color_type_warning
                SnackBatType.SUCCESS -> R.color.color_type_success
                SnackBatType.DEFAULT -> R.color.color_type_default
                else -> R.color.modal
            }

        )
        .overlayBlockable()
        font?.let { _font ->
            snackbar.messageTypeface(_font)
        }
    return snackbar
}