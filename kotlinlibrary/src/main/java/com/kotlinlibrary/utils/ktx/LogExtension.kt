package com.kotlinlibrary.utils.ktx

import android.util.Log
import androidx.annotation.IntRange

fun Any.logs(
    message: Any,
    @IntRange(from=1, to=Log.ASSERT.toLong()) type: Int = 1
) {
    when (type) {
        Log.ASSERT -> Log.wtf(this.javaClass.simpleName, "$message")
        Log.DEBUG -> Log.d(this.javaClass.simpleName, "$message")
        Log.ERROR -> Log.e(this.javaClass.simpleName, "$message")
        Log.INFO -> Log.i(this.javaClass.simpleName, "$message")
        Log.VERBOSE -> Log.v(this.javaClass.simpleName, "$message")
        Log.WARN -> Log.w(this.javaClass.simpleName, "$message")
        else -> Log.wtf(this.javaClass.simpleName, "$message")
    }
}