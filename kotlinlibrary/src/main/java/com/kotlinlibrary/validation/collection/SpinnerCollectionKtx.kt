package com.kotlinlibrary.validation.collection

import android.app.Activity
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.kotlinlibrary.validation.views.*

inline fun <reified ErrorMessage : Any> Any.nonEmptyList(vararg spinnersList: Spinner, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Activity.nonEmptyList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Fragment.nonEmptyList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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
inline fun <reified ErrorMessage : Any> Any.minLengthList(
    minLength: Int,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.minLengthList(
    minLength: Int,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.minLengthList(
    minLength: Int,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.maxLengthList(
    maxLength: Int,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.maxLengthList(
    maxLength: Int,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.maxLengthList(
    maxLength: Int,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.validEmailList(vararg spinnersList: Spinner, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Activity.validEmailList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Fragment.validEmailList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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
inline fun <reified ErrorMessage : Any> Any.validNumberList(vararg spinnersList: Spinner, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Activity.validNumberList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Fragment.validNumberList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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
inline fun <reified ErrorMessage : Any> Any.greaterThanList(
    number: Number,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.greaterThanList(
    number: Number,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.greaterThanList(
    number: Number,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.greaterThanOrEqualList(
    number: Number,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.greaterThanOrEqualList(
    number: Number,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.greaterThanOrEqualList(
    number: Number,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.lessThanList(
    number: Number,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.lessThanList(
    number: Number,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.lessThanList(
    number: Number,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.lessThanOrEqualnList(
    number: Number,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.lessThanOrEqualList(
    number: Number,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.lessThanOrEqualList(
    number: Number,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.numberEqualToList(
    number: Number,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.numberEqualToList(
    number: Number,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.numberEqualToList(
    number: Number,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.allUperCaseList(vararg spinnersList: Spinner, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Activity.allUperCaseList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Fragment.allUperCaseList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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
inline fun <reified ErrorMessage : Any> Any.allLowerCaseList(vararg spinnersList: Spinner, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Activity.allLowerCaseList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Fragment.allLowerCaseList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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
inline fun <reified ErrorMessage : Any> Any.atleastOneUpperCaseList(
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.atleastOneUpperCaseList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.atleastOneUpperCaseList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.atleastOneLowerCaseList(
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.atleastOneLowerCaseList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.atleastOneLowerCaseList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.atleastOneNumberList(
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.atleastOneNumberList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.atleastOneNumberList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.startWithNumberList(
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.startWithNumberList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Fragment.startWithNumberList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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
inline fun <reified ErrorMessage : Any> Any.startWithNonNumberList(
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.startWithNonNumberList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.startWithNonNumberList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.noNumbersList(vararg spinnersList: Spinner, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Activity.noNumbersList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Fragment.noNumbersList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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
inline fun <reified ErrorMessage : Any> Any.onlyNumbersList(vararg spinnersList: Spinner, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Activity.onlyNumbersList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Fragment.onlyNumbersList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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
inline fun <reified ErrorMessage : Any> Any.noSpecialCharactersList(
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.noSpecialCharactersList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.noSpecialCharactersList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.atleastOneSpecialCharactersList(
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.atleastOneSpecialCharactersList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.atleastOneSpecialCharactersList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.textEqualToList(
    target: String,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.textEqualToList(
    target: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.textEqualToList(
    target: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.textNotEqualToList(
    target: String,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.textNotEqualToList(
    target: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.textNotEqualToList(
    target: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.startsWithList(
    target: String,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.startsWithList(
    target: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.startsWithList(
    target: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.endssWithList(
    target: String,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.endssWithList(
    target: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.endssWithList(
    target: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.containsList(
    target: String,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.containsList(
    target: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.containsList(
    target: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.notContainsList(
    target: String,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.notContainsList(
    target: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.notContainsList(
    target: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.creditCardNumberList(
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.creditCardNumberList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.creditCardNumberList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.creditCardNumberWithSpacesList(
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.creditCardNumberWithSpacesList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.creditCardNumberWithSpacesList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.creditCardNumberWithDashesList(
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.creditCardNumberWithDashesList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.creditCardNumberWithDashesList(
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.validUrlList(vararg spinnersList: Spinner, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Activity.validUrlList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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

inline fun <reified ErrorMessage : Any> Fragment.validUrlList(vararg spinnerIds: Int, crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit): Boolean {
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
inline fun <reified ErrorMessage : Any> Any.regexList(
    pattern: String,
    vararg spinnersList: Spinner,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.regexList(
    pattern: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.regexList(
    pattern: String,
    vararg spinnerIds: Int,
    crossinline callback: (view: Spinner, message: ErrorMessage?) -> Unit
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