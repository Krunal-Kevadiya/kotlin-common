package com.kotlinlibrary.validation.collection

import com.kotlinlibrary.validation.views.*

inline fun <reified ErrorMessage : Any> Any.nonEmptyList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.nonEmpty<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// Min Length
inline fun <reified ErrorMessage : Any> Any.minLengthList(
    minLength: Int,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.minLength<ErrorMessage>(minLength) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// Max Length
inline fun <reified ErrorMessage : Any> Any.maxLengthList(
    maxLength: Int,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.maxLength<ErrorMessage>(maxLength) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// Valid Email
inline fun <reified ErrorMessage : Any> Any.validEmailList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.validEmail<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// Valid Number
inline fun <reified ErrorMessage : Any> Any.validNumberList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.validNumber<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// Greater Than
inline fun <reified ErrorMessage : Any> Any.greaterThanList(
    number: Number,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.greaterThan<ErrorMessage>(number) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// Greater Than Or Equal
inline fun <reified ErrorMessage : Any> Any.greaterThanOrEqualList(
    number: Number,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.greaterThanOrEqual<ErrorMessage>(number) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// Less Than
inline fun <reified ErrorMessage : Any> Any.lessThanList(
    number: Number,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.lessThan<ErrorMessage>(number) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// Less Than Or Equal
inline fun <reified ErrorMessage : Any> Any.lessThanOrEqualnList(
    number: Number,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.lessThanOrEqual<ErrorMessage>(number) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// Number Equal To
inline fun <reified ErrorMessage : Any> Any.numberEqualToList(
    number: Number,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.numberEqualTo<ErrorMessage>(number) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// All Upper Case
inline fun <reified ErrorMessage : Any> Any.allUperCaseList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.allUperCase<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// All Lower Case
inline fun <reified ErrorMessage : Any> Any.allLowerCaseList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.allLowerCase<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// At least one upper Case
inline fun <reified ErrorMessage : Any> Any.atleastOneUpperCaseList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.atleastOneUpperCase<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// At least one lower Case
inline fun <reified ErrorMessage : Any> Any.atleastOneLowerCaseList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.atleastOneLowerCase<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// At least one number
inline fun <reified ErrorMessage : Any> Any.atleastOneNumberList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.atleastOneNumber<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// Starts with number
inline fun <reified ErrorMessage : Any> Any.startWithNumberList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.startWithNumber<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// Starts with non number
inline fun <reified ErrorMessage : Any> Any.startWithNonNumberList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.startWithNonNumber<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// noNumbers
inline fun <reified ErrorMessage : Any> Any.noNumbersList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.noNumbers<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// only numbers
inline fun <reified ErrorMessage : Any> Any.onlyNumbersList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.onlyNumbers<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// no special characters
inline fun <reified ErrorMessage : Any> Any.noSpecialCharactersList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.noSpecialCharacters<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}


// at least one special characters
inline fun <reified ErrorMessage : Any> Any.atleastOneSpecialCharactersList(
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.atleastOneSpecialCharacters<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// text equal to
inline fun <reified ErrorMessage : Any> Any.textEqualToList(
    target: String,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.textEqualTo<ErrorMessage>(target) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// text not equal to
inline fun <reified ErrorMessage : Any> Any.textNotEqualToList(
    target: String,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.textNotEqualTo<ErrorMessage>(target) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// starts with
inline fun <reified ErrorMessage : Any> Any.startsWithList(
    target: String,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.startsWith<ErrorMessage>(target) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// ends with
inline fun <reified ErrorMessage : Any> Any.endssWithList(
    target: String,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.endssWith<ErrorMessage>(target) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// contains
inline fun <reified ErrorMessage : Any> Any.containsList(
    target: String,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.contains<ErrorMessage>(target) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// not contains
inline fun <reified ErrorMessage : Any> Any.notContainsList(
    target: String,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.notContains<ErrorMessage>(target) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// credit card number
inline fun <reified ErrorMessage : Any> Any.creditCardNumberList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.creditCardNumber<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// credit card number with spaces
inline fun <reified ErrorMessage : Any> Any.creditCardNumberWithSpacesList(
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.creditCardNumberWithSpaces<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// credit card number with dashes
inline fun <reified ErrorMessage : Any> Any.creditCardNumberWithDashesList(
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.creditCardNumberWithDashes<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// valid url
inline fun <reified ErrorMessage : Any> Any.validUrlList(vararg strsList: String, crossinline callback: (view: String, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (str in strsList) {
        result = str.validUrl<ErrorMessage> {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}

// regex pattern
inline fun <reified ErrorMessage : Any> Any.regexList(
    pattern: String,
    vararg strsList: String,
    crossinline callback: (view: String, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (str in strsList) {
        result = str.regex<ErrorMessage>(pattern) {
            callback.invoke(str, it)
        }

        if (!result)
            return false
    }
    return result
}
