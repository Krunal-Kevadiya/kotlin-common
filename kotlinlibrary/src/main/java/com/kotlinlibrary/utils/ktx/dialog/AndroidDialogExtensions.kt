package com.kotlinlibrary.utils.ktx.dialog

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import com.kotlinlibrary.utils.getContextFromSource

fun Any.alert(
    message: CharSequence,
    title: CharSequence? = null,
    init: (AlertBuilder<DialogInterface>.() -> Unit)? = null
): AlertBuilder<AlertDialog> {
    return AndroidAlertBuilder(getContextFromSource(this)).apply {
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
    return AndroidAlertBuilder(getContextFromSource(this)).apply {
        if (titleResource != null) {
            this.titleResource = titleResource
        }
        this.messageResource = messageResource
        if (init != null) init()
    }
}

fun Any.alert(
    init: AlertBuilder<DialogInterface>.() -> Unit
): AlertBuilder<DialogInterface> = AndroidAlertBuilder(getContextFromSource(this)).apply { init() }

@Suppress("DEPRECATION")
fun Any.progressDialog(
    message: Int? = null,
    title: Int? = null,
    init: (ProgressDialog.() -> Unit)? = null
): ProgressDialog {
    val context = getContextFromSource(this)
    return progressDialog(false, message?.let { context.getString(it) }, title?.let { context.getString(it) }, init)
}

@Suppress("DEPRECATION")
fun Any.indeterminateProgressDialog(
    message: Int? = null,
    title: Int? = null,
    init: (ProgressDialog.() -> Unit)? = null
): ProgressDialog {
    val context = getContextFromSource(this)
    return progressDialog(true, message?.let { context.getString(it) }, title?.let { context.getString(it) }, init)
}

@Suppress("DEPRECATION")
fun Any.progressDialog(
    message: CharSequence? = null,
    title: CharSequence? = null,
    init: (ProgressDialog.() -> Unit)? = null
) = progressDialog(false, message, title, init)

@Suppress("DEPRECATION")
fun Any.indeterminateProgressDialog(
    message: CharSequence? = null,
    title: CharSequence? = null,
    init: (ProgressDialog.() -> Unit)? = null
) = progressDialog(true, message, title, init)


@Suppress("DEPRECATION")
private fun Any.progressDialog(
    indeterminate: Boolean,
    message: CharSequence? = null,
    title: CharSequence? = null,
    init: (ProgressDialog.() -> Unit)? = null
) = ProgressDialog(getContextFromSource(this)).apply {
    isIndeterminate = indeterminate
    if (!indeterminate) setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
    if (message != null) setMessage(message)
    if (title != null) setTitle(title)
    if (init != null) init()
    show()
}
