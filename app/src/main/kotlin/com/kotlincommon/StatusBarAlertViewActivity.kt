package com.kotlincommon

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.kotlinlibrary.statusbaralert.StatusBarAlert
import com.kotlinlibrary.statusbaralert.StatusBarAlertView

class StatusBarAlertViewActivity : AppCompatActivity() {

    private var statusBarProgress: StatusBarAlertView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statusbar_alertview)

        findViewById<Button>(R.id.btn_1).setOnClickListener {
            showAlertView(progressBar())
        }
        findViewById<Button>(R.id.btn_2).setOnClickListener {
            showAlertView(redAlertBar())
        }
        findViewById<Button>(R.id.btn_3).setOnClickListener {
            showAlertView(blinkBar())
        }
    }

    override fun onDestroy() {
        StatusBarAlert.hide(this, Runnable{})
        super.onDestroy()
    }

    private fun showAlertView(alertView: StatusBarAlertView?) {
        if(statusBarProgress != null) {
            statusBarProgress?.hideIndeterminateProgress(statusBarProgress!!)
            statusBarProgress = alertView
            statusBarProgress?.showIndeterminateProgress()
        } else {
            statusBarProgress = alertView
            statusBarProgress?.showIndeterminateProgress()
        }
    }

    private fun progressBar(): StatusBarAlertView? {
        //return progressMessage(msg = "Please wait") available extention
        return StatusBarAlert.Builder(this)
            .autoHide(true)
            .withDuration(5000)
            .withAddedStack(false)
            .showProgress(true)
            .showTextAnimation(false)
            .withText("Please wait")
            .withAlertColor(R.color.color_type_default)
            .build()
    }

    private fun redAlertBar(): StatusBarAlertView? {
        return StatusBarAlert.Builder(this)
            .autoHide(true)
            .withDuration(100)
            .withAddedStack(false)
            .showProgress(false)
            .withText("RED ALERT!")
            .withTypeface(Typeface.createFromAsset(assets, "BeautifulAndOpenHearted.ttf"))
            .withAlertColor(R.color.red)
            .build()
    }

    private fun blinkBar(): StatusBarAlertView? {
        return ResourcesCompat.getFont(this, R.font.montserrat_light)?.let {
            StatusBarAlert.Builder(this)
                .autoHide(true)
                .withDuration(100)
                .withAddedStack(false)
                .showProgress(false)
                .withText("BLINK!")
                .withTypeface(it)
                .withAlertColor(R.color.green)
                .build()
        }
    }
}