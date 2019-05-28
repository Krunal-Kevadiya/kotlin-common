package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException
import com.kotlinlibrary.validation.ValidatedObservableField

class AtLeastOneSpecialCharacterRule<ErrorMessage>(
    var errorMsg: ErrorMessage? = null
) : BaseRule<ErrorMessage> {
    override fun validate(text: String): Boolean {
        if (text.isEmpty())
            return false
        return !ValidatedObservableField<ErrorMessage>(text).regex("[A-Za-z0-9]+").check()
    }

    override fun getErrorMessage(): ErrorMessage? {
        return when {
            errorMsg == null -> null
            errorMsg != null -> errorMsg!!
            errorMsg is String -> "Should contain at least 1 special characters." as ErrorMessage
            errorMsg is Int -> R.string.vald_at_least_one_special_characters as ErrorMessage
            else -> throw MismatchErrorTypeException()
        }
    }

    override fun setError(msg: ErrorMessage?) {
        errorMsg = msg
    }
}