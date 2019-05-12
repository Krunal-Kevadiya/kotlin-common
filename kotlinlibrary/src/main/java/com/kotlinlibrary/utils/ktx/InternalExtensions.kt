package com.kotlinlibrary.utils.ktx

import android.util.Log
import java.text.DecimalFormat

inline fun <T, R> T.tryCatch(block :(T) -> R) :R {
    try {
        return block(this)
    } catch(e :Exception) {
        Log.e("tag", "I/O Exception", e)
        throw e
    }
}

@Suppress("unused", "UNCHECKED_CAST")
internal inline fun <R> getValue(block :() -> R, def :Any?) :R =
    try {
        block()
    } catch(e :Exception) {
        def as R
    }

@Suppress("unused", "UNCHECKED_CAST")
internal inline fun <T, R> T.convert(block :(T) -> R, def :Any?) :R =
    try {
        block(this)
    } catch(e :Exception) {
        def as R
    }

@Suppress("unused", "UNCHECKED_CAST")
internal inline fun <T, R> T.convertAcceptNull(block :(T) -> R, def :Any?) :R? =
    try {
        block(this)
    } catch(e :Exception) {
        def as R
    }


fun toNumFormat(num :String) :String {
    val df = DecimalFormat("#,###")
    return df.format(Integer.parseInt(num))
}

inline fun <reified T> Any.ifInstance(block: T.() -> Unit) {
    if (this is T) block(this)
}
