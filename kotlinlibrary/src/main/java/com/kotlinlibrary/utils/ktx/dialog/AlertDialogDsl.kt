package com.kotlinlibrary.utils.ktx.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.view.KeyEvent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlin.DeprecationLevel.ERROR

@SuppressLint("SupportAnnotationUsage")
interface AlertBuilder<out D : DialogInterface> {
    val ctx: Context

    var title: CharSequence
        @Deprecated("Property does not have a getter", level = ERROR) get

    var titleResource: Int
        @Deprecated("Property does not have a getter", level = ERROR) get

    var message: CharSequence
        @Deprecated("Property does not have a getter", level = ERROR) get

    var messageResource: Int
        @Deprecated("Property does not have a getter", level = ERROR) get

    var icon: Drawable
        @Deprecated("Property does not have a getter", level = ERROR) get

    @setparam:DrawableRes
    var iconResource: Int
        @Deprecated("Property does not have a getter", level = ERROR) get

    var isCancelable: Boolean
        @Deprecated("Property does not have a getter", level = ERROR) get

    fun onCancelled(handler: (dialog: DialogInterface) -> Unit)

    fun onKeyPressed(handler: (dialog: DialogInterface, keyCode: Int, e: KeyEvent) -> Boolean)

    fun positiveButton(buttonText: String, onClicked: (dialog: DialogInterface) -> Unit)
    fun positiveButton(@StringRes buttonTextResource: Int, onClicked: (dialog: DialogInterface) -> Unit)

    fun negativeButton(buttonText: String, onClicked: (dialog: DialogInterface) -> Unit)
    fun negativeButton(@StringRes buttonTextResource: Int, onClicked: (dialog: DialogInterface) -> Unit)

    fun neutralPressed(buttonText: String, onClicked: (dialog: DialogInterface) -> Unit)
    fun neutralPressed(@StringRes buttonTextResource: Int, onClicked: (dialog: DialogInterface) -> Unit)

    fun items(items: List<CharSequence>, onItemSelected: (dialog: DialogInterface, index: Int) -> Unit)
    fun <T> items(items: List<T>, onItemSelected: (dialog: DialogInterface, item: T, index: Int) -> Unit)

    fun build(): D
    fun show(): D
}

fun AlertBuilder<*>.okButton(handler: (dialog: DialogInterface) -> Unit) =
    positiveButton(android.R.string.ok, handler)

fun AlertBuilder<*>.cancelButton(handler: (dialog: DialogInterface) -> Unit) =
    negativeButton(android.R.string.cancel, handler)

fun AlertBuilder<*>.yesButton(handler: (dialog: DialogInterface) -> Unit) =
    positiveButton(android.R.string.yes, handler)

fun AlertBuilder<*>.noButton(handler: (dialog: DialogInterface) -> Unit) =
    negativeButton(android.R.string.no, handler)

fun AlertBuilder<*>.customButton(@StringRes buttonLabel: Int, handler: (dialog: DialogInterface) -> Unit) =
    positiveButton(buttonLabel, handler)
