package com.kotlinlibrary.utils.imagepick

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.ContextWrapper
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kotlinlibrary.R
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PickMediaExtensions {
    fun setInternalStorage(isInternal: Boolean) {
        if (isInternal) {
            IMAGE_CONTENT_URL = MediaStore.Images.Media.INTERNAL_CONTENT_URI
            VIDEO_CONTENT_URL = MediaStore.Video.Media.INTERNAL_CONTENT_URI
        } else {
            IMAGE_CONTENT_URL = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            VIDEO_CONTENT_URL = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        }
    }

    fun pickFromCamera(context: Context, callback: (Int, String) -> Unit) =
        requestPhotoPick(
            context,
            PICK_FROM_CAMERA,
            callback
        )

    fun pickFromCamera(context: Context, callback: F2<Int, String>?) =
        requestPhotoPick(
            context,
            PICK_FROM_CAMERA
        ) { code, uri ->
            callback?.invoke(code, uri)
        }

    fun pickFromGallery(context: Context, callback: (Int, String) -> Unit) =
        requestPhotoPick(
            context,
            PICK_FROM_GALLERY,
            callback
        )

    fun pickFromGallery(context: Context, callback: F2<Int, String>?) =
        requestPhotoPick(
            context,
            PICK_FROM_GALLERY
        ) { code, uri ->
            callback?.invoke(code, uri)
        }

    fun pickFromVideo(context: Context, callback: (Int, String) -> Unit) =
        requestPhotoPick(
            context,
            PICK_FROM_VIDEO,
            callback
        )

    fun pickFromVideo(context: Context, callback: F2<Int, String>?) =
        requestPhotoPick(
            context,
            PICK_FROM_VIDEO
        ) { code, uri ->
            callback?.invoke(code, uri)
        }

    fun pickFromVideoCamera(context: Context, callback: (Int, String) -> Unit) =
        requestPhotoPick(
            context,
            PICK_FROM_CAMERA_VIDEO,
            callback
        )

    fun pickFromVideoCamera(context: Context, callback: F2<Int, String>?) =
        requestPhotoPick(
            context,
            PICK_FROM_CAMERA_VIDEO
        ) { code, uri ->
            callback?.invoke(code, uri)
        }

    class ResultFragment() : Fragment() {
        companion object {
            private const val REQUEST_PERMISSION = 1001
            private const val NEEDED_PERMISSIONS = 1002
        }

        lateinit var mContext: Context
        var mRequestCode: Int = 0
        private var fm: FragmentManager? = null
        var callback: ((Int, String) -> Unit)? = null

        @SuppressLint("ValidFragment")
        constructor(fm: FragmentManager, callback: (Int, String) -> Unit) : this() {
            this.fm = fm
            this.callback = callback
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setHasOptionsMenu(false)
            activity?.let {
                mContext = it
            }
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            mContext = context
        }

        fun checkPermissions(arrays: Array<String>, code: Int) {
            mRequestCode = code
            when {
                Build.VERSION.SDK_INT < 23 -> grantedPermission(mRequestCode)
                mContext.isGranted(arrays).isEmpty() -> grantedPermission(mRequestCode)
                (mContext as Activity).isRationale(arrays).isNotEmpty() -> rationalePermission(arrays)
                else -> requestPermissions(arrays,
                    REQUEST_PERMISSION
                )
            }
        }

        fun rationalPermissions(arrays: Array<String>, code: Int) {
            mRequestCode = code
            requestPermissions(arrays,
                NEEDED_PERMISSIONS
            )
        }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
            val notGranted: Array<String> = mContext.isGranted(permissions)
            val notDenied: Array<String> = (mContext as Activity).isRationale(permissions)
            if (requestCode == REQUEST_PERMISSION || requestCode == NEEDED_PERMISSIONS) {
                when {
                    notGranted.isEmpty() -> {
                        grantedPermission(mRequestCode)
                    }
                    notDenied.isEmpty() -> {
                        deniedPermission()
                    }
                    else -> {
                        rationalePermission(notDenied)
                    }
                }
            } else {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }

        fun grantedPermission(requestCode: Int) {
            requestPhotoPick(
                mContext,
                requestCode,
                callback as ((Int, String) -> Unit)
            )
            fm?.beginTransaction()?.remove(this)?.commitAllowingStateLoss()
        }

        fun rationalePermission(arrays: Array<String>) {
            mContext.alert(
                mContext.getString(R.string.permission_desc_rational_photo),
                mContext.getString(R.string.permission_title_photo),
                mContext.getString(R.string.btn_proceed), false, callback = { _, which ->
                when (which) {
                    DialogInterface.BUTTON_NEGATIVE -> {
                        failedPermission()
                    }
                    DialogInterface.BUTTON_POSITIVE -> {
                        rationalPermissions(arrays, mRequestCode)
                    }
                    else -> {
                        failedPermission()
                    }
                }
            })
        }

        fun deniedPermission() {
            callback?.invoke(PICK_PERMISSION_DENIED, "")
            fm?.beginTransaction()?.remove(this)?.commitAllowingStateLoss()
        }

        fun failedPermission() {
            fm?.beginTransaction()?.remove(this)?.commitAllowingStateLoss()
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            when (requestCode) {
                PICK_FROM_CAMERA ->
                    if (resultCode == Activity.RESULT_OK) {
                        try {
                            currentPhotoPath?.let {
                                val path = Uri.parse(it).getRealPath(mContext)
                                callback?.invoke(PICK_SUCCESS, path!!)
                            }
                        } catch (e: Exception) {
                            logs(e, LogType.ERROR)
                            try {
                                currentPhotoPath?.let {
                                    val path = Uri.parse(it).getRealPathFromURI(mContext)
                                    callback?.invoke(PICK_SUCCESS, path!!)
                                }
                            } catch (e: Exception) {
                                logs(e, LogType.ERROR)
                                toast("Fail to get image path, Please try again later.")
                            }
                        }
                    }
                PICK_FROM_GALLERY ->
                    if (resultCode == Activity.RESULT_OK) {
                        try {
                            callback?.invoke(PICK_SUCCESS, data?.data?.getRealPath((mContext)) as String)
                        } catch (e: Exception) {
                            logs(e, LogType.ERROR)
                            try {
                                val path = data?.data?.getRealPathFromURI(mContext)
                                callback?.invoke(PICK_SUCCESS, path!!)
                            } catch (e: Exception) {
                                logs(e, LogType.ERROR)
                                toast("Fail to get image path, Please try again later.")
                            }
                        }
                    }
                PICK_FROM_VIDEO ->
                    if (resultCode == Activity.RESULT_OK) {
                        try {
                            callback?.invoke(PICK_SUCCESS, data?.data?.getRealPath((mContext)) as String)
                        } catch (e: Exception) {
                            logs(e, LogType.ERROR)
                            try {
                                val path = data?.data?.getRealPathFromURI(mContext)
                                callback?.invoke(PICK_SUCCESS, path!!)
                            } catch (e: Exception) {
                                logs(e, LogType.ERROR)
                                toast("Fail to get image path, Please try again later.")
                            }
                        }
                    }
                PICK_FROM_CAMERA_VIDEO ->
                    if (resultCode == Activity.RESULT_OK) {
                        try {
                            var path = data?.data?.getRealPath(mContext) as String
                            if (path.isEmpty()) {
                                path = currentVideoPath as String
                            }

                            path.let {
                                callback?.invoke(PICK_SUCCESS, path)
                            }
                        } catch (e: Exception) {
                            logs(e, LogType.ERROR)
                            try {
                                var path = data?.data?.getRealPathFromURI(mContext)
                                if (path == null || path.isEmpty()) {
                                    path = currentVideoPath as String
                                }

                                path.let {
                                    callback?.invoke(PICK_SUCCESS, path)
                                }
                            } catch (e: Exception) {
                                logs(e, LogType.ERROR)
                                toast("Fail to get image path, Please try again later.")
                            }
                        }
                    }
            }

            fm?.beginTransaction()?.remove(this)?.commit()
        }
    }

    companion object {
        @JvmField
        var instance: PickMediaExtensions =
            PickMediaExtensions()
        var IMAGE_CONTENT_URL = MediaStore.Images.Media.EXTERNAL_CONTENT_URI!!
        var VIDEO_CONTENT_URL = MediaStore.Video.Media.EXTERNAL_CONTENT_URI!!
        var currentPhotoPath: String? = null
        var currentVideoPath: String? = null
        const val PICK_FROM_CAMERA = 0
        const val PICK_FROM_GALLERY = 1
        const val PICK_FROM_VIDEO = 2
        const val PICK_FROM_CAMERA_VIDEO = 3
        const val PICK_SUCCESS = 3
        const val PICK_PERMISSION_DENIED = 1
    }
}

