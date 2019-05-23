package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException
import com.kotlinlibrary.validation.ValidatedObservableField

class OnlyNumbersRule<ErrorMessage>(
    var errorMsg: ErrorMessage? = null
) : BaseRule<ErrorMessage> {
    override fun validate(text: String): Boolean =
        ValidatedObservableField<ErrorMessage>(text).regex("\\d+").check()

    override fun getErrorMessage(): ErrorMessage {
        return when {
            errorMsg != null -> errorMsg!!
            errorMsg is String -> "Should not contain any alphabet characters!" as ErrorMessage
            errorMsg is Int -> R.string.vald_should_not_contain_any_alphabet_characters as ErrorMessage
            else -> throw MismatchErrorTypeException()
        }
    }

    override fun setError(msg: ErrorMessage) {
        errorMsg = msg
    }
}