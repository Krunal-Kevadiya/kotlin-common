package com.kotlinlibrary.validation

import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.Observable

inline fun <reified ErrorMessage : Any> Any.bindValidator(
    value: String,
    onChange: Boolean = false,
    vararg dependencies: Observable
): ValidatorObservableField<ErrorMessage> {
    return ValidatorObservableField(value, onChange, ErrorMessage::class.java, *dependencies)
}

inline fun <reified ErrorMessage : Any> Any.bindValidator(
    value: String
): Validator<ErrorMessage> {
    return Validator(value, ErrorMessage::class.java)
}

// Views
inline fun <reified ErrorMessage : Any> String.validator(): Validator<ErrorMessage> {
    return Validator(this, ErrorMessage::class.java)
}

inline fun <reified ErrorMessage : Any> TextView.validator(): Validator<ErrorMessage> {
    return Validator(text.toString(), ErrorMessage::class.java)
}

inline fun <reified ErrorMessage : Any> Spinner.validator(): Validator<ErrorMessage> {
    return Validator(this.selectedItem.toString(), ErrorMessage::class.java)
}

inline fun <reified ErrorMessage : Any> EditText.validator(): Validator<ErrorMessage> {
    return Validator(text.toString(), ErrorMessage::class.java)
}

inline fun <reified ErrorMessage : Any> AutoCompleteTextView.validator(): Validator<ErrorMessage> {
    return Validator(text.toString(), ErrorMessage::class.java)
}