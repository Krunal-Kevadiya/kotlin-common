package com.kotlinlibrary.validation.views

import com.kotlinlibrary.validation.validator

inline fun <reified ErrorMessage : Any> String.nonEmpty(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.nonEmpty(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }
            .check()
}

inline fun <reified ErrorMessage : Any> String.nonEmpty(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().nonEmpty()
            .addErrorCallback {
                callback.invoke(it)
            }
            .check()
}

inline fun <reified ErrorMessage : Any> String.minLength(minLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.minLength(minLength: Int, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.minLength(minLength: Int, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().minLength(minLength)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.maxLength(maxLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.maxLength(maxLength: Int, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.maxLength(maxLength: Int, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.validEmail(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.validEmail(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.validEmail(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validEmail()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.validNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.validNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.validNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validNumber()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.greaterThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.greaterThan(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.greaterThan(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThan(number)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.greaterThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.greaterThanOrEqual(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.greaterThanOrEqual(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.lessThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.lessThan(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.lessThan(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThan(number)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.lessThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.lessThanOrEqual(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.lessThanOrEqual(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.numberEqualTo(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.numberEqualTo(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.numberEqualTo(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.allUperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.allUperCase(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.allUperCase(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allUpperCase()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.allLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.allLowerCase(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.allLowerCase(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allLowerCase()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.atleastOneUpperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.atleastOneUpperCase(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.atleastOneUpperCase(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.atleastOneLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.atleastOneLowerCase(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.atleastOneLowerCase(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.atleastOneNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.atleastOneNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.atleastOneNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneNumber()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.startWithNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.startWithNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.startWithNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNumber()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.startWithNonNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.startWithNonNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.startWithNonNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNonNumber()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.noNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.noNumbers(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.noNumbers(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noNumbers()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.onlyNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.onlyNumbers(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.onlyNumbers(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().onlyNumbers()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.noSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.noSpecialCharacters(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.noSpecialCharacters(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.atleastOneSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.atleastOneSpecialCharacters(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.atleastOneSpecialCharacters(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.textEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.textEqualTo(target: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.textEqualTo(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textEqualTo(target)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.textNotEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.textNotEqualTo(target: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.textNotEqualTo(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.startsWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.startsWith(target: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.startsWith(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startsWith(target)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.endssWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.endssWith(target: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.endssWith(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().endsWith(target)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.contains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.contains(target: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.contains(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().contains(target)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.notContains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.notContains(target: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.notContains(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().notContains(target)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.creditCardNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.creditCardNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.creditCardNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumber()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.creditCardNumberWithSpaces(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.creditCardNumberWithSpaces(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.creditCardNumberWithSpaces(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.creditCardNumberWithDashes(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.creditCardNumberWithDashes(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.creditCardNumberWithDashes(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.validUrl(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.validUrl(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.validUrl(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validUrl()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.regex(pattern: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> String.regex(pattern: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

inline fun <reified ErrorMessage : Any> String.regex(pattern: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().regex(pattern)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}