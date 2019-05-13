package com.kotlinlibrary.utils.ktx.dialog

import android.widget.Toast
import com.kotlinlibrary.utils.getContextFromSource

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text resource.
 */
fun Any.toast(message: Int): Toast = Toast
        .makeText(getContextFromSource(this), message, Toast.LENGTH_SHORT)
        .apply {
            show()
        }

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text.
 */
fun Any.toast(message: CharSequence): Toast = Toast
        .makeText(getContextFromSource(this), message, Toast.LENGTH_SHORT)
        .apply {
            show()
        }

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text resource.
 */
fun Any.longToast(message: Int): Toast = Toast
        .makeText(getContextFromSource(this), message, Toast.LENGTH_LONG)
        .apply {
            show()
        }

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text.
 */
fun Any.longToast(message: CharSequence): Toast = Toast
        .makeText(getContextFromSource(this), message, Toast.LENGTH_LONG)
        .apply {
            show()
        }