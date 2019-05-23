package com.kotlinlibrary.validation.rules

import android.util.Patterns
import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException

class ValidUrlRule<ErrorMessage>(
    var errorMsg: ErrorMessage? = null
) : BaseRule<ErrorMessage> {
    override fun validate(text: String): Boolean {
        if (text.isEmpty())
            return false
        return Patterns.WEB_URL.matcher(text).matches()
    }

    override fun getErrorMessage(): ErrorMessage {
        return when {
            errorMsg != null -> errorMsg!!
            errorMsg is String -> "Invalid web URL" as ErrorMessage
            errorMsg is Int -> R.string.vald_invalid_web_url as ErrorMessage
            else -> throw MismatchErrorTypeException()
        }
    }

    override fun setError(msg: ErrorMessage) {
        errorMsg = msg
    }
}