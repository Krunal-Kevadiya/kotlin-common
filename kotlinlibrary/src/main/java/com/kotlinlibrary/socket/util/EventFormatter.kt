package com.kotlinlibrary.socket.util

class EventFormatter constructor(private val namespace: String?) {
    fun format(event: String): String {
        var result = event

        if (result[0] == '.' || result[0] == '\\') {
            return result.substring(1)
        } else if (namespace!!.isNotEmpty()) {
            result = "$namespace.$event"
        }

        return result.replace('.', '\\')
    }
}
