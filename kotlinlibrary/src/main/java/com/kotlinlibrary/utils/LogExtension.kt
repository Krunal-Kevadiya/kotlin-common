package com.kotlinlibrary.utils

import android.util.Log
import androidx.annotation.IntDef

object LogType {
    const val UNKNOWN = 1
    const val ASSERT = 7
    const val DEBUG = 3
    const val ERROR = 6
    const val INFO = 4
    const val VERBOSE = 2
    const val WARN = 5
}

@IntDef(LogType.ASSERT, LogType.DEBUG, LogType.ERROR, LogType.INFO, LogType.VERBOSE, LogType.WARN, LogType.UNKNOWN)
annotation class LogTypes

fun Any.logs(message: Any, @LogTypes type: Int) {
    when (type) {
        LogType.ASSERT -> Log.wtf(this.javaClass.simpleName, "$message")
        LogType.DEBUG -> Log.d(this.javaClass.simpleName, "$message")
        LogType.ERROR -> Log.e(this.javaClass.simpleName, "$message")
        LogType.INFO -> Log.i(this.javaClass.simpleName, "$message")
        LogType.VERBOSE -> Log.v(this.javaClass.simpleName, "$message")
        LogType.WARN -> Log.w(this.javaClass.simpleName, "$message")
        LogType.UNKNOWN -> Log.wtf(this.javaClass.simpleName, "$message")
        else -> Log.wtf(this.javaClass.simpleName, "$message")
    }
}