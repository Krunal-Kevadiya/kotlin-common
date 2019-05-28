package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException

class EndsWithRule<ErrorMessage>(
    val target: String,
    var errorMsg: ErrorMessage? = null
) : BaseRule<ErrorMessage> {
    override fun validate(text: String): Boolean {
        if (text.isEmpty())
            return false
        return text.endsWith(target)
    }

    override fun getErrorMessage(): ErrorMessage? {
        return when {
            errorMsg == null -> null
            errorMsg != null -> errorMsg!!
            errorMsg is String -> "Should end with $target." as ErrorMessage
            errorMsg is Int -> R.string.vald_should_end_with_target as ErrorMessage
            else -> throw MismatchErrorTypeException()
        }
    }

    override fun setError(msg: ErrorMessage?) {
        errorMsg = msg
    }
}