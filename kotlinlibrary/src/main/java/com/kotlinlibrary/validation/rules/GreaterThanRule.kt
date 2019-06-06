package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException
import java.text.NumberFormat

class GreaterThanRule<ErrorMessage>(
    var target: Number = 0,
    var errorMsg: ErrorMessage? = null,
    clazz: Class<ErrorMessage>
) : BaseRule<ErrorMessage>(clazz) {
    var isValidNumber: Boolean = false
    private fun validNumber(text: String): Boolean {
        isValidNumber = when {
            text.isEmpty() -> false
            text.startsWith("-") -> {
                val txtNum = text.substringAfter("-")
                txtNum.matches(Regex("^[0-9]\\d*(\\.\\d+)?$"))
            }
            else -> text.matches(Regex("^[0-9]\\d*(\\.\\d+)?$"))
        }
        return isValidNumber
    }

    override fun validate(text: String): Boolean {
        if (text.isEmpty())
            return false
        // Negative
        if (text.startsWith("-")) {
            val txtNum = text.substringAfter("-")
            if (validNumber(txtNum)) {
                var number = NumberFormat.getNumberInstance().parse(txtNum)
                number = number.toFloat() * -1
                return (number.toFloat() > target.toFloat())
            }
            return false
        }
        // Positive
        else {
            if (validNumber(text)) {
                val number = NumberFormat.getNumberInstance().parse(text)
                return (number.toFloat() > target.toFloat())
            }
            return false
        }
    }

    override fun getErrorMessage(): ErrorMessage? {
        return when {
            !isValidNumber -> return when {
                typed(kotlin.String::class.java, java.lang.String::class.java) -> "Invalid Number!" as ErrorMessage
                typed(kotlin.Int::class.java, java.lang.Integer::class.java) -> R.string.vald_invalid_number as ErrorMessage
                else -> throw MismatchErrorTypeException()
            }
            errorMsg != null -> errorMsg
            typed(kotlin.String::class.java, java.lang.String::class.java) -> "Should be greater than $target." as ErrorMessage
            typed(kotlin.Int::class.java, java.lang.Integer::class.java) -> R.string.vald_should_be_greater_than_target as ErrorMessage
            else -> throw MismatchErrorTypeException()
        }
    }

    override fun setError(msg: ErrorMessage?) {
        errorMsg = msg
    }
}