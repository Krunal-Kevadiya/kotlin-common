package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.validation.ValidatedObservableField

class OnlyNumbersRule(var errorMsg: String = "Should not contain any alphabet characters!") : BaseRule {

    override fun validate(text: String): Boolean = ValidatedObservableField(text).regex("\\d+").check()

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}