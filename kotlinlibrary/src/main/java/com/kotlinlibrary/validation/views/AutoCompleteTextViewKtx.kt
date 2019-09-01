package com.kotlinlibrary.validation.views

import android.widget.AutoCompleteTextView
import com.kotlinlibrary.validation.validator

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.nonEmpty(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.nonEmpty(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().nonEmpty(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }
        .check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.nonEmpty(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().nonEmpty()
        .addErrorCallback {
            callback.invoke(it)
        }
        .check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.minLength(minLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.minLength(
    minLength: Int,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().minLength(minLength, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.minLength(minLength: Int, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().minLength(minLength)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.maxLengths(maxLength: Int, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().maxLengths(maxLength, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.maxLengths(
    maxLength: Int,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().maxLengths(maxLength, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.maxLengths(maxLength: Int, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().maxLengths(maxLength)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.validEmail(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.validEmail(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validEmail(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.validEmail(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validEmail()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.validNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.validNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.validNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.greaterThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.greaterThan(
    number: Number,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().greaterThan(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.greaterThan(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThan(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.greaterThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.greaterThanOrEqual(
    number: Number,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.greaterThanOrEqual(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().greaterThanOrEqual(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.lessThan(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.lessThan(
    number: Number,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().lessThan(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.lessThan(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThan(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.lessThanOrEqual(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.lessThanOrEqual(
    number: Number,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.lessThanOrEqual(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().lessThanOrEqual(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.numberEqualTo(number: Number, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.numberEqualTo(
    number: Number,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.numberEqualTo(number: Number, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().numberEqualTo(number)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.allUperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.allUperCase(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allUpperCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.allUperCase(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allUpperCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


inline fun <reified ErrorMessage : Any> AutoCompleteTextView.allLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.allLowerCase(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().allLowerCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.allLowerCase(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().allLowerCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.atleastOneUpperCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.atleastOneUpperCase(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.atleastOneUpperCase(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneUpperCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.atleastOneLowerCase(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.atleastOneLowerCase(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.atleastOneLowerCase(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneLowerCase()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.atleastOneNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.atleastOneNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.atleastOneNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.startWithNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.startWithNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.startWithNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.startWithNonNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.startWithNonNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startWithNonNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.startWithNonNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startWithNonNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.noNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.noNumbers(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noNumbers(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.noNumbers(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noNumbers()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


inline fun <reified ErrorMessage : Any> AutoCompleteTextView.onlyNumbers(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.onlyNumbers(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().onlyNumbers(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.onlyNumbers(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().onlyNumbers()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.noSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.noSpecialCharacters(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.noSpecialCharacters(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().noSpecialCharacters()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.atleastOneSpecialCharacters(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.atleastOneSpecialCharacters(
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.atleastOneSpecialCharacters(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().atleastOneSpecialCharacters()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.textEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.textEqualTo(
    target: String,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().textEqualTo(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.textEqualTo(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textEqualTo(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.textNotEqualTo(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.textNotEqualTo(
    target: String,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.textNotEqualTo(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().textNotEqualTo(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.startsWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.startsWith(
    target: String,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().startsWith(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.startsWith(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().startsWith(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.endssWith(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.endssWith(
    target: String,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().endsWith(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.endssWith(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().endsWith(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.contains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.contains(
    target: String,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().contains(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.contains(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().contains(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.notContains(target: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.notContains(
    target: String,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().notContains(target, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.notContains(target: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().notContains(target)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}


inline fun <reified ErrorMessage : Any> AutoCompleteTextView.creditCardNumber(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.creditCardNumber(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumber(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.creditCardNumber(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumber()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.creditCardNumberWithSpaces(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.creditCardNumberWithSpaces(
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.creditCardNumberWithSpaces(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithSpaces()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.creditCardNumberWithDashes(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.creditCardNumberWithDashes(
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.creditCardNumberWithDashes(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().creditCardNumberWithDashes()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.validUrl(errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.validUrl(crossinline callback: (message: ErrorMessage?) -> Unit, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().validUrl(errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.validUrl(crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().validUrl()
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.regex(pattern: String, errorMsg: ErrorMessage? = null): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg).check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.regex(
    pattern: String,
    crossinline callback: (message: ErrorMessage?) -> Unit,
    errorMsg: ErrorMessage? = null
): Boolean {
    return validator<ErrorMessage>().regex(pattern, errorMsg)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.regex(pattern: String, crossinline callback: (message: ErrorMessage?) -> Unit): Boolean {
    return validator<ErrorMessage>().regex(pattern)
        .addErrorCallback {
            callback.invoke(it)
        }.check()
}