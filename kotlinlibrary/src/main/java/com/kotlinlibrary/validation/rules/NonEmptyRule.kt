package com.kotlinlibrary.validation.rules

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

    override fun setError(msg: ErrorMessage?) {
        errorMsg = msg
    }
}