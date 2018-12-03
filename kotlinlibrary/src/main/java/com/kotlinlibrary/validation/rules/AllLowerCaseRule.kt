package com.kotlinlibrary.validation.rules

class AllLowerCaseRule(var errorMsg: String = "All letters should be in lower case.") : BaseRule {
    override fun validate(text: String): Boolean = text == text.toLowerCase()

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}