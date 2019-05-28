package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException

class MaxLengthRule<ErrorMessage>(
    val maxLength: Int,
    var errorMsg: ErrorMessage? = null
) : BaseRule<ErrorMessage> {
    override fun validate(text: String): Boolean {
        return text.length <= maxLength
    }

    override fun getErrorMessage(): ErrorMessage? {
        return when {
            errorMsg == null -> null
            errorMsg != null -> errorMsg!!
            errorMsg is String -> "Length should be less than or equal to $maxLength." as ErrorMessage
            errorMsg is Int -> R.string.vald_length_should_be_less_than_or_equal_to_max_length as ErrorMessage
            else -> throw MismatchErrorTypeException()
        }
    }

    override fun setError(msg: ErrorMessage?) {
        errorMsg = msg
    }
}