package com.kotlinlibrary.validation.rules

import android.util.Log
import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException

class NonEmptyRule<ErrorMessage>(
    var errorMsg: ErrorMessage? = null,
    clazz: Class<ErrorMessage>
) : BaseRule<ErrorMessage>(clazz) {
    override fun validate(text: String): Boolean = text.isNotEmpty()

    override fun getErrorMessage(): ErrorMessage? {
        return when {
            errorMsg != null -> errorMsg
            typed(kotlin.String::class.java, java.lang.String::class.java) -> "Can't be empty!" as ErrorMessage
            typed(kotlin.Int::class.java, java.lang.Integer::class.java) -> R.string.vald_can_not_be_empty as ErrorMessage
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