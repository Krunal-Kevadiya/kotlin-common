package com.kotlinlibrary.utils.ktx.dialog

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.annotation.StyleRes
import com.kotlinlibrary.utils.getContextFromSource

@JvmOverloads
fun Any.alert(
    @StyleRes theme: Int,
    message: String,
    title: String = "",
    positiveButton: String? = null,
    cancelable: Boolean = true,
    callback: (
        DialogInterface
    ) -> Unit = {}
): AlertDialog.Builder {
    val context = getContextFromSource(this)
    return AlertDialog.Builder(context).apply {
        context.setTheme(theme)
        if (title.isEmpty().not())
            setTitle(title)
        setMessage(message)
        setNegativeButton(context.getString(android.R.string.cancel)) { _, _ -> }
        setPositiveButton(positiveButton ?: context.getString(android.R.string.ok)) { dialog, _ -> callback(dialog) }
        setCancelable(cancelable)
        show()
    }
}

@JvmOverloads
fun <T> Any.selector(
    items: List<T>,
    callback: (DialogInterface, item: T, Int) -> Unit,
    title: String = "",
    cancelable: Boolean = true
): AlertDialog.Builder {
    return AlertDialog.Builder(getContextFromSource(this)).apply {
        if (title.isEmpty().not())
            setTitle(title)
        setItems(Array(items.size) { i -> items[i].toString() }) { dialog, which ->
            callback(dialog, items[which], which)
        }
        setCancelable(cancelable)
        show()
    }
}

@Suppress("unused")
@JvmOverloads
fun Any.confirm(
    message: String,
    callback: DialogInterface.() -> Unit,
    title: String = "",
    positiveButton: String? = null,
    negativeButton: String? = null,
    cancelable: Boolean = true
): AlertDialog.Builder {
    val context = getContextFromSource(this)
    return AlertDialog.Builder(context).apply {
        if (title.isEmpty().not())
            setTitle(title)
        setMessage(message)
        setPositiveButton(positiveButton ?: context.getString(android.R.string.ok)) { dialog, _ -> dialog.callback() }
        setNegativeButton(negativeButton ?: context.getString(android.R.string.no)) { _, _ -> }
        setCancelable(cancelable)
        show()
    }
}

@JvmOverloads
fun Any.progress(message: String, cancelable: Boolean = true, title: String = ""): DialogInterface {
    return ProgressDialog(getContextFromSource(this)).apply {
        setProgressStyle(ProgressDialog.STYLE_SPINNER)
        setMessage(message)
        if (title.isEmpty().not())
            setTitle(title)
        setCancelable(cancelable)
        show()
    }
}
