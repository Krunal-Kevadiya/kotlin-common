package com.kotlinlibrary.validation.rules

import com.kotlinlibrary.R
import com.kotlinlibrary.validation.MismatchErrorTypeException
import com.kotlinlibrary.validation.datatype.validNumber
import java.text.NumberFormat

class LessThanRule<ErrorMessage>(
    var target: Number = 0,
    var errorMsg: ErrorMessage? = null
) : BaseRule<ErrorMessage> {
    override fun validate(text: String): Boolean {
        if (text.isEmpty())
            return false
        // Negative
        if (text.startsWith("-")) {
            val txtNum = text.substringAfter("-")
            if (txtNum.validNumber<ErrorMessage>()) {
                var number = NumberFormat.getNumberInstance().parse(txtNum)
                number = number.toFloat() * -1
                return (number.toFloat() < target.toFloat())
            }
            return false
        }
        // Positive
        else {
            if (text.validNumber<ErrorMessage>()) {
                val number = NumberFormat.getNumberInstance().parse(text)
                return (number.toFloat() < target.toFloat())
            }
            return false
        }
    }

    override fun getErrorMessage(): ErrorMessage? {
        return when {
            errorMsg == null -> null
            errorMsg != null -> errorMsg!!
            errorMsg is String -> "Should be less than $target." as ErrorMessage
            errorMsg is Int -> R.string.vald_should_be_less_than_target as ErrorMessage
            else -> throw MismatchErrorTypeException()
        }
    }

    override fun setError(msg: ErrorMessage?) {
        errorMsg = msg
    }
}