package com.kotlinlibrary.retrofitadapter

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.kotlinlibrary.utils.connectivityManager

@get:RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
val Context.hasConnection: Boolean
    get() = connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false