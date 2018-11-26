package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.validation.ValidatedObservableField

class AtLeastOneUpercCaseRule(var errorMsg: String = "At least one letter should be in upper case.") : BaseRule {
    override fun validate(text: String): Boolean = ValidatedObservableField(text).regex("^(?=.*[A-Z]).+\$").check()

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}