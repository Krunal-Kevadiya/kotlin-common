package com.kotlincommon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_with_progress).setOnClickListener {
            startActivity(Intent(this, ButtonViewActivity::class.java))
        }

        findViewById<Button>(R.id.btn_credit_card).setOnClickListener {
            startActivity(Intent(this, CreditCardActivity::class.java))
        }

        findViewById<Button>(R.id.btn_image_picker).setOnClickListener {
            startActivity(Intent(this, ImagePickerActivity::class.java))
        }

        findViewById<Button>(R.id.btn_permission).setOnClickListener {
            startActivity(Intent(this, PermissionActivity::class.java))
        }

        findViewById<Button>(R.id.btn_permission).setOnClickListener {
            startActivity(Intent(this, PermissionActivity::class.java))
        }

        findViewById<Button>(R.id.btn_argument).setOnClickListener {
            startActivity(Intent(this, ArgumentActivity::class.java))
        }

        findViewById<Button>(R.id.btn_loadmore).setOnClickListener {
            startActivity(Intent(this, LoadMoreActivity::class.java))
        }

        findViewById<Button>(R.id.btn_preference).setOnClickListener {
            startActivity(Intent(this, PreferenceActivity::class.java))
        }

        findViewById<Button>(R.id.btn_recycle_adapter).setOnClickListener {
            startActivity(Intent(this, RecyclerAdapterActivity::class.java))
        }

        findViewById<Button>(R.id.btn_resource).setOnClickListener {
            startActivity(Intent(this, ResourceActivity::class.java))
        }

        findViewById<Button>(R.id.btn_snackbar).setOnClickListener {
            startActivity(Intent(this, SnackBarActivity::class.java))
        }

        findViewById<Button>(R.id.btn_spanner).setOnClickListener {
            startActivity(Intent(this, SpannerActivity::class.java))
        }

        findViewById<Button>(R.id.btn_statusbar_alertview).setOnClickListener {
            startActivity(Intent(this, StatusBarAlertViewActivity::class.java))
        }

        findViewById<Button>(R.id.btn_validation).setOnClickListener {
            startActivity(Intent(this, ValidationActivity::class.java))
        }

        findViewById<Button>(R.id.btn_skeleton).setOnClickListener {
            startActivity(Intent(this, SkeletonViewActivity::class.java))
        }
    }
}
