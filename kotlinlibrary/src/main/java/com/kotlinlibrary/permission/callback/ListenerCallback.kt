package com.kotlinlibrary.permission.callback

import com.kotlinlibrary.permission.RuntimePermission
import java.util.ArrayList

class ListenerCallback(
    private val runtimePermission: RuntimePermission,
    foreverDenied: List<String>?,
    denied: List<String>?
) {
    private val foreverDenied = ArrayList<String>()
    private val denied = ArrayList<String>()

    init {
        if (foreverDenied != null) {
            this.foreverDenied.addAll(foreverDenied)
        }
        if (denied != null) {
            this.denied.addAll(denied)
        }
    }

    fun askAgain() {
        runtimePermission.ask()
    }

    fun goToSettings() {
        runtimePermission.goToSettings()
    }

    fun hasDenied(): Boolean {
        return denied.isNotEmpty()
    }

    fun hasForeverDenied(): Boolean {
        return foreverDenied.isNotEmpty()
    }

    fun getForeverDenied(): List<String> {
        return foreverDenied
    }

    fun getDenied(): List<String> {
        return denied
    }
}
