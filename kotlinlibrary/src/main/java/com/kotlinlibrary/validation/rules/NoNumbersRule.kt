package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException

class NoNumbersRule<ErrorMessage>(
    var errorMsg: ErrorMessage? = null,
    clazz: Class<ErrorMessage>
) : BaseRule<ErrorMessage>(clazz) {
    override fun validate(text: String): Boolean = !text.matches(Regex(".*\\d.*"))

    override fun getErrorMessage(): ErrorMessage? {
        return when {
            errorMsg != null -> errorMsg
            typed(kotlin.String::class.java, java.lang.String::class.java) -> "Should not contain any numbers!" as ErrorMessage
            typed(kotlin.Int::class.java, java.lang.Integer::class.java) -> R.string.vald_should_not_contain_any_numbers as ErrorMessage
            else -> throw MismatchErrorTypeException()
        }
    }

    override fun setError(msg: ErrorMessage?) {
        errorMsg = msg
    }
}