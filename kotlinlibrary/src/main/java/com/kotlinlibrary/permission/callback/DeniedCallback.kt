package com.kotlinlibrary.permission.callback

import java.util.ArrayList
import com.kotlinlibrary.permission.RuntimePermission

class DeniedCallback(
    val runtimePermission: RuntimePermission,
    denied: List<String>?
) {
    val denied = ArrayList<String>()

    init {
        if (denied != null) {
            this.denied.addAll(denied)
        }
    }

    fun askAgain() {
        runtimePermission.ask()
    }

    fun hasDenied(): Boolean {
        return !denied.isEmpty()
    }

    fun getDenied(): List<String> {
        return denied
    }
}
