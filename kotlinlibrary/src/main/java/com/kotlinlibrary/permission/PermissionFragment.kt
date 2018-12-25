package com.kotlinlibrary.permission

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment

import java.util.ArrayList

class PermissionFragment : Fragment() {
    private val permissionsList = ArrayList<String>()
    private var listener: ((List<String>, List<String>, List<String>) -> Unit)? = null

    init {
        retainInstance = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arguments = arguments
        if (arguments != null) {
            val permissionsArgs = arguments.getStringArrayList(LIST_PERMISSIONS)
            permissionsArgs?.let {
                permissionsList.addAll(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (permissionsList.size > 0) {
            requestPermissions(permissionsList.toTypedArray(), REQUEST_CODE)
        } else {
            fragmentManager?.beginTransaction()?.remove(this)?.commitAllowingStateLoss()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE && permissions.isNotEmpty() && this.listener != null) {
            val listener = this.listener

            val acceptedPermissions = ArrayList<String>()
            val askAgainPermissions = ArrayList<String>()
            val refusedPermissions = ArrayList<String>()

            for (i in permissions.indices) {
                val permissionName = permissions[i]
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    acceptedPermissions.add(permissionName)
                } else {
                    if (shouldShowRequestPermissionRationale(permissionName)) {
                        askAgainPermissions.add(permissionName)
                    } else {
                        refusedPermissions.add(permissionName)
                    }
                }
            }
            listener?.invoke(acceptedPermissions, refusedPermissions, askAgainPermissions)
            fragmentManager?.beginTransaction()?.remove(this)?.commitAllowingStateLoss()
        }
    }

    fun setListener(listener: (List<String>, List<String>, List<String>) -> Unit): PermissionFragment {
        this.listener = listener
        return this
    }

    companion object {
        const val LIST_PERMISSIONS = "LIST_PERMISSIONS"
        private const val REQUEST_CODE = 23

        fun newInstance(permissions: List<String>): PermissionFragment {
            val args = Bundle()
            args.putStringArrayList(LIST_PERMISSIONS, ArrayList(permissions))
            val fragment = PermissionFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