private fun getActivity(context: Context): AppCompatActivity? {
    var c = context

    while (c is ContextWrapper) {
        if (c is AppCompatActivity) {
            return c
        }
        c = c.baseContext
    }
    return null
}

private fun createImageUri(context: Context): Uri? {
    val contentResolver = context.contentResolver
    val cv = ContentValues()
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    cv.put(MediaStore.Images.Media.TITLE, timeStamp)
    return contentResolver.insert(PickMediaExtensions.IMAGE_CONTENT_URL, cv)
}

private fun createVideoUri(context: Context): Uri? {
    val contentResolver = context.contentResolver
    val cv = ContentValues()
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    cv.put(MediaStore.Images.Media.TITLE, timeStamp)
    return contentResolver.insert(PickMediaExtensions.VIDEO_CONTENT_URL, cv)
}

fun requestPhotoPick(context: Context, pickType: Int, callback: (Int, String) -> Unit) {
    val fm = getActivity(context)?.supportFragmentManager
    val fragment = PickMediaExtensions.ResultFragment(fm as FragmentManager, callback)

    fm.beginTransaction().add(fragment, "FRAGMENT_TAG").commitAllowingStateLoss()
    fm.executePendingTransactions()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
        (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
        fragment.checkPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA), pickType)
        return
    }
    val intent = Intent()

    when (pickType) {
        PickMediaExtensions.PICK_FROM_CAMERA -> {
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            val captureUri = createImageUri(context)
            PickMediaExtensions.currentPhotoPath = captureUri.toString()
            intent.putExtra(MediaStore.EXTRA_OUTPUT, captureUri)
        }
        PickMediaExtensions.PICK_FROM_GALLERY -> {
            intent.action = Intent.ACTION_PICK
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
        }
        PickMediaExtensions.PICK_FROM_VIDEO -> {
            intent.action = Intent.ACTION_PICK
            intent.type = MediaStore.Video.Media.CONTENT_TYPE
        }
        PickMediaExtensions.PICK_FROM_CAMERA_VIDEO -> {
            intent.action = MediaStore.ACTION_VIDEO_CAPTURE
            val captureUri = createVideoUri(context)
            PickMediaExtensions.currentVideoPath = captureUri.toString()
            intent.putExtra(MediaStore.EXTRA_OUTPUT, captureUri)
        }
    }

    fragment.startActivityForResult(intent, pickType)
}

fun verifyPermissions(grantResults: IntArray): Boolean =
    if (grantResults.isEmpty()) false else grantResults.none { it != PackageManager.PERMISSION_GRANTED }
