package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.validation.ValidatedObservableField

class AtLeastOneNumberCaseRule(var errorMsg: String = "At least one letter should be a number.") : BaseRule {
    override fun validate(text: String): Boolean = ValidatedObservableField(text).regex(".*\\d.*").check()

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}