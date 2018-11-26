package com.kotlincommon

import android.Manifest
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kotlinlibrary.permission.KotlinPermissions
import com.kotlinlibrary.permission.PermissionAkt
import com.kotlinlibrary.permission.bindPermission
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs

class PermissionActivity : AppCompatActivity() {

    private val permissionCore: KotlinPermissions.PermissionCore by
    bindPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE) {
        when (it) {
            PermissionAkt.ACCEPTED -> {
                logs("Granted", LogType.ERROR)
            }
            PermissionAkt.DENIED -> {
                logs("Denied", LogType.ERROR)
                permissionCore.ask()
            }
            PermissionAkt.FOREVER_DENIED -> {
                logs("Forever denied", LogType.ERROR)
            }
            else -> {
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        findViewById<Button>(R.id.btn_permission).setOnClickListener {
            permissionCore.ask()
        }
    }
}

/*
KotlinPermissions.with(this)
    .permissions(Manifest.permission.CAMERA)
    .onAccepted {
        setCameraStatus("Granted")
    }
    .onDenied {
        setCameraStatus("Denied")
    }
    .onForeverDenied {
        setCameraStatus("Forever denied")
    }
    .ask()


private var permissionCore: KotlinPermissions.PermissionCore? = null
permissionCore = KotlinPermissions.with(activity!!)
                .permissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .onAccepted {
                    setLocationStatus("Granted")
                }
                .onDenied {
                    setLocationStatus("Denied")
                    permissionCore?.ask()
                }
                .onForeverDenied {
                    setLocationStatus("Forever denied")
                }
permissionCore?.ask()
*/
