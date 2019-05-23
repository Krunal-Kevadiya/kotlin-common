package com.kotlinlibrary.validation.datatype

import android.app.Activity
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.kotlinlibrary.validation.views.*

fun <ErrorMessage> Any.nonEmptyList(vararg editTextList: EditText, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Activity.nonEmptyList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Fragment.nonEmptyList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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
fun <ErrorMessage> Any.minLengthList(
    minLength: Int,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.minLengthList(
    minLength: Int,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.minLengthList(
    minLength: Int,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.maxLengthList(
    maxLength: Int,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.maxLengthList(
    maxLength: Int,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.maxLengthList(
    maxLength: Int,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.validEmailList(vararg editTextList: EditText, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Activity.validEmailList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Fragment.validEmailList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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
fun <ErrorMessage> Any.validNumberList(vararg editTextList: EditText, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Activity.validNumberList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Fragment.validNumberList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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
fun <ErrorMessage> Any.greaterThanList(
    number: Number,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.greaterThanList(
    number: Number,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.greaterThanList(
    number: Number,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.greaterThanOrEqualList(
    number: Number,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.greaterThanOrEqualList(
    number: Number,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.greaterThanOrEqualList(
    number: Number,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.lessThanList(
    number: Number,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.lessThanList(
    number: Number,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.lessThanList(
    number: Number,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.lessThanOrEqualnList(
    number: Number,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.lessThanOrEqualList(
    number: Number,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.lessThanOrEqualList(
    number: Number,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.numberEqualToList(
    number: Number,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.numberEqualToList(
    number: Number,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.numberEqualToList(
    number: Number,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.allUperCaseList(vararg editTextList: EditText, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Activity.allUperCaseList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Fragment.allUperCaseList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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
fun <ErrorMessage> Any.allLowerCaseList(vararg editTextList: EditText, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Activity.allLowerCaseList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Fragment.allLowerCaseList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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
fun <ErrorMessage> Any.atleastOneUpperCaseList(
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.atleastOneUpperCaseList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.atleastOneUpperCaseList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.atleastOneLowerCaseList(
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.atleastOneLowerCaseList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.atleastOneLowerCaseList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.atleastOneNumberList(
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.atleastOneNumberList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.atleastOneNumberList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.startWithNumberList(
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.startWithNumberList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.startWithNumberList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.startWithNonNumberList(
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.startWithNonNumberList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.startWithNonNumberList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.noNumbersList(vararg editTextList: EditText, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Activity.noNumbersList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Fragment.noNumbersList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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
fun <ErrorMessage> Any.onlyNumbersList(vararg editTextList: EditText, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Activity.onlyNumbersList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Fragment.onlyNumbersList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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
fun <ErrorMessage> Any.noSpecialCharactersList(
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.noSpecialCharactersList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.noSpecialCharactersList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.atleastOneSpecialCharactersList(
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.atleastOneSpecialCharactersList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.atleastOneSpecialCharactersList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.textEqualToList(
    target: String,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.textEqualToList(
    target: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.textEqualToList(
    target: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.textNotEqualToList(
    target: String,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.textNotEqualToList(
    target: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.textNotEqualToList(
    target: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.startsWithList(
    target: String,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.startsWithList(
    target: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.startsWithList(
    target: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.endssWithList(
    target: String,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.endssWithList(
    target: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.endssWithList(
    target: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.containsList(
    target: String,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.containsList(
    target: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.containsList(
    target: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.notContainsList(
    target: String,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.notContainsList(
    target: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.notContainsList(
    target: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.creditCardNumberList(
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.creditCardNumberList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.creditCardNumberList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.creditCardNumberWithSpacesList(
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.creditCardNumberWithSpacesList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.creditCardNumberWithSpacesList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.creditCardNumberWithDashesList(
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.creditCardNumberWithDashesList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.creditCardNumberWithDashesList(
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
fun <ErrorMessage> Any.validUrlList(vararg editTextList: EditText, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Activity.validUrlList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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

fun <ErrorMessage> Fragment.validUrlList(vararg editTextIds: Int, callback: (view: EditText, message: ErrorMessage?) -> Unit): Boolean {
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
fun <ErrorMessage> Any.regexList(
    pattern: String,
    vararg editTextList: EditText,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Activity.regexList(
    pattern: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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

fun <ErrorMessage> Fragment.regexList(
    pattern: String,
    vararg editTextIds: Int,
    callback: (view: EditText, message: ErrorMessage?) -> Unit
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
