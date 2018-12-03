package com.kotlinlibrary.permission

interface ResponsePermissionCallback {
     fun onResult(permissionResult: List<String>)
}