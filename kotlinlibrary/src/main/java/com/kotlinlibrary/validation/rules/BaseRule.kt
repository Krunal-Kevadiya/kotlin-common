package com.kotlinlibrary.validation.rules

open class BaseRule<ErrorMessage>(var clazz: Class<ErrorMessage>) {
    open fun validate(text: String) : Boolean = false
    open fun getErrorMessage() : ErrorMessage? = null
    open fun setError(msg: ErrorMessage?) {}

    fun typed(desiredKotlinClass: Class<*>, desiredJavaClass: Class<*>): Boolean {
        return desiredKotlinClass.isAssignableFrom(clazz) || desiredJavaClass.isAssignableFrom(clazz)
    }
}