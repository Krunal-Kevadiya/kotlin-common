package com.kotlincommon

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kotlinlibrary.utils.spanner.span

class SpannerActivity : AppCompatActivity() {

    private val textView: TextView by lazy {
        findViewById<TextView>(R.id.textView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spanner)

        val spanRegister = span {
            foregroundColor(ContextCompat.getColor(this@SpannerActivity, R.color.colorPrimary)) {
                fontface(this@SpannerActivity, R.font.montserrat_light) {
                    +getString(R.string.app_name)
                }
            }
            foregroundColor(ContextCompat.getColor(this@SpannerActivity, R.color.colorPrimaryDark)) {
                fontface(this@SpannerActivity, R.font.montserrat_regular) {
                    +" ${getString(R.string.app_name)}"
                }
            }
        }
        textView.text = spanRegister.build()
    }
}