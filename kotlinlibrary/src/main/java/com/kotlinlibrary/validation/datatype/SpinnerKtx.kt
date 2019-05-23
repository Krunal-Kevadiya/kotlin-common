package com.kotlinlibrary.validation.datatype

import android.app.Activity
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.kotlinlibrary.validation.views.*

fun <ErrorMessage> Any.nonEmptyList(vararg spinnersList: Spinner, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.nonEmpty<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.nonEmptyList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).nonEmpty<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.nonEmptyList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).nonEmpty<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// Min Length
fun <ErrorMessage> Any.minLengthList(
    minLength: Int,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.minLength<ErrorMessage>(minLength) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.minLengthList(
    minLength: Int,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).minLength<ErrorMessage>(minLength) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.minLengthList(
    minLength: Int,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).minLength<ErrorMessage>(minLength) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// Max Length
fun <ErrorMessage> Any.maxLengthList(
    maxLength: Int,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.maxLength<ErrorMessage>(maxLength) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.maxLengthList(
    maxLength: Int,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).maxLength<ErrorMessage>(maxLength) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.maxLengthList(
    maxLength: Int,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).maxLength<ErrorMessage>(maxLength) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// Valid Email
fun <ErrorMessage> Any.validEmailList(vararg spinnersList: Spinner, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.validEmail<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.validEmailList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).validEmail<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.validEmailList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).validEmail<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// Valid Number
fun <ErrorMessage> Any.validNumberList(vararg spinnersList: Spinner, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.validNumber<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.validNumberList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).validNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.validNumberList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).validNumber<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// Greater Than
fun <ErrorMessage> Any.greaterThanList(
    number: Number,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.greaterThan<ErrorMessage>(number) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.greaterThanList(
    number: Number,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).greaterThan<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.greaterThanList(
    number: Number,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).greaterThan<ErrorMessage>(number) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// Greater Than Or Equal
fun <ErrorMessage> Any.greaterThanOrEqualList(
    number: Number,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.greaterThanOrEqual<ErrorMessage>(number) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.greaterThanOrEqualList(
    number: Number,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).greaterThanOrEqual<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.greaterThanOrEqualList(
    number: Number,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).greaterThanOrEqual<ErrorMessage>(number) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// Less Than
fun <ErrorMessage> Any.lessThanList(
    number: Number,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.lessThan<ErrorMessage>(number) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.lessThanList(
    number: Number,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).lessThan<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.lessThanList(
    number: Number,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).lessThan<ErrorMessage>(number) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// Less Than Or Equal
fun <ErrorMessage> Any.lessThanOrEqualnList(
    number: Number,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.lessThanOrEqual<ErrorMessage>(number) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.lessThanOrEqualList(
    number: Number,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).lessThanOrEqual<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.lessThanOrEqualList(
    number: Number,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).lessThanOrEqual<ErrorMessage>(number) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// Number Equal To
fun <ErrorMessage> Any.numberEqualToList(
    number: Number,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.numberEqualTo<ErrorMessage>(number) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.numberEqualToList(
    number: Number,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).numberEqualTo<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.numberEqualToList(
    number: Number,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).numberEqualTo<ErrorMessage>(number) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// All Upper Case
fun <ErrorMessage> Any.allUperCaseList(vararg spinnersList: Spinner, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.allUperCase<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.allUperCaseList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).allUperCase<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.allUperCaseList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).allUperCase<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// All Lower Case
fun <ErrorMessage> Any.allLowerCaseList(vararg spinnersList: Spinner, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.allLowerCase<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.allLowerCaseList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).allLowerCase<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.allLowerCaseList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).allLowerCase<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// At least one upper Case
fun <ErrorMessage> Any.atleastOneUpperCaseList(
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.atleastOneUpperCase<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.atleastOneUpperCaseList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).atleastOneUpperCase<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.atleastOneUpperCaseList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).atleastOneUpperCase<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// At least one lower Case
fun <ErrorMessage> Any.atleastOneLowerCaseList(
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.atleastOneLowerCase<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.atleastOneLowerCaseList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).atleastOneLowerCase<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.atleastOneLowerCaseList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).atleastOneLowerCase<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// At least one number
fun <ErrorMessage> Any.atleastOneNumberList(
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.atleastOneNumber<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.atleastOneNumberList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).atleastOneNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.atleastOneNumberList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).atleastOneNumber<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// Starts with number
fun <ErrorMessage> Any.startWithNumberList(
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.startWithNumber<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.startWithNumberList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).startWithNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.startWithNumberList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).startWithNumber<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// Starts with non number
fun <ErrorMessage> Any.startWithNonNumberList(
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.startWithNonNumber<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.startWithNonNumberList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).startWithNonNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.startWithNonNumberList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).startWithNonNumber<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// noNumbers
fun <ErrorMessage> Any.noNumbersList(vararg spinnersList: Spinner, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.noNumbers<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.noNumbersList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).noNumbers<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.noNumbersList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).noNumbers<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// only numbers
fun <ErrorMessage> Any.onlyNumbersList(vararg spinnersList: Spinner, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.onlyNumbers<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.onlyNumbersList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).onlyNumbers<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.onlyNumbersList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).onlyNumbers<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// no special characters
fun <ErrorMessage> Any.noSpecialCharactersList(
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.noSpecialCharacters<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.noSpecialCharactersList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).noSpecialCharacters<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.noSpecialCharactersList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).noSpecialCharacters<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// at least one special characters
fun <ErrorMessage> Any.atleastOneSpecialCharactersList(
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.atleastOneSpecialCharacters<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.atleastOneSpecialCharactersList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).atleastOneSpecialCharacters<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.atleastOneSpecialCharactersList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).atleastOneSpecialCharacters<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// text equal to
fun <ErrorMessage> Any.textEqualToList(
    target: String,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.textEqualTo<ErrorMessage>(target) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.textEqualToList(
    target: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).textEqualTo<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.textEqualToList(
    target: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).textEqualTo<ErrorMessage>(target) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// text not equal to
fun <ErrorMessage> Any.textNotEqualToList(
    target: String,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.textNotEqualTo<ErrorMessage>(target) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.textNotEqualToList(
    target: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).textNotEqualTo<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.textNotEqualToList(
    target: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).textNotEqualTo<ErrorMessage>(target) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// starts with
fun <ErrorMessage> Any.startsWithList(
    target: String,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.startsWith<ErrorMessage>(target) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.startsWithList(
    target: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).startsWith<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.startsWithList(
    target: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).startsWith<ErrorMessage>(target) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// ends with
fun <ErrorMessage> Any.endssWithList(
    target: String,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.endssWith<ErrorMessage>(target) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.endssWithList(
    target: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).endssWith<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.endssWithList(
    target: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).endssWith<ErrorMessage>(target) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// contains
fun <ErrorMessage> Any.containsList(
    target: String,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.contains<ErrorMessage>(target) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.containsList(
    target: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).contains<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.containsList(
    target: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).contains<ErrorMessage>(target) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// not contains
fun <ErrorMessage> Any.notContainsList(
    target: String,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.notContains<ErrorMessage>(target) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.notContainsList(
    target: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).notContains<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.notContainsList(
    target: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).notContains<ErrorMessage>(target) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// credit card number
fun <ErrorMessage> Any.creditCardNumberList(
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.creditCardNumber<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.creditCardNumberList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).creditCardNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.creditCardNumberList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).creditCardNumber<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// credit card number with spaces
fun <ErrorMessage> Any.creditCardNumberWithSpacesList(
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.creditCardNumberWithSpaces<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.creditCardNumberWithSpacesList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).creditCardNumberWithSpaces<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.creditCardNumberWithSpacesList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).creditCardNumberWithSpaces<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// credit card number with dashes
fun <ErrorMessage> Any.creditCardNumberWithDashesList(
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.creditCardNumberWithDashes<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.creditCardNumberWithDashesList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).creditCardNumberWithDashes<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.creditCardNumberWithDashesList(
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).creditCardNumberWithDashes<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// valid url
fun <ErrorMessage> Any.validUrlList(vararg spinnersList: Spinner, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.validUrl<ErrorMessage> {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.validUrlList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).validUrl<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.validUrlList(vararg spinnerIds: Int, callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).validUrl<ErrorMessage> {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}

// regex pattern
fun <ErrorMessage> Any.regexList(
    pattern: String,
    vararg spinnersList: Spinner,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (spinner in spinnersList) {
        result = spinner.regex<ErrorMessage>(pattern) {
            callback.invoke(spinner, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.regexList(
    pattern: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in spinnerIds) {
        result = findViewById<Spinner>(id).regex<ErrorMessage>(pattern) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.regexList(
    pattern: String,
    vararg spinnerIds: Int,
    callback: (view: Spinner, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in spinnerIds) {
                result = v.findViewById<Spinner>(id).regex<ErrorMessage>(pattern) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}