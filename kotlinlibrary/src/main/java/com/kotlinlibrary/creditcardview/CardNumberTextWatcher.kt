package com.kotlinlibrary.creditcardview

import android.os.Build
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import com.kotlinlibrary.R
import com.kotlinlibrary.utils.ktx.logs

abstract class CardNumberTextWatcher(private val mCardTextInputLayout: CardTextInputLayout) : TextWatcher {
    private var currentText = ""

    init {
        currentText = mCardTextInputLayout.editText!!.text.toString()
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(source1: CharSequence, start: Int, before: Int, count: Int) {
        var source = source1
        val card = setCardIcon(source.toString())
        mCardTextInputLayout.error = ""
        if (source.toString() != currentText) {
            source = source.toString().replace(CardValidator.CARD_SEPARATORS, "")
            val result = StringBuilder()
            for (i in 0 until source.length) {
                if (i % 4 == 0 && i != 0) {
                    result.append(CardValidator.CARD_SEPARATORS)
                }

                result.append(source[i])
            }
            currentText = result.toString()
            mCardTextInputLayout.editText!!.setText(result.toString())
            mCardTextInputLayout.editText!!.setSelection(currentText.length)
        }
        try {
            mCardTextInputLayout.passwordVisibilityToggleRequested()
        } catch (e: NoSuchFieldException) {
            logs(e)
        } catch (e: IllegalAccessException) {
            logs(e)
        }
        val moveToNext: Boolean
        moveToNext = when {
            card == null -> false
            card.possibleLengths.contains(
                currentText.replace(CardValidator.CARD_SEPARATORS, "").length
            ) -> CardValidator(currentText).isValidCardNumber
            else -> false
        }

        mCardTextInputLayout.setHasValidInput(moveToNext)
        onValidated(moveToNext, currentText, card)
    }

    override fun afterTextChanged(s: Editable) {
    }

    private fun setCardIcon(source: String): Card? {
        val card = CardValidator(source).guessCard()
        val filterArray = arrayOfNulls<InputFilter>(1)
        if (card != null) {
            val maxLength = Integer.parseInt(card.maxLength.toString())
            filterArray[0] = InputFilter.LengthFilter(getSpacedPanLength(maxLength))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mCardTextInputLayout.editText!!.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    ContextCompat.getDrawable(mCardTextInputLayout.context, card.drawable),
                    null
                )
            }
        } else {
            filterArray[0] = InputFilter.LengthFilter(getSpacedPanLength(19))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mCardTextInputLayout.editText!!.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    ContextCompat.getDrawable(mCardTextInputLayout.context, R.drawable.payment_ic_generic),
                    null
                )
            }
        }

        mCardTextInputLayout.editText!!.filters = filterArray
        return card
    }

    private fun getSpacedPanLength(length: Int): Int {
        return if (length % 4 == 0) length + length / 4 - 1 else length + length / 4
    }

    protected abstract fun onValidated(moveToNext: Boolean, cardPan: String, cardInfo: Card?)
}
