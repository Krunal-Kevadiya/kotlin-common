package com.kotlinlibrary.location

import android.location.Location

interface LocationCallback {
    fun onSuccess(location: Location)
    fun onFailure(e: Exception)
}
