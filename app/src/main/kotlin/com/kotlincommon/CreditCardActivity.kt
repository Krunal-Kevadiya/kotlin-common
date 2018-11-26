package com.kotlincommon

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kotlinlibrary.creditcardview.Card
import com.kotlinlibrary.creditcardview.CardNumberTextWatcher
import com.kotlinlibrary.creditcardview.CardTextInputLayout
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs

class CreditCardActivity : AppCompatActivity() {

    val edtCardNumber: CardTextInputLayout by lazy {
        findViewById<CardTextInputLayout>(R.id.edt_card_number)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_card)


        edtCardNumber.post {
            edtCardNumber.editText?.addTextChangedListener(object : CardNumberTextWatcher(edtCardNumber) {
                override fun onValidated(moveToNext: Boolean, cardPan: String, cardInfo: Card?) {
                    logs(cardPan, LogType.ERROR)
                    logs((cardInfo?.cvvLength ?: 3), LogType.ERROR)
                }
            })
        }

        findViewById<Button>(R.id.btn_submit).setOnClickListener {
            if (!edtCardNumber.hasValidInput()) {
                logs("cart number invalid", LogType.ERROR)
            }
        }
    }
}
