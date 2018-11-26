package com.kotlinlibrary.validation.rules

import android.util.Patterns

class ValidUrlRule(var errorMsg: String = "Invalid web URL") : BaseRule {
    override fun validate(text: String): Boolean {
        if (text.isEmpty())
            return false

        return Patterns.WEB_URL.matcher(text).matches()
    }

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}