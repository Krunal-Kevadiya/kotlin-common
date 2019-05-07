package com.kotlincommon.sample

import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kotlinlibrary.permission.RuntimePermission
import com.kotlinlibrary.permission.bindPermission
import com.kotlinlibrary.permission.callback.PermissionCallback

class PermissionActivity : AppCompatActivity() {

    private val textView: TextView by lazy {
        findViewById<TextView>(R.id.textView)
    }

    fun appendText(textView: TextView, text: String) {
        textView.post { textView.text = textView.text.toString() + "\n" + text }
    }

    // Using Lazy DSL
    private val permission: RuntimePermission by
        bindPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE) {
            setResult(it)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        findViewById<Button>(R.id.btn_permission).setOnClickListener {
            // Using Extension DSL
            /*askPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE) {
                setResult(it)
            }.ask()*/

            // Using Manually
            /*RuntimePermission(this@PermissionActivity)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .onResponse { setResult(it) }
                .ask()*/
            permission.ask()
        }
    }

    private fun setResult(it: PermissionCallback) {
        with(it) {
            if (hasAccepted()) {
                textView.text = "Accepted :$accepted"
            }

            if (hasDenied()) {
                appendText(textView, "Denied :")
                appendText(textView, "$denied")

                AlertDialog.Builder(this@PermissionActivity)
                    .setMessage("Please accept our permissions to denied")
                    .setPositiveButton("yes") { dialog, which ->
                        askAgain()
                    }
                    .setNegativeButton("no") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }

            if (hasForeverDenied()) {
                appendText(textView, "ForeverDenied :")
                appendText(textView, "$foreverDenied")

                AlertDialog.Builder(this@PermissionActivity)
                    .setMessage("Please accept our permissions foreverDenied")
                    .setPositiveButton("yes") { dialog, which ->
                        goToSettings()
                    }
                    .setNegativeButton("no") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}
