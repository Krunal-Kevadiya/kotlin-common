package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException

class TextEqualToRule<ErrorMessage>(
    val target: String,
    var errorMsg: ErrorMessage? = null,
    clazz: Class<ErrorMessage>
) : BaseRule<ErrorMessage>(clazz) {
    override fun validate(text: String): Boolean {
        if (text.isEmpty())
            return false
        return text == target
    }

    override fun getErrorMessage(): ErrorMessage? {
        return when {
            errorMsg != null -> errorMsg
            typed(kotlin.String::class.java, java.lang.String::class.java) -> "Should be equal to $target" as ErrorMessage
            typed(kotlin.Int::class.java, java.lang.Integer::class.java) -> R.string.vald_should_be_equal_to_target as ErrorMessage
            else -> throw MismatchErrorTypeException()
        }
    }

    override fun setError(msg: ErrorMessage?) {
        errorMsg = msg
    }
}