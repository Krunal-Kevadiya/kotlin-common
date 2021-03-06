package com.kotlincommon.sample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.TextInputLayout
import com.kotlincommon.sample.databinding.ActivityValidationBinding
import com.kotlinlibrary.validation.bindValidator
import com.kotlinlibrary.validation.collection.nonEmptyList
import com.kotlinlibrary.validation.validator
import com.kotlinlibrary.validation.views.validEmail

class ValidationActivity : AppCompatActivity() {
    val editTextEmail = bindValidator<Int>("", false)
        .nonEmpty(R.string.vald_email_address_empty)
        .validEmail(R.string.vald_email_address_invalid)
        .addErrorCallback { error ->
            findViewById<EditText>(R.id.editText)?.let {
                if(error != null) {
                    it.error = it.context.getString(error)
                } else {
                    it.error = null
                }
            }
        }

    val editTextPassword = bindValidator<String>("", true)
        .nonEmpty("Please enter your password.")
        .regex(".*[A-Z]+.*", "Must contain capital letters.")
        .regex(".*[0-9]+.*", "Must contain digits.")
        .regex(".*[a-z]+.*", "Must contain small letters.")
        .minLength(8, "Eight or more characters.")
        .maxLengths(16, "No more then sixteen characters.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityValidationBinding>(this, R.layout.activity_validation)
        binding.setVariable(BR.viewModel, this)

        findViewById<Button>(R.id.btnValidation).setOnClickListener {
            if (editTextEmail.check()) {
                Log.e(ValidationActivity::class.java.simpleName, "True ${editTextEmail.getError()}")
            } else {
                Log.e(ValidationActivity::class.java.simpleName, "False ${editTextEmail.getError()}")
            }
        }
    }
}

object EditTextBind {
    @JvmStatic
    @BindingAdapter("error")
    fun setTextSelection(editText: TextInputLayout, text: String?) {
        text?.let {
            editText.error = it
        }
    }
}
