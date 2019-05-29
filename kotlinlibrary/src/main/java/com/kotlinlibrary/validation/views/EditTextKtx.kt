package com.kotlinlibrary.validation.views

import android.widget.EditText
import com.kotlinlibrary.validation.validator

inline fun <reified ErrorMessage : Any> EditText.nonEmpty(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.nonEmpty(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }
        .check()
}

inline fun <reified ErrorMessage : Any> EditText.nonEmpty(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().nonEmpty()
        .addErrorCallback {
            callback.invoke(it)
        }
        .check()
}

inline fun <reified ErrorMessage : Any> EditText.minLength(minLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.minLength(minLength: Int, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.minLength(minLength: Int, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().minLength(minLength)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.maxLength(maxLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.maxLength(maxLength: Int, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.maxLength(maxLength: Int, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.validEmail(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.validEmail(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.validEmail(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validEmail()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.validNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.validNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.validNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.greaterThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.greaterThan(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.greaterThan(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThan(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.greaterThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.greaterThanOrEqual(
    number: Number,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.greaterThanOrEqual(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.lessThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.lessThan(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.lessThan(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThan(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.lessThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.lessThanOrEqual(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.lessThanOrEqual(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.numberEqualTo(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.numberEqualTo(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.numberEqualTo(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.allUperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.allUperCase(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.allUperCase(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allUpperCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.allLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.allLowerCase(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.allLowerCase(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allLowerCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.atleastOneUpperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.atleastOneUpperCase(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.atleastOneUpperCase(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.atleastOneLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.atleastOneLowerCase(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.atleastOneLowerCase(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.atleastOneNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.atleastOneNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.atleastOneNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.startWithNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.startWithNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.startWithNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.startWithNonNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.startWithNonNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.startWithNonNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNonNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.noNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.noNumbers(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.noNumbers(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noNumbers()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.onlyNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.onlyNumbers(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.onlyNumbers(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().onlyNumbers()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.noSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.noSpecialCharacters(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.noSpecialCharacters(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.atleastOneSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.atleastOneSpecialCharacters(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.atleastOneSpecialCharacters(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.textEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.textEqualTo(target: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.textEqualTo(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textEqualTo(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.textNotEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.textNotEqualTo(target: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.textNotEqualTo(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.startsWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.startsWith(target: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.startsWith(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startsWith(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.endssWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.endssWith(target: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.endssWith(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().endsWith(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.contains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.contains(target: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.contains(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().contains(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.notContains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.notContains(target: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.notContains(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().notContains(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.creditCardNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.creditCardNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.creditCardNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.creditCardNumberWithSpaces(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.creditCardNumberWithSpaces(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.creditCardNumberWithSpaces(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.creditCardNumberWithDashes(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.creditCardNumberWithDashes(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.creditCardNumberWithDashes(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.validUrl(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.validUrl(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.validUrl(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validUrl()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.regex(pattern: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> EditText.regex(pattern: String, crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> EditText.regex(pattern: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().regex(pattern)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}