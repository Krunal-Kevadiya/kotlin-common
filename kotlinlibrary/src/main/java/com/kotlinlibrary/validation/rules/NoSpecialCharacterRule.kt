package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.validation.ValidatedObservableField

class NoSpecialCharacterRule(var errorMsg: String = "Should not contain any special characters") : BaseRule {
    override fun validate(text: String): Boolean {
        if (text.isEmpty())
            return false

        return ValidatedObservableField(text).regex("[A-Za-z0-9]+").check()
    }

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}