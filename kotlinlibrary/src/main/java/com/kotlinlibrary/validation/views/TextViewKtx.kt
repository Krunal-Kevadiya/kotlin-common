package com.kotlinlibrary.validation.views

import android.widget.TextView
import com.kotlinlibrary.validation.ValidatedObservableField

fun <ErrorMessage> TextView.validator(onChange: Boolean = false): ValidatedObservableField<ErrorMessage> {
    return ValidatedObservableField(text.toString(), onChange)
}

fun <ErrorMessage> TextView.nonEmpty(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg).check()
}

fun <ErrorMessage> TextView.nonEmpty(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }
        .check()
}

fun <ErrorMessage> TextView.nonEmpty(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().nonEmpty()
        .addErrorCallback {
            callback.invoke(it)
        }
        .check()
}

fun <ErrorMessage> TextView.minLength(minLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg).check()
}

fun <ErrorMessage> TextView.minLength(minLength: Int, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.minLength(minLength: Int, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().minLength(minLength)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.maxLength(maxLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength, errorMsg).check()
}

fun <ErrorMessage> TextView.maxLength(maxLength: Int, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.maxLength(maxLength: Int, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.validEmail(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg).check()
}

fun <ErrorMessage> TextView.validEmail(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.validEmail(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validEmail()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.validNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg).check()
}

fun <ErrorMessage> TextView.validNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.validNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.greaterThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg).check()
}

fun <ErrorMessage> TextView.greaterThan(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.greaterThan(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThan(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.greaterThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg).check()
}

fun <ErrorMessage> TextView.greaterThanOrEqual(
    number: Number,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.greaterThanOrEqual(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.lessThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg).check()
}

fun <ErrorMessage> TextView.lessThan(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.lessThan(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThan(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.lessThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg).check()
}

fun <ErrorMessage> TextView.lessThanOrEqual(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.lessThanOrEqual(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.numberEqualTo(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg).check()
}

fun <ErrorMessage> TextView.numberEqualTo(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.numberEqualTo(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.allUperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg).check()
}

fun <ErrorMessage> TextView.allUperCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.allUperCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allUpperCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.allLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg).check()
}

fun <ErrorMessage> TextView.allLowerCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.allLowerCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allLowerCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.atleastOneUpperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg).check()
}

fun <ErrorMessage> TextView.atleastOneUpperCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.atleastOneUpperCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.atleastOneLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg).check()
}

fun <ErrorMessage> TextView.atleastOneLowerCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.atleastOneLowerCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.atleastOneNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg).check()
}

fun <ErrorMessage> TextView.atleastOneNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.atleastOneNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.startWithNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg).check()
}

fun <ErrorMessage> TextView.startWithNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.startWithNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.startWithNonNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg).check()
}

fun <ErrorMessage> TextView.startWithNonNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.startWithNonNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNonNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.noNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg).check()
}

fun <ErrorMessage> TextView.noNumbers(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.noNumbers(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noNumbers()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.onlyNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg).check()
}

fun <ErrorMessage> TextView.onlyNumbers(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.onlyNumbers(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().onlyNumbers()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.noSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg).check()
}

fun <ErrorMessage> TextView.noSpecialCharacters(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.noSpecialCharacters(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.atleastOneSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg).check()
}

fun <ErrorMessage> TextView.atleastOneSpecialCharacters(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.atleastOneSpecialCharacters(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.textEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg).check()
}

fun <ErrorMessage> TextView.textEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.textEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textEqualTo(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.textNotEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg).check()
}

fun <ErrorMessage> TextView.textNotEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.textNotEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.startsWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg).check()
}

fun <ErrorMessage> TextView.startsWith(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.startsWith(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startsWith(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.endssWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg).check()
}

fun <ErrorMessage> TextView.endssWith(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.endssWith(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().endsWith(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.contains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg).check()
}

fun <ErrorMessage> TextView.contains(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.contains(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().contains(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.notContains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg).check()
}

fun <ErrorMessage> TextView.notContains(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.notContains(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().notContains(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.creditCardNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg).check()
}

fun <ErrorMessage> TextView.creditCardNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.creditCardNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.creditCardNumberWithSpaces(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg).check()
}

fun <ErrorMessage> TextView.creditCardNumberWithSpaces(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.creditCardNumberWithSpaces(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.creditCardNumberWithDashes(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg).check()
}

fun <ErrorMessage> TextView.creditCardNumberWithDashes(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.creditCardNumberWithDashes(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.validUrl(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg).check()
}

fun <ErrorMessage> TextView.validUrl(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.validUrl(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validUrl()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.regex(pattern: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg).check()
}

fun <ErrorMessage> TextView.regex(pattern: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> TextView.regex(pattern: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().regex(pattern)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}