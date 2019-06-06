package com.kotlinlibrary.validation.rules

import android.util.Log
import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException

class NonEmptyRule<ErrorMessage>(
    var errorMsg: ErrorMessage? = null
) : BaseRule<ErrorMessage> {
    override fun validate(text: String): Boolean = text.isNotEmpty()

    override fun getErrorMessage(): ErrorMessage? {
        return when {
            errorMsg != null -> errorMsg!!
            errorMsg is String -> "Can't be empty!" as? ErrorMessage
            errorMsg is Int -> R.string.vald_can_not_be_empty as? ErrorMessage
            errorMsg == null -> null
            else -> throw MismatchErrorTypeException()
        }
    }

    inline fun <reified T> handleList(l: T?) {
        when (T::class) {
            Int::class -> Log.e("ABC", "Int")
            Double::class -> Log.e("ABC", "Double")
            String::class -> Log.e("ABC", "String")
        }
    }

    override fun setError(msg: ErrorMessage?) {
        errorMsg = msg
    }
}