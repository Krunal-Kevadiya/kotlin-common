package com.kotlincommon

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.imagepick.PickMediaExtensions
import com.kotlinlibrary.utils.imagepick.getRealPath
import com.kotlinlibrary.utils.imagepick.getRealPathFromURI
import com.kotlinlibrary.utils.imagepick.requestMediaScanner
import com.kotlinlibrary.utils.logs
import java.io.File

class ImagePickerActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_FOR_CROP = 1000
    }

    private var imagePathLocal: String = ""
    private val imageView: ImageView by lazy {
        findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker)

        findViewById<Button>(R.id.btn_camera).setOnClickListener {
            PickMediaExtensions.instance.pickFromCamera(this) { resultCode: Int, path: String ->
                resultMesage(resultCode, path)
            }
        }

        findViewById<Button>(R.id.btn_gallery).setOnClickListener {
            PickMediaExtensions.instance.pickFromGallery(this) { resultCode: Int, path: String ->
                resultMesage(resultCode, path)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_FOR_CROP) {
            if (data != null && data.extras != null && data.hasExtra("data")) {
                data.extras?.let {
                    val bitmap = it.getParcelable<Bitmap>("data")
                    bitmap?.let { btm ->
                        imageView.setImageBitmap(btm)
                    }
                }
            } else if (resultCode == Activity.RESULT_OK) {
                imageView.setImageURI(Uri.fromFile(File(imagePathLocal)))
            }
        }
    }

    private fun resultMesage(resultCode: Int, path: String) {
        if (resultCode == PickMediaExtensions.PICK_PERMISSION_DENIED) {
            logs(getString(R.string.permission_desc_setting_photo), LogType.ERROR)
        } else if (resultCode == PickMediaExtensions.PICK_SUCCESS) {
            val realPath1 = Uri.parse(path).getRealPathFromURI(this)
            if (realPath1 != null) {
                openCrop(realPath1)
                requestMediaScanner(realPath1)
            } else {
                val realPath = Uri.parse(path).getRealPath(this)
                if (realPath != null) {
                    openCrop(realPath)
                    requestMediaScanner(realPath)
                } else {
                    logs("error unknown", LogType.ERROR)
                }
            }
        }
    }

    private fun openCrop(path: String) {
        imagePathLocal = path
        try {
            val cropIntent = Intent("com.android.camera.action.CROP").apply {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    val uri = Uri.fromFile(File(imagePathLocal))
                    setDataAndType(uri, "image/*")
                } else {
                    val uri = FileProvider.getUriForFile(this@ImagePickerActivity, BuildConfig.APPLICATION_ID + "" + ".provider", File(path))
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                    putExtra(MediaStore.EXTRA_OUTPUT, uri)
                    data = uri
                }
                putExtra("crop", true)
                putExtra("aspectX", 1)
                putExtra("aspectY", 1)
                putExtra("scale", true)
                putExtra("outputX", 300)
                putExtra("outputY", 300)
                putExtra("return-data", true)
            }
            startActivityForResult(cropIntent, REQUEST_FOR_CROP)
        } catch (e: Exception) {
            logs(e, LogType.ERROR)
            imageView.setImageURI(Uri.fromFile(File(imagePathLocal)))
        }
    }
}
