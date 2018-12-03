package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.validation.ValidatedObservableField

class AtLeastOneLowerCaseRule(var errorMsg: String = "At least one letter should be in lower case.") : BaseRule {
    override fun validate(text: String): Boolean = ValidatedObservableField(text).regex("^(?=.*[a-z]).+\$").check()

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}