package com.kotlinlibrary.aws

import android.content.Intent
import android.text.TextUtils
import androidx.core.app.JobIntentService
import com.kotlinlibrary.BuildConfig
import com.kotlinlibrary.R
import java.io.File

class ImageUploadService : JobIntentService() {
    companion object {
        const val INTENT_FILTER_IMAGE_UPLOAD = "file_upload_percentage"
        const val EXTRA_PERCENTAGE = "${BuildConfig.APPLICATION_ID}.EXTRA_PERCENTAGE"
        const val EXTRA_STATUS = "${BuildConfig.APPLICATION_ID}.EXTRA_STATUS"
        const val EXTRA_URL = "${BuildConfig.APPLICATION_ID}.EXTRA_URL"
        const val EXTRA_BUCKET = "${BuildConfig.APPLICATION_ID}.EXTRA_BUCKET"
        const val EXTRA_OPTION = "${BuildConfig.APPLICATION_ID}.EXTRA_OPTION"
    }

    override fun onHandleWork(intent: Intent) {
        if (intent.hasExtra(EXTRA_URL) && intent.hasExtra(EXTRA_BUCKET) && intent.hasExtra(EXTRA_OPTION)) {
            val path = intent.getStringExtra(EXTRA_URL)
            val bucket = intent.getStringExtra(EXTRA_BUCKET)
            val option = intent.getParcelableExtra<AmazonOption.Builder>(EXTRA_OPTION)
            if (!TextUtils.isEmpty(path)) {
                val file = File(path)
                if (file.length() > 0) {
                    val amazonManager = AmazonManager.getInstance(applicationContext, option)
                    amazonManager.uploadImage(file, bucket, object : ImageUploadListener {
                        override fun imageUploadProgress(percentage: Int) {
                            setTotalPercentage(percentage, null)
                        }

                        override fun imageUploadCompleted(url: String) {
                            setTotalPercentage(100, url)
                        }

                        override fun imageUploadFailure(message: String) {
                            uploadFail(message)
                        }
                    })
                } else {
                    uploadFail(getString(R.string.error_file_size))
                }
            } else {
                uploadFail(getString(R.string.error_file_size))
            }
        } else {
            uploadFail(getString(R.string.error_file_size))
        }
    }

    private fun uploadFail(message: String?) {
        if (message != null) {
            setFailure(message)
        } else {
            setFailure(getString(R.string.error_unknown))
        }
    }

    private fun setTotalPercentage(percentage: Int, url: String?) {
        val intent = Intent(INTENT_FILTER_IMAGE_UPLOAD).apply {
            putExtra(EXTRA_STATUS, true)
            putExtra(EXTRA_PERCENTAGE, percentage)
            url?.let { link ->
                putExtra(EXTRA_URL, link)
            }
        }

        sendBroadcast(intent)
    }

    private fun setFailure(msg: String) {
        val intent = Intent(INTENT_FILTER_IMAGE_UPLOAD).apply {
            putExtra(EXTRA_STATUS, false)
            putExtra(EXTRA_URL, msg)
        }
        sendBroadcast(intent)
    }
}
