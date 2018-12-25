package com.kotlinlibrary.permission

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.kotlinlibrary.permission.callback.*

fun FragmentActivity.bindPermission(
    vararg permission: String,
    callback: (PermissionCallback) -> Unit
) = lazy(LazyThreadSafetyMode.NONE) {
    RuntimePermission(this)
        .request(*permission)
        .onResponse(callback)
}

fun Fragment.bindPermission(
    vararg permission: String,
    callback: (PermissionCallback) -> Unit
) = lazy(LazyThreadSafetyMode.NONE) {
    RuntimePermission(activity)
        .request(*permission)
        .onResponse(callback)
}

fun FragmentActivity.askPermission(
    vararg permission: String,
    callback: (PermissionCallback) -> Unit
) = RuntimePermission(this)
        .request(*permission)
        .onResponse(callback)

fun FragmentActivity.askPermission(
    vararg permission: String,
    onAccepted: (AcceptedCallback) -> Unit,
    onDenied: (ListenerCallback) -> Unit
) = RuntimePermission(this)
        .request(*permission)
        .onListener(onAccepted, onDenied)

fun FragmentActivity.askPermission(
    vararg permission: String,
    onAccepted: (AcceptedCallback) -> Unit,
    onDenied: (DeniedCallback) -> Unit,
    onForeverDenied: (ForeverDeniedCallback) -> Unit
) = RuntimePermission(this)
        .request(*permission)
        .onAccepted(onAccepted)
        .onDenied(onDenied)
        .onForeverDenied(onForeverDenied)

fun Fragment.askPermission(
    vararg permission: String,
    callback: (PermissionCallback) -> Unit
) = RuntimePermission(activity)
        .request(*permission)
        .onResponse(callback)

fun Fragment.askPermission(
    vararg permission: String,
    onAccepted: (AcceptedCallback) -> Unit,
    onDenied: (ListenerCallback) -> Unit
) = RuntimePermission(activity)
        .request(*permission)
        .onListener(onAccepted, onDenied)

fun Fragment.askPermission(
    vararg permission: String,
    onAccepted: (AcceptedCallback) -> Unit,
    onDenied: (DeniedCallback) -> Unit,
    onForeverDenied: (ForeverDeniedCallback) -> Unit
) = RuntimePermission(activity)
        .request(*permission)
        .onAccepted(onAccepted)
        .onDenied(onDenied)
        .onForeverDenied(onForeverDenied)
