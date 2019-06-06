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
import com.kotlinlibrary.validation.ValidatedObservableField

class ValidationActivity : AppCompatActivity() {
    val temp = MutableList<Int>

    val editTextEmail: ValidatedObservableField<Int> = ValidatedObservableField<Int>("", false)
        .nonEmpty()
        .validEmail()
        .addErrorCallback { error ->
            findViewById<EditText>(R.id.editText)?.let {
                if(error != null) {
                    it.error = it.context.getString(error)
                } else {
                    it.error = null
                }
            }
        }

    val editTextPassword: ValidatedObservableField<String> = ValidatedObservableField<String>("", true)
        .nonEmpty("Please enter your password.")
        .regex(".*[A-Z]+.*", "Must contain capital letters.")
        .regex(".*[0-9]+.*", "Must contain digits.")
        .regex(".*[a-z]+.*", "Must contain small letters.")
        .minLength(8, "Eight or more characters.")
        .maxLength(16, "No more then sixteen characters.")

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
