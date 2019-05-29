package com.kotlinlibrary.validation.collection

import android.app.Activity
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.kotlinlibrary.validation.views.*

inline fun <reified ErrorMessage : Any> Any.nonEmptyList(vararg editTextList: EditText, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.nonEmpty<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.nonEmptyList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).nonEmpty<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.nonEmptyList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).nonEmpty<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.minLength<ErrorMessage>(minLength) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.minLengthList(
    minLength: Int,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).minLength<ErrorMessage>(minLength) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.minLengthList(
    minLength: Int,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).minLength<ErrorMessage>(minLength) {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.maxLength<ErrorMessage>(maxLength) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.maxLengthList(
    maxLength: Int,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).maxLength<ErrorMessage>(maxLength) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.maxLengthList(
    maxLength: Int,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).maxLength<ErrorMessage>(maxLength) {
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
inline fun <reified ErrorMessage : Any> Any.validEmailList(vararg editTextList: EditText, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.validEmail<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.validEmailList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).validEmail<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.validEmailList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).validEmail<ErrorMessage> {
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
inline fun <reified ErrorMessage : Any> Any.validNumberList(vararg editTextList: EditText, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.validNumber<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.validNumberList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).validNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.validNumberList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).validNumber<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.greaterThan<ErrorMessage>(number) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.greaterThanList(
    number: Number,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).greaterThan<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.greaterThanList(
    number: Number,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).greaterThan<ErrorMessage>(number) {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.greaterThanOrEqual<ErrorMessage>(number) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.greaterThanOrEqualList(
    number: Number,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).greaterThanOrEqual<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.greaterThanOrEqualList(
    number: Number,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).greaterThanOrEqual<ErrorMessage>(number) {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.lessThan<ErrorMessage>(number) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.lessThanList(
    number: Number,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).lessThan<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.lessThanList(
    number: Number,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).lessThan<ErrorMessage>(number) {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.lessThanOrEqual<ErrorMessage>(number) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.lessThanOrEqualList(
    number: Number,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).lessThanOrEqual<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.lessThanOrEqualList(
    number: Number,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).lessThanOrEqual<ErrorMessage>(number) {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.numberEqualTo<ErrorMessage>(number) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.numberEqualToList(
    number: Number,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).numberEqualTo<ErrorMessage>(number) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.numberEqualToList(
    number: Number,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).numberEqualTo<ErrorMessage>(number) {
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
inline fun <reified ErrorMessage : Any> Any.allUperCaseList(vararg editTextList: EditText, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.allUperCase<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.allUperCaseList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).allUperCase<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.allUperCaseList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).allUperCase<ErrorMessage> {
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
inline fun <reified ErrorMessage : Any> Any.allLowerCaseList(vararg editTextList: EditText, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.allLowerCase<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.allLowerCaseList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).allLowerCase<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.allLowerCaseList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).allLowerCase<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.atleastOneUpperCase<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.atleastOneUpperCaseList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).atleastOneUpperCase<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.atleastOneUpperCaseList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).atleastOneUpperCase<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.atleastOneLowerCase<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.atleastOneLowerCaseList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).atleastOneLowerCase<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.atleastOneLowerCaseList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).atleastOneLowerCase<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.atleastOneNumber<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.atleastOneNumberList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).atleastOneNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.atleastOneNumberList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).atleastOneNumber<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.startWithNumber<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.startWithNumberList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).startWithNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.startWithNumberList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).startWithNumber<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.startWithNonNumber<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.startWithNonNumberList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).startWithNonNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.startWithNonNumberList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).startWithNonNumber<ErrorMessage> {
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
inline fun <reified ErrorMessage : Any> Any.noNumbersList(vararg editTextList: EditText, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.noNumbers<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.noNumbersList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).noNumbers<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.noNumbersList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).noNumbers<ErrorMessage> {
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
inline fun <reified ErrorMessage : Any> Any.onlyNumbersList(vararg editTextList: EditText, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.onlyNumbers<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.onlyNumbersList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).onlyNumbers<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.onlyNumbersList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).onlyNumbers<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.noSpecialCharacters<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.noSpecialCharactersList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).noSpecialCharacters<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.noSpecialCharactersList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).noSpecialCharacters<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.atleastOneSpecialCharacters<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.atleastOneSpecialCharactersList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).atleastOneSpecialCharacters<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.atleastOneSpecialCharactersList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).atleastOneSpecialCharacters<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.textEqualTo<ErrorMessage>(target) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.textEqualToList(
    target: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).textEqualTo<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.textEqualToList(
    target: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).textEqualTo<ErrorMessage>(target) {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.textNotEqualTo<ErrorMessage>(target) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.textNotEqualToList(
    target: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).textNotEqualTo<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.textNotEqualToList(
    target: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).textNotEqualTo<ErrorMessage>(target) {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.startsWith<ErrorMessage>(target) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.startsWithList(
    target: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).startsWith<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.startsWithList(
    target: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).startsWith<ErrorMessage>(target) {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.endssWith<ErrorMessage>(target) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.endssWithList(
    target: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).endssWith<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.endssWithList(
    target: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).endssWith<ErrorMessage>(target) {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.contains<ErrorMessage>(target) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.containsList(
    target: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).contains<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.containsList(
    target: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).contains<ErrorMessage>(target) {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.notContains<ErrorMessage>(target) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.notContainsList(
    target: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).notContains<ErrorMessage>(target) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.notContainsList(
    target: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).notContains<ErrorMessage>(target) {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.creditCardNumber<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.creditCardNumberList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).creditCardNumber<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.creditCardNumberList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).creditCardNumber<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.creditCardNumberWithSpaces<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.creditCardNumberWithSpacesList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).creditCardNumberWithSpaces<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.creditCardNumberWithSpacesList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).creditCardNumberWithSpaces<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.creditCardNumberWithDashes<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.creditCardNumberWithDashesList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).creditCardNumberWithDashes<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.creditCardNumberWithDashesList(
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).creditCardNumberWithDashes<ErrorMessage> {
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
inline fun <reified ErrorMessage : Any> Any.validUrlList(vararg editTextList: EditText, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.validUrl<ErrorMessage> {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.validUrlList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).validUrl<ErrorMessage> {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.validUrlList(vararg editTextIds: Int, crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).validUrl<ErrorMessage> {
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
    vararg editTextList: EditText,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (edittext in editTextList) {
        result = edittext.regex<ErrorMessage>(pattern) {
            callback.invoke(edittext, it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Activity.regexList(
    pattern: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    for (id in editTextIds) {
        result = findViewById<EditText>(id).regex<ErrorMessage>(pattern) {
            callback.invoke(findViewById(id), it)
        }
        if (!result)
            return false
    }
    return result
}

inline fun <reified ErrorMessage : Any> Fragment.regexList(
    pattern: String,
    vararg editTextIds: Int,
    crossinline callback: (view: EditText, message: ErrorMessage?) -> Unit
): Boolean {
    var result = false
    if (view != null) {
        view?.let { v ->
            for (id in editTextIds) {
                result = v.findViewById<EditText>(id).regex<ErrorMessage>(pattern) {
                    callback.invoke(v.findViewById(id), it)
                }
                if (!result)
                    return false
            }
        }
    }
    return result
}
