package com.kotlinlibrary.permission.callback

import java.util.ArrayList
import com.kotlinlibrary.permission.RuntimePermission

class DeniedCallback(
    private val runtimePermission: RuntimePermission,
    denied: List<String>?
) {
    private val denied = ArrayList<String>()

    init {
        if (denied != null) {
            this.denied.addAll(denied)
        }
    }

    fun askAgain() {
        runtimePermission.ask()
    }

    fun hasDenied(): Boolean {
        return denied.isNotEmpty()
    }

    fun getDenied(): List<String> {
        return denied
    }
}
