package com.kotlinlibrary.validation

open class ValidatorException(message: String) : Throwable(message)

class MismatchErrorTypeException : ValidatorException("Invalid error type, must be used Int or String")