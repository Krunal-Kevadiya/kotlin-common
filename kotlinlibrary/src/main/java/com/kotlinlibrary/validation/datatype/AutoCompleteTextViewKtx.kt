package com.kotlinlibrary.validation.datatype

import android.app.Activity
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.kotlinlibrary.validation.views.*

fun <ErrorMessage> Any.nonEmptyList(
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.nonEmpty<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.nonEmptyList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).nonEmpty<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.nonEmptyList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).nonEmpty<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.minLength<ErrorMessage>(minLength) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.minLengthList(
    minLength: Int,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).minLength<ErrorMessage>(minLength) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.minLengthList(
    minLength: Int,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).minLength<ErrorMessage>(minLength) {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.maxLength<ErrorMessage>(maxLength) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.maxLengthList(
    maxLength: Int,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).maxLength<ErrorMessage>(maxLength) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.maxLengthList(
    maxLength: Int,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).maxLength<ErrorMessage>(maxLength) {
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
fun <ErrorMessage> Any.validEmailList(
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.validEmail<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.validEmailList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).validEmail<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.validEmailList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).validEmail<ErrorMessage> {
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

fun <ErrorMessage> Any.validNumberList(
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.validNumber<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.validNumberList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).validNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.validNumberList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).validNumber<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.greaterThan<ErrorMessage>(number) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.greaterThanList(
    number: Number,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).greaterThan<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.greaterThanList(
    number: Number,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).greaterThan<ErrorMessage>(number) {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.greaterThanOrEqual<ErrorMessage>(number) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.greaterThanOrEqualList(
    number: Number,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).greaterThanOrEqual<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.greaterThanOrEqualList(
    number: Number,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).greaterThanOrEqual<ErrorMessage>(number) {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.lessThan<ErrorMessage>(number) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.lessThanList(
    number: Number,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).lessThan<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.lessThanList(
    number: Number,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).lessThan<ErrorMessage>(number) {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.lessThanOrEqual<ErrorMessage>(number) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.lessThanOrEqualList(
    number: Number,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).lessThanOrEqual<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.lessThanOrEqualList(
    number: Number,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).lessThanOrEqual<ErrorMessage>(number) {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.numberEqualTo<ErrorMessage>(number) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.numberEqualToList(
    number: Number,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).numberEqualTo<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.numberEqualToList(
    number: Number,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).numberEqualTo<ErrorMessage>(number) {
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
fun <ErrorMessage> Any.allUperCaseList(
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.allUperCase<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.allUperCaseList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).allUperCase<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.allUperCaseList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).allUperCase<ErrorMessage> {
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
fun <ErrorMessage> Any.allLowerCaseList(
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.allLowerCase<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.allLowerCaseList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).allLowerCase<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.allLowerCaseList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).allLowerCase<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.atleastOneUpperCase<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.atleastOneUpperCaseList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).atleastOneUpperCase<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.atleastOneUpperCaseList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).atleastOneUpperCase<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.atleastOneLowerCase<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.atleastOneLowerCaseList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).atleastOneLowerCase<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.atleastOneLowerCaseList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).atleastOneLowerCase<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.atleastOneNumber<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.atleastOneNumberList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).atleastOneNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.atleastOneNumberList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).atleastOneNumber<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.startWithNumber<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.startWithNumberList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).startWithNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.startWithNumberList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).startWithNumber<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.startWithNonNumber<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.startWithNonNumberList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).startWithNonNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.startWithNonNumberList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).startWithNonNumber<ErrorMessage> {
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
fun <ErrorMessage> Any.noNumbersList(
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.noNumbers<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.noNumbersList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).noNumbers<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.noNumbersList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).noNumbers<ErrorMessage> {
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
fun <ErrorMessage> Any.onlyNumbersList(
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.onlyNumbers<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.onlyNumbersList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).onlyNumbers<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.onlyNumbersList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).onlyNumbers<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.noSpecialCharacters<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.noSpecialCharactersList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).noSpecialCharacters<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.noSpecialCharactersList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).noSpecialCharacters<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.atleastOneSpecialCharacters<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.atleastOneSpecialCharactersList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).atleastOneSpecialCharacters<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.atleastOneSpecialCharactersList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).atleastOneSpecialCharacters<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.textEqualTo<ErrorMessage>(target) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.textEqualToList(
    target: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).textEqualTo<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.textEqualToList(
    target: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).textEqualTo<ErrorMessage>(target) {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.textNotEqualTo<ErrorMessage>(target) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.textNotEqualToList(
    target: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).textNotEqualTo<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.textNotEqualToList(
    target: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).textNotEqualTo<ErrorMessage>(target) {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.startsWith<ErrorMessage>(target) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.startsWithList(
    target: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).startsWith<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.startsWithList(
    target: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).startsWith<ErrorMessage>(target) {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.endssWith<ErrorMessage>(target) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.endssWithList(
    target: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).endssWith<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.endssWithList(
    target: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).endssWith<ErrorMessage>(target) {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.contains<ErrorMessage>(target) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.containsList(
    target: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).contains<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.containsList(
    target: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).contains<ErrorMessage>(target) {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.notContains<ErrorMessage>(target) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.notContainsList(
    target: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).notContains<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.notContainsList(
    target: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).notContains<ErrorMessage>(target) {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.creditCardNumber<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.creditCardNumberList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).creditCardNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.creditCardNumberList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).creditCardNumber<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.creditCardNumberWithSpaces<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.creditCardNumberWithSpacesList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).creditCardNumberWithSpaces<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.creditCardNumberWithSpacesList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).creditCardNumberWithSpaces<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.creditCardNumberWithDashes<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.creditCardNumberWithDashesList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).creditCardNumberWithDashes<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.creditCardNumberWithDashesList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).creditCardNumberWithDashes<ErrorMessage> {
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
fun <ErrorMessage> Any.validUrlList(
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.validUrl<ErrorMessage> {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.validUrlList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).validUrl<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.validUrlList(
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).validUrl<ErrorMessage> {
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
    vararg autoCompletesList: AutoCompleteTextView,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (autocomplete in autoCompletesList) {
        result = autocomplete.regex<ErrorMessage>(pattern) {
            callback.invoke(autocomplete, it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Activity.regexList(
    pattern: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in autoCompletesIds) {
        result = findViewById<AutoCompleteTextView>(id).regex<ErrorMessage>(pattern) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

fun <ErrorMessage> Fragment.regexList(
    pattern: String,
    vararg autoCompletesIds: Int,
    callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in autoCompletesIds) {
                result = v.findViewById<AutoCompleteTextView>(id).regex<ErrorMessage>(pattern) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}