package com.kotlincommon.sample

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.kotlinlibrary.buttonview.RoundButton
import java.util.*


class ButtonViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_view)

        findViewById<RoundButton>(R.id.fab_login).setOnClickListener {
            if (it is RoundButton) {
                it.startAnimation()

                it.postDelayed({
                    it.revertAnimation()
                }, 5000)
            }
        }

        findViewById<RoundButton>(R.id.btn_login).setOnClickListener {
            if (it is RoundButton) {
                it.startAnimation()

                it.postDelayed({
                    it.revertAnimation()
                }, 5000)
            }
        }

        findViewById<RoundButton>(R.id.btn_messanger).setOnClickListener {
            if (it is RoundButton) {
                it.startAnimation()

                it.postDelayed({
                    it.setResultState(RoundButton.ResultState.SUCCESS);
                }, 3000)

                it.postDelayed({
                    val builder = RoundButton.newBuilder()
                        .withText("Sended")
                        .withBackgroundColor(Color.TRANSPARENT)
                        .withTextColor(Color.DKGRAY)
                        .withWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                        .withCornerRadius(0)
                        .withCornerWidth(0)
                    it.setCustomizations(builder)
                }, 5000)
            }
        }

        findViewById<RoundButton>(R.id.btn_custom).setOnClickListener {
            if (it is RoundButton) {
                it.startAnimation()

                it.postDelayed({
                    it.revertAnimation()
                }, 5000)
            }
        }

        findViewById<RoundButton>(R.id.btn_standard).setOnClickListener {
            if (it is RoundButton) {
                it.startAnimation()

                it.postDelayed({
                    it.revertAnimation()
                }, 5000)
            }
        }

        findViewById<RoundButton>(R.id.btn_dot).setOnClickListener {
            if (it is RoundButton) {
                it.startAnimation()

                it.postDelayed({
                    it.revertAnimation()
                }, 5000)
            }
        }

        findViewById<RoundButton>(R.id.btn_round).setOnClickListener {
            if (it is RoundButton) {
                it.startAnimation()

                it.postDelayed({
                    it.revertAnimation()
                }, 5000)
            }
        }

        findViewById<RoundButton>(R.id.btn_alpha).setOnClickListener {
            if (it is RoundButton) {
                if (it.isAnimating) {
                    it.setResultState(RoundButton.ResultState.FAILURE)
                } else {
                    it.startAnimation()
                    it.postDelayed({
                        it.revertAnimation()
                    }, 5000)
                }
            }
        }

        findViewById<RoundButton>(R.id.btn_success).setOnClickListener {
            if (it is RoundButton) {
                it.startAnimation()
                it.postDelayed({
                    it.setResultState(RoundButton.ResultState.SUCCESS)
                }, 3000)
                it.postDelayed({
                    it.revertAnimation()
                }, 7000)
            }
        }

        findViewById<RoundButton>(R.id.btn_failure).setOnClickListener {
            if (it is RoundButton) {
                it.startAnimation()
                it.postDelayed({
                    it.setResultState(RoundButton.ResultState.FAILURE)
                }, 3000)
                it.postDelayed({
                    it.revertAnimation()
                }, 7000)
            }
        }

        findViewById<RoundButton>(R.id.btn_bonus).setOnClickListener {
            if (it is RoundButton) {
                it.startAnimation()

                it.postDelayed({
                    val random = Random()
                    val builder = RoundButton.newBuilder()
                        .withText("Bonus")
                        .withBackgroundColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)))
                        .withTextColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)))
                        .withCornerColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)))
                        .withCornerRadius(random.nextInt(20))
                        .withCornerWidth(random.nextInt(20))
                    it.setCustomizations(builder)
                    it.revertAnimation()
                }, 5000)
            }
        }
    }
}
