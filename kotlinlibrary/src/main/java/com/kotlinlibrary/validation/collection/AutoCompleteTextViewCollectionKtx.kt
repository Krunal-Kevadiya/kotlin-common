package com.kotlinlibrary.validation.collection

import android.app.Activity
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.kotlinlibrary.validation.views.*

inline fun <reified ErrorMessage : Any> Any.nonEmptyList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.nonEmptyList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.nonEmptyList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.minLengthList(
    minLength: Int,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.minLengthList(
    minLength: Int,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.minLengthList(
    minLength: Int,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.maxLengthList(
    maxLength: Int,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.maxLengthList(
    maxLength: Int,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.maxLengthList(
    maxLength: Int,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.validEmailList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.validEmailList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.validEmailList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Any.validNumberList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.validNumberList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.validNumberList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Any.greaterThanList(
    number: Number,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.greaterThanList(
    number: Number,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.greaterThanList(
    number: Number,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Any.greaterThanOrEqualList(
    number: Number,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.greaterThanOrEqualList(
    number: Number,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.greaterThanOrEqualList(
    number: Number,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Any.lessThanList(
    number: Number,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.lessThanList(
    number: Number,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.lessThanList(
    number: Number,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.lessThanOrEqualnList(
    number: Number,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.lessThanOrEqualList(
    number: Number,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.lessThanOrEqualList(
    number: Number,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.numberEqualToList(
    number: Number,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.numberEqualToList(
    number: Number,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.numberEqualToList(
    number: Number,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.allUperCaseList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.allUperCaseList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.allUperCaseList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.allLowerCaseList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.allLowerCaseList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.allLowerCaseList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.atleastOneUpperCaseList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.atleastOneUpperCaseList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.atleastOneUpperCaseList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.atleastOneLowerCaseList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.atleastOneLowerCaseList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.atleastOneLowerCaseList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.atleastOneNumberList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.atleastOneNumberList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.atleastOneNumberList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.startWithNumberList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.startWithNumberList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.startWithNumberList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.startWithNonNumberList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.startWithNonNumberList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.startWithNonNumberList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.noNumbersList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.noNumbersList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.noNumbersList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.onlyNumbersList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.onlyNumbersList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.onlyNumbersList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.noSpecialCharactersList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.noSpecialCharactersList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.noSpecialCharactersList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.atleastOneSpecialCharactersList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.atleastOneSpecialCharactersList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.atleastOneSpecialCharactersList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.textEqualToList(
    target: String,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.textEqualToList(
    target: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.textEqualToList(
    target: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.textNotEqualToList(
    target: String,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.textNotEqualToList(
    target: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.textNotEqualToList(
    target: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.startsWithList(
    target: String,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.startsWithList(
    target: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.startsWithList(
    target: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.endssWithList(
    target: String,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.endssWithList(
    target: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.endssWithList(
    target: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.containsList(
    target: String,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.containsList(
    target: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.containsList(
    target: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.notContainsList(
    target: String,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.notContainsList(
    target: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.notContainsList(
    target: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.creditCardNumberList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.creditCardNumberList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.creditCardNumberList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.creditCardNumberWithSpacesList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.creditCardNumberWithSpacesList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.creditCardNumberWithSpacesList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.creditCardNumberWithDashesList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.creditCardNumberWithDashesList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.creditCardNumberWithDashesList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.validUrlList(
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.validUrlList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.validUrlList(
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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
inline fun <reified ErrorMessage : Any> Any.regexList(
    pattern: String,
    vararg autoCompletesList: AutoCompleteTextView,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Activity.regexList(
    pattern: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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

inline fun <reified ErrorMessage : Any> Fragment.regexList(
    pattern: String,
    vararg autoCompletesIds: Int,
    crossinline callback: (view: AutoCompleteTextView, message: ErrorMessage?) -> Unit
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