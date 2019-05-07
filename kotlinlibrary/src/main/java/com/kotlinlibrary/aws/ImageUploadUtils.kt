package com.kotlinlibrary.aws

import android.content.Context
import com.amazonaws.AmazonClientException
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.kotlinlibrary.R
import com.kotlinlibrary.retrofitadapter.hasConnection
import com.kotlinlibrary.utils.ktx.logs
import java.io.File
import java.util.Calendar

fun AmazonManager.uploadImage(file: File, bucket: String, event: ImageUploadListener) {
    var listener: ImageUploadListener? = event
    try {
        val amazonS3 = getAmazonS3Client()
        val transferUtility = getTransferUtility()

        if (file.length() > 0) {
            val androidId = "".random(15)
            val dateString = Calendar.getInstance().timeInMillis
            val fileName = "${dateString}_${androidId}_capture.png"
            val bucketPath = "${option.defaultBucket}/$bucket"
            val observer = transferUtility.upload(bucketPath, fileName, file, CannedAccessControlList.PublicReadWrite)
            observer.setTransferListener(object : TransferListener {
                override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
                    var percentage = 0
                    if (bytesTotal != 0L) {
                        percentage = (bytesCurrent * 100 / bytesTotal).toInt()
                        if (percentage == 100) {
                            listener?.imageUploadCompleted(amazonS3.getResourceUrl(bucketPath, fileName))
                            listener = null
                        } else {
                            upload(percentage, listener)
                        }
                    } else {
                        upload(percentage, listener)
                    }
                }

                override fun onStateChanged(id: Int, state: TransferState?) {
                    context.get()?.let { context ->
                        if (!context.hasConnection) {
                            uploadFail(context, context.getString(R.string.msg_no_internet_available), listener)
                        }
                    }
                }

                override fun onError(id: Int, ex: Exception?) {
                    context.get()?.let { context ->
                        uploadFail(context, ex?.message, listener)
                    }
                }
            })
        } else {
            context.get()?.let { context ->
                uploadFail(context, context.getString(R.string.error_file_size), listener)
            }
        }
    } catch (e: AmazonClientException) {
        logs(e)
        context.get()?.let { context ->
            uploadFail(context, e.message, listener)
        }
    } catch (e: Exception) {
        logs(e)
        context.get()?.let { context ->
            uploadFail(context, e.message, listener)
        }
    }
}

fun upload(percentage: Int, listener: ImageUploadListener?) {
    listener?.imageUploadProgress(percentage)
}

fun uploadFail(context: Context, message: String?, listener: ImageUploadListener?) {
    if (message != null) {
        listener?.imageUploadFailure(message)
    } else {
        listener?.imageUploadFailure(context.getString(R.string.error_unknown))
    }
}

interface ImageUploadListener {
    fun imageUploadProgress(percentage: Int)
    fun imageUploadCompleted(url: String)
    fun imageUploadFailure(message: String)
}

fun String.random(length: Int = 8): String {
    val base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var randomString = ""

    for (i in 1..length) {
        val randomValue = (0..base.count()).random()
        randomString += "${base[randomValue]}"
    }
    return randomString
}
