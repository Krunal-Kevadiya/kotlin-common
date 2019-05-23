package com.kotlinlibrary.validation.views

import android.widget.Spinner
import com.kotlinlibrary.validation.ValidatedObservableField

fun <ErrorMessage> Spinner.validator(onChange: Boolean = false): ValidatedObservableField<ErrorMessage> {
    return ValidatedObservableField(this.selectedItem.toString(), onChange)
}

fun <ErrorMessage> Spinner.nonEmpty(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg).check()
}

fun <ErrorMessage> Spinner.nonEmpty(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }
        .check()
}

fun <ErrorMessage> Spinner.nonEmpty(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().nonEmpty()
        .addErrorCallback {
            callback.invoke(it)
        }
        .check()
}

fun <ErrorMessage> Spinner.minLength(minLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg).check()
}

fun <ErrorMessage> Spinner.minLength(minLength: Int, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.minLength(minLength: Int, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().minLength(minLength)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.maxLength(maxLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength, errorMsg).check()
}

fun <ErrorMessage> Spinner.maxLength(maxLength: Int, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.maxLength(maxLength: Int, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().maxLength(maxLength)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


fun <ErrorMessage> Spinner.validEmail(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg).check()
}

fun <ErrorMessage> Spinner.validEmail(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.validEmail(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validEmail()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.validNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg).check()
}

fun <ErrorMessage> Spinner.validNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.validNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.greaterThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg).check()
}

fun <ErrorMessage> Spinner.greaterThan(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.greaterThan(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThan(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.greaterThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg).check()
}

fun <ErrorMessage> Spinner.greaterThanOrEqual(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.greaterThanOrEqual(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.lessThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg).check()
}

fun <ErrorMessage> Spinner.lessThan(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.lessThan(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThan(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.lessThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg).check()
}

fun <ErrorMessage> Spinner.lessThanOrEqual(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.lessThanOrEqual(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.numberEqualTo(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg).check()
}

fun <ErrorMessage> Spinner.numberEqualTo(number: Number, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.numberEqualTo(number: Number, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.allUperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg).check()
}

fun <ErrorMessage> Spinner.allUperCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.allUperCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allUpperCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.allLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg).check()
}

fun <ErrorMessage> Spinner.allLowerCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.allLowerCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allLowerCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.atleastOneUpperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg).check()
}

fun <ErrorMessage> Spinner.atleastOneUpperCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.atleastOneUpperCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.atleastOneLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg).check()
}

fun <ErrorMessage> Spinner.atleastOneLowerCase(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.atleastOneLowerCase(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.atleastOneNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg).check()
}

fun <ErrorMessage> Spinner.atleastOneNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.atleastOneNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.startWithNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg).check()
}

fun <ErrorMessage> Spinner.startWithNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.startWithNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


fun <ErrorMessage> Spinner.startWithNonNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg).check()
}

fun <ErrorMessage> Spinner.startWithNonNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.startWithNonNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNonNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.noNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg).check()
}

fun <ErrorMessage> Spinner.noNumbers(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.noNumbers(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noNumbers()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.onlyNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg).check()
}

fun <ErrorMessage> Spinner.onlyNumbers(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.onlyNumbers(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().onlyNumbers()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


fun <ErrorMessage> Spinner.noSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg).check()
}

fun <ErrorMessage> Spinner.noSpecialCharacters(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.noSpecialCharacters(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


fun <ErrorMessage> Spinner.atleastOneSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg).check()
}

fun <ErrorMessage> Spinner.atleastOneSpecialCharacters(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.atleastOneSpecialCharacters(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.textEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg).check()
}

fun <ErrorMessage> Spinner.textEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.textEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textEqualTo(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.textNotEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg).check()
}

fun <ErrorMessage> Spinner.textNotEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.textNotEqualTo(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


fun <ErrorMessage> Spinner.startsWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg).check()
}

fun <ErrorMessage> Spinner.startsWith(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.startsWith(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startsWith(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


fun <ErrorMessage> Spinner.endssWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg).check()
}

fun <ErrorMessage> Spinner.endssWith(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.endssWith(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().endsWith(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.contains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg).check()
}

fun <ErrorMessage> Spinner.contains(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.contains(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().contains(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.notContains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg).check()
}

fun <ErrorMessage> Spinner.notContains(target: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.notContains(target: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().notContains(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.creditCardNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg).check()
}

fun <ErrorMessage> Spinner.creditCardNumber(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.creditCardNumber(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.creditCardNumberWithSpaces(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg).check()
}

fun <ErrorMessage> Spinner.creditCardNumberWithSpaces(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.creditCardNumberWithSpaces(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.creditCardNumberWithDashes(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg).check()
}

fun <ErrorMessage> Spinner.creditCardNumberWithDashes(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.creditCardNumberWithDashes(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.validUrl(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg).check()
}

fun <ErrorMessage> Spinner.validUrl(callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.validUrl(callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validUrl()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


fun <ErrorMessage> Spinner.regex(pattern: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg).check()
}

fun <ErrorMessage> Spinner.regex(pattern: String, callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

fun <ErrorMessage> Spinner.regex(pattern: String, callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().regex(pattern)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}