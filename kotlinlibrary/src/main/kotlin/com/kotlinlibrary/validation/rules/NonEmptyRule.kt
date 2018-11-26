package com.kotlinlibrary.validation.rules

class NonEmptyRule(var errorMsg: String = "Can't be empty!") : BaseRule {
    override fun validate(text: String): Boolean = !text.isEmpty()

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}