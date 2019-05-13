package com.kotlinlibrary.utils.ktx

import android.content.pm.PackageManager
import android.util.Base64
import com.kotlinlibrary.utils.getContextFromSource
import java.security.MessageDigest

fun Any.getKeyHash(): String {
    val context = getContextFromSource(this)
    val hashList: ArrayList<String> = ArrayList()
    try {
        val info = context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_SIGNATURES)
        for (signature in info.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            hashList.add(String(Base64.encode(md.digest(), 0)))
        }

        return if (hashList.isNotEmpty()) hashList[0] else ""
    } catch (e: IllegalStateException) {
        e.printStackTrace()
    }

    return ""
}