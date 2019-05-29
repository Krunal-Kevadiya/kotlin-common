package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException

class AtLeastOneNumberCaseRule<ErrorMessage>(
    var errorMsg: ErrorMessage? = null,
    clazz: Class<ErrorMessage>
) : BaseRule<ErrorMessage>(clazz) {
    override fun validate(text: String): Boolean = text.matches(Regex(".*\\d.*"))

    override fun getErrorMessage(): ErrorMessage? {
        return when {
            errorMsg != null -> errorMsg
            typed(kotlin.String::class.java, java.lang.String::class.java) -> "At least one letter should be a number." as ErrorMessage
            typed(kotlin.Int::class.java, java.lang.Integer::class.java) -> R.string.vald_at_least_one_number_case as ErrorMessage
            else -> throw MismatchErrorTypeException()
        }
    }

    override fun setError(msg: ErrorMessage?) {
        errorMsg = msg
    }
}