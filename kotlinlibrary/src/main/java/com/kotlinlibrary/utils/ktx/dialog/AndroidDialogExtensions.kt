package com.kotlinlibrary.utils.ktx.dialog

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import com.kotlinlibrary.utils.getContextFrom

fun Any.alert(
    message: CharSequence,
    title: CharSequence? = null,
    init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
): AlertBuilder<AlertDialog> {
    return AndroidAlertBuilder(getContextFrom(this)).apply {
        if (title != null) {
            this.title = title
        }
        this.message = message
        if (init != null) init()
    }
}

fun Any.alert(
    messageResource: Int,
    titleResource: Int? = null,
    init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
): AlertBuilder<DialogInterface> {
    return AndroidAlertBuilder(getContextFrom(this)).apply {
        if (titleResource != null) {
            this.titleResource = titleResource
        }
        this.messageResource = messageResource
        if (init != null) init()
    }
}

fun Any.alert(
    init: AlertBuilder<DialogInterface>.() -> Unit
): AlertBuilder<DialogInterface> = AndroidAlertBuilder(getContextFrom(this)).apply { init() }

fun Any.progressDialog(
    message: Int? = null,
    title: Int? = null,
    init: (ProgressDialog.() -> Unit)? = null
): ProgressDialog {
    val context = getContextFrom(this)
    return progressDialog(false, message?.let { context.getString(it) }, title?.let { context.getString(it) }, init)
}

fun Any.indeterminateProgressDialog(
    message: Int? = null,
    title: Int? = null,
    init: (ProgressDialog.() -> Unit)? = null
): ProgressDialog {
    val context = getContextFrom(this)
    return progressDialog(true, message?.let { context.getString(it) }, title?.let { context.getString(it) }, init)
}

fun Any.progressDialog(
    message: CharSequence? = null,
    title: CharSequence? = null,
    init: (ProgressDialog.() -> Unit)? = null
) = progressDialog(false, message, title, init)

fun Any.indeterminateProgressDialog(
    message: CharSequence? = null,
    title: CharSequence? = null,
    init: (ProgressDialog.() -> Unit)? = null
) = progressDialog(true, message, title, init)


private fun Any.progressDialog(
    indeterminate: Boolean,
    message: CharSequence? = null,
    title: CharSequence? = null,
    init: (ProgressDialog.() -> Unit)? = null
) = ProgressDialog(getContextFrom(this)).apply {
    isIndeterminate = indeterminate
    if (!indeterminate) setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
    if (message != null) setMessage(message)
    if (title != null) setTitle(title)
    if (init != null) init()
    show()
}
