package com.kotlinlibrary.validation

open class ValidationException(message: String) : Throwable(message)

class MismatchErrorTypeException : ValidationException("Invalid error type, must be used Int or String")