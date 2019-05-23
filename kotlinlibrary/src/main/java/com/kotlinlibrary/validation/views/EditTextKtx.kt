package com.kotlinlibrary.validation.views

import android.widget.EditText
import com.kotlinlibrary.validation.ValidatedObservableField

fun <ErrorMessage> EditText.validator(onChange: Boolean = false): ValidatedObservableField<ErrorMessage> {
    return ValidatedObservableField(text.toString(), onChange)
}

fun <ErrorMessage> EditText.nonEmpty(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg).check()
}

fun <ErrorMessage> EditText.nonEmpty(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }
        .check()
}

fun <ErrorMessage> EditText.nonEmpty(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().nonEmpty()
        .addErrorCallback {
            callback.invoke(it)
        }
        .check()
}

fun <ErrorMessage> EditText.minLength(minLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg).check()
}

fun <ErrorMessage> EditText.minLength(minLength: Int, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.minLength(minLength: Int, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().minLength(minLength)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.maxLength(maxLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength, errorMsg).check()
}

fun <ErrorMessage> EditText.maxLength(maxLength: Int, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.maxLength(maxLength: Int, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.validEmail(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg).check()
}

fun <ErrorMessage> EditText.validEmail(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.validEmail(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validEmail()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.validNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg).check()
}

fun <ErrorMessage> EditText.validNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.validNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.greaterThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg).check()
}

fun <ErrorMessage> EditText.greaterThan(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.greaterThan(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThan(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.greaterThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg).check()
}

fun <ErrorMessage> EditText.greaterThanOrEqual(
    number: Number,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.greaterThanOrEqual(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.lessThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg).check()
}

fun <ErrorMessage> EditText.lessThan(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.lessThan(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThan(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.lessThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg).check()
}

fun <ErrorMessage> EditText.lessThanOrEqual(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.lessThanOrEqual(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.numberEqualTo(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg).check()
}

fun <ErrorMessage> EditText.numberEqualTo(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.numberEqualTo(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.allUperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg).check()
}

fun <ErrorMessage> EditText.allUperCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.allUperCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allUpperCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.allLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg).check()
}

fun <ErrorMessage> EditText.allLowerCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.allLowerCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allLowerCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.atleastOneUpperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg).check()
}

fun <ErrorMessage> EditText.atleastOneUpperCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.atleastOneUpperCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.atleastOneLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg).check()
}

fun <ErrorMessage> EditText.atleastOneLowerCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.atleastOneLowerCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.atleastOneNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg).check()
}

fun <ErrorMessage> EditText.atleastOneNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.atleastOneNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.startWithNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg).check()
}

fun <ErrorMessage> EditText.startWithNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.startWithNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.startWithNonNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg).check()
}

fun <ErrorMessage> EditText.startWithNonNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.startWithNonNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNonNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.noNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg).check()
}

fun <ErrorMessage> EditText.noNumbers(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.noNumbers(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noNumbers()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.onlyNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg).check()
}

fun <ErrorMessage> EditText.onlyNumbers(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.onlyNumbers(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().onlyNumbers()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.noSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg).check()
}

fun <ErrorMessage> EditText.noSpecialCharacters(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.noSpecialCharacters(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.atleastOneSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg).check()
}

fun <ErrorMessage> EditText.atleastOneSpecialCharacters(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.atleastOneSpecialCharacters(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.textEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg).check()
}

fun <ErrorMessage> EditText.textEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.textEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textEqualTo(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.textNotEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg).check()
}

fun <ErrorMessage> EditText.textNotEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.textNotEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.startsWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg).check()
}

fun <ErrorMessage> EditText.startsWith(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.startsWith(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startsWith(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.endssWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg).check()
}

fun <ErrorMessage> EditText.endssWith(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.endssWith(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().endsWith(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.contains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg).check()
}

fun <ErrorMessage> EditText.contains(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.contains(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().contains(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.notContains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg).check()
}

fun <ErrorMessage> EditText.notContains(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.notContains(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().notContains(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.creditCardNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg).check()
}

fun <ErrorMessage> EditText.creditCardNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.creditCardNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.creditCardNumberWithSpaces(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg).check()
}

fun <ErrorMessage> EditText.creditCardNumberWithSpaces(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.creditCardNumberWithSpaces(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.creditCardNumberWithDashes(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg).check()
}

fun <ErrorMessage> EditText.creditCardNumberWithDashes(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.creditCardNumberWithDashes(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.validUrl(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg).check()
}

fun <ErrorMessage> EditText.validUrl(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.validUrl(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validUrl()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.regex(pattern: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg).check()
}

fun <ErrorMessage> EditText.regex(pattern: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> EditText.regex(pattern: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().regex(pattern)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}