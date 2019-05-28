package com.kotlinlibrary.validation.rules

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

    override fun setError(msg: ErrorMessage?) {
        errorMsg = msg
    }
}