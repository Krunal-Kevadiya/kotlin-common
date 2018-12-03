package com.kotlinlibrary.validation.rules

class MinLengthRule(val minLength: Int, var errorMsg: String = "Length should be greater than $minLength") : BaseRule {

    override fun validate(text: String): Boolean {
        return text.length >= minLength
    }

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}