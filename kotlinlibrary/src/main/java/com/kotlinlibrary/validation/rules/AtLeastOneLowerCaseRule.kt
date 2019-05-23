package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException
import com.kotlinlibrary.validation.ValidatedObservableField

class AtLeastOneLowerCaseRule<ErrorMessage>(
    var errorMsg: ErrorMessage? = null
) : BaseRule<ErrorMessage> {
    override fun validate(text: String): Boolean =
        ValidatedObservableField<ErrorMessage>(text).regex("^(?=.*[a-z]).+\$").check()

    override fun getErrorMessage(): ErrorMessage {
        return when {
            errorMsg != null -> errorMsg!!
            errorMsg is String -> "At least one letter should be in lower case." as ErrorMessage
            errorMsg is Int -> R.string.vald_at_least_one_lower_case as ErrorMessage
            else -> throw MismatchErrorTypeException()
        }
    }

    override fun setError(msg: ErrorMessage) {
        errorMsg = msg
    }
}