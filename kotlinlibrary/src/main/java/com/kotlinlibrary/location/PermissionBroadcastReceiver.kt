package com.kotlinlibrary.location

import androidx.lifecycle.Observer

/**
 * Receives results related to permission model
 */
class PermissionObserver(private val onResult: (Throwable?) -> Unit) : Observer<String> {

    override fun onChanged(status: String?) {
        status ?: return
        isRequestingPermission.set(false)
        when (status) {
            Constants.GRANTED -> {
                onResult(null)
            }
            else -> {
                onResult(Throwable(status))
            }
        }
        permissionLiveData.removeObserver(this)
        permissionLiveData.postValue(null)
    }
}