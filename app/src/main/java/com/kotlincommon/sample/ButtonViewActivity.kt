package com.kotlincommon.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlinlibrary.buttonview.ProgressButton
import com.kotlinlibrary.buttonview.utils.ResultState

class ButtonViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_view)

        findViewById<ProgressButton>(R.id.btn_normal).setOnClickListener {
            if (it is ProgressButton) {
                it.startAnimation()

                it.postDelayed({
                    it.revertAnimation()
                }, 5000)
            }
        }

        findViewById<ProgressButton>(R.id.btn_normal_with_stroke).setOnClickListener {
            if (it is ProgressButton) {
                it.startAnimation()

                it.postDelayed({
                    it.revertAnimation()
                }, 5000)
            }
        }

        findViewById<ProgressButton>(R.id.btn_gradient).setOnClickListener {
            if (it is ProgressButton) {
                it.startAnimation()

                it.postDelayed({
                    it.revertAnimation()
                }, 5000)
            }
        }

        findViewById<ProgressButton>(R.id.btn_gradient_rect).setOnClickListener {
            if (it is ProgressButton) {
                it.startAnimation()

                it.postDelayed({
                    it.revertAnimation()
                }, 5000)
            }
        }

        findViewById<ProgressButton>(R.id.btn_normal_icon).setOnClickListener {
            if (it is ProgressButton) {
                it.startAnimation()

                it.postDelayed({
                    it.revertAnimation()
                }, 5000)
            }
        }

        findViewById<ProgressButton>(R.id.btn_normal_icon_stroke).setOnClickListener {
            if (it is ProgressButton) {
                it.startAnimation()

                it.postDelayed({
                    it.setResultState(ResultState.SUCCESS)
                }, 5000)

                it.postDelayed({
                    it.revertAnimation()
                }, 7000)
            }
        }

        findViewById<ProgressButton>(R.id.btn_normal_state).setOnClickListener {
            if (it is ProgressButton) {
                it.startAnimation()

                it.postDelayed({
                    it.setResultState(ResultState.FAILURE)
                }, 5000)

                it.postDelayed({
                    it.revertAnimation()
                }, 7000)
            }
        }

        findViewById<ProgressButton>(R.id.btn_gradient_state_revert).setOnClickListener {
            if (it is ProgressButton) {
                it.startAnimation()

                it.postDelayed({
                    it.setResultState(ResultState.FAILURE)
                }, 5000)
            }
        }

        findViewById<ProgressButton>(R.id.btn_stroke).setOnClickListener {
            if (it is ProgressButton) {
                it.startAnimation()

                it.postDelayed({
                    it.revertAnimation()
                }, 5000)
            }
        }
    }
}
