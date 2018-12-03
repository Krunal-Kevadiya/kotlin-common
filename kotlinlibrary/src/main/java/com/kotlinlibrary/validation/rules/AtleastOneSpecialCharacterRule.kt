package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.validation.ValidatedObservableField

class AtleastOneSpecialCharacterRule(var errorMsg: String = "Should contain at least 1 special characters") : BaseRule {
    override fun validate(text: String): Boolean {
        if (text.isEmpty())
            return false

        return !ValidatedObservableField(text).regex("[A-Za-z0-9]+").check()
    }

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}