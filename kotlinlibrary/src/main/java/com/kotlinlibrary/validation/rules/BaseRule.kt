package com.kotlinlibrary.validation.rules

interface BaseRule<ErrorMessage> {
    fun validate(text: String) : Boolean
    fun getErrorMessage() : ErrorMessage?
    fun setError(msg: ErrorMessage?)
}