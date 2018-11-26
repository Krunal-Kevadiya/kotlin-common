package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.validation.ValidatedObservableField

class ValidNumberRule(var errorMsg: String = "Invalid Number!") : BaseRule {

    override fun validate(text: String): Boolean {
        if (text.isEmpty())
            return false

        return if (text.startsWith("-")) {
            val txtNum = text.substringAfter("-")
            ValidatedObservableField(txtNum).regex("^[0-9]\\d*(\\.\\d+)?$").check()
        } else {
            ValidatedObservableField(text).regex("^[0-9]\\d*(\\.\\d+)?$").check()
        }
    }

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}