package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException
import com.kotlinlibrary.validation.ValidatedObservableField

class ValidNumberRule<ErrorMessage>(
    var errorMsg: ErrorMessage? = null
) : BaseRule<ErrorMessage> {
    override fun validate(text: String): Boolean {
        if (text.isEmpty())
            return false
        return if (text.startsWith("-")) {
            val txtNum = text.substringAfter("-")
            ValidatedObservableField<ErrorMessage>(txtNum).regex("^[0-9]\\d*(\\.\\d+)?$").check()
        } else {
            ValidatedObservableField<ErrorMessage>(text).regex("^[0-9]\\d*(\\.\\d+)?$").check()
        }
    }

    override fun getErrorMessage(): ErrorMessage? {
        return when {
            errorMsg == null -> null
            errorMsg != null -> errorMsg!!
            errorMsg is String -> "Invalid Number!" as ErrorMessage
            errorMsg is Int -> R.string.vald_invalid_number as ErrorMessage
            else -> throw MismatchErrorTypeException()
        }
    }

    override fun setError(msg: ErrorMessage?) {
        errorMsg = msg
    }
}