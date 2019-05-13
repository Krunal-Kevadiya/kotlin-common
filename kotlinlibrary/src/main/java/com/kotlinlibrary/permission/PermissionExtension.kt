package com.kotlinlibrary.permission

import com.kotlinlibrary.permission.callback.*
import com.kotlinlibrary.utils.getFragmentActivityFromSource

fun Any.bindPermission(
    vararg permission: String,
    callback: (PermissionCallback) -> Unit
) = lazy(LazyThreadSafetyMode.NONE) {
    RuntimePermission(getFragmentActivityFromSource(this))
        .request(*permission)
        .onResponse(callback)
}

fun Any.askPermission(
    vararg permission: String,
    callback: (PermissionCallback) -> Unit
) = RuntimePermission(getFragmentActivityFromSource(this))
        .request(*permission)
        .onResponse(callback)

fun Any.askPermission(
    vararg permission: String,
    onAccepted: (AcceptedCallback) -> Unit,
    onDenied: (ListenerCallback) -> Unit
) = RuntimePermission(getFragmentActivityFromSource(this))
        .request(*permission)
        .onListener(onAccepted, onDenied)

fun Any.askPermission(
    vararg permission: String,
    onAccepted: (AcceptedCallback) -> Unit,
    onDenied: (DeniedCallback) -> Unit,
    onForeverDenied: (ForeverDeniedCallback) -> Unit
) = RuntimePermission(getFragmentActivityFromSource(this))
        .request(*permission)
        .onAccepted(onAccepted)
        .onDenied(onDenied)
        .onForeverDenied(onForeverDenied)