package com.kotlinlibrary.permission.callback

import com.kotlinlibrary.permission.RuntimePermission
import java.util.ArrayList

class PermissionCallback(
    private val runtimePermission: RuntimePermission,
    accepted: List<String>?,
    foreverDenied: List<String>?,
    denied: List<String>?
) {
    val accepted = ArrayList<String>()
    val foreverDenied = ArrayList<String>()
    val denied = ArrayList<String>()

    init {
        if (accepted != null) {
            this.accepted.addAll(accepted)
        }
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

    fun hasAccepted(): Boolean {
        return foreverDenied.isEmpty() && denied.isEmpty()
    }

    fun hasDenied(): Boolean {
        return denied.isNotEmpty()
    }

    fun hasForeverDenied(): Boolean {
        return foreverDenied.isNotEmpty()
    }

    fun getAccepted(): List<String> {
        return accepted
    }

    fun getForeverDenied(): List<String> {
        return foreverDenied
    }

    fun getDenied(): List<String> {
        return denied
    }
}
