package com.kotlinlibrary.validation.views

import android.widget.AutoCompleteTextView
import com.kotlinlibrary.validation.ValidatedObservableField

fun <ErrorMessage> AutoCompleteTextView.validator(onChange: Boolean = false): ValidatedObservableField<ErrorMessage> {
    return ValidatedObservableField(text.toString(), onChange)
}

fun <ErrorMessage> AutoCompleteTextView.nonEmpty(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.nonEmpty(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }
        .check()
}

fun <ErrorMessage> AutoCompleteTextView.nonEmpty(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().nonEmpty()
        .addErrorCallback {
            callback.invoke(it)
        }
        .check()
}

fun <ErrorMessage> AutoCompleteTextView.minLength(minLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.minLength(
    minLength: Int,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.minLength(minLength: Int, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().minLength(minLength)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.maxLength(maxLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.maxLength(
    maxLength: Int,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.maxLength(maxLength: Int, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.validEmail(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.validEmail(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.validEmail(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validEmail()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.validNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.validNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.validNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.greaterThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.greaterThan(
    number: Number,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.greaterThan(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThan(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.greaterThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.greaterThanOrEqual(
    number: Number,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.greaterThanOrEqual(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.lessThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.lessThan(
    number: Number,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.lessThan(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThan(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.lessThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.lessThanOrEqual(
    number: Number,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.lessThanOrEqual(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.numberEqualTo(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.numberEqualTo(
    number: Number,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.numberEqualTo(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.allUperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.allUperCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.allUperCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allUpperCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


fun <ErrorMessage> AutoCompleteTextView.allLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.allLowerCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.allLowerCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allLowerCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.atleastOneUpperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.atleastOneUpperCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.atleastOneUpperCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.atleastOneLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.atleastOneLowerCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.atleastOneLowerCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.atleastOneNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.atleastOneNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.atleastOneNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.startWithNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.startWithNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.startWithNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.startWithNonNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.startWithNonNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.startWithNonNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNonNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.noNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.noNumbers(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.noNumbers(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noNumbers()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


fun <ErrorMessage> AutoCompleteTextView.onlyNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.onlyNumbers(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.onlyNumbers(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().onlyNumbers()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.noSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.noSpecialCharacters(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.noSpecialCharacters(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.atleastOneSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.atleastOneSpecialCharacters(
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.atleastOneSpecialCharacters(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.textEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.textEqualTo(
    target: String,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.textEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textEqualTo(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.textNotEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.textNotEqualTo(
    target: String,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.textNotEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.startsWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.startsWith(
    target: String,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.startsWith(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startsWith(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.endssWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.endssWith(
    target: String,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.endssWith(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().endsWith(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.contains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.contains(
    target: String,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.contains(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().contains(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.notContains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.notContains(
    target: String,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.notContains(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().notContains(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


fun <ErrorMessage> AutoCompleteTextView.creditCardNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.creditCardNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.creditCardNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.creditCardNumberWithSpaces(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.creditCardNumberWithSpaces(
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.creditCardNumberWithSpaces(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.creditCardNumberWithDashes(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.creditCardNumberWithDashes(
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.creditCardNumberWithDashes(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.validUrl(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.validUrl(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.validUrl(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validUrl()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.regex(pattern: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg).check()
}

fun <ErrorMessage> AutoCompleteTextView.regex(
    pattern: String,
    callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> AutoCompleteTextView.regex(pattern: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().regex(pattern)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}