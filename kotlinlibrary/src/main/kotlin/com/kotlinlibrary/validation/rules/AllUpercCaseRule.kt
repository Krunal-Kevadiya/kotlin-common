package com.kotlinlibrary.validation.rules

class AllUpercCaseRule(var errorMsg: String = "All letters should be in upper case.") : BaseRule {
    override fun validate(text: String): Boolean = text == text.toUpperCase()

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}