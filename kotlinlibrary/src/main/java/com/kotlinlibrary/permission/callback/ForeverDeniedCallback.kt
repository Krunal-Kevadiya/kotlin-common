package com.kotlinlibrary.permission.callback

import java.util.ArrayList
import com.kotlinlibrary.permission.RuntimePermission

class ForeverDeniedCallback(
    val runtimePermission: RuntimePermission,
    foreverDenied: List<String>?
) {
    val foreverDenied = ArrayList<String>()

    init {
        if (foreverDenied != null) {
            this.foreverDenied.addAll(foreverDenied)
        }
    }

    fun goToSettings() {
        runtimePermission.goToSettings()
    }

    fun hasForeverDenied(): Boolean {
        return !foreverDenied.isEmpty()
    }

    fun getForeverDenied(): List<String> {
        return foreverDenied
    }
}
