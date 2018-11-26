package com.kotlinlibrary.socket

import org.json.JSONObject
import java.util.HashMap

class SocketIOOptions {
    var host: String = "http://localhost:6001"
    var authEndpoint: String = "/broadcasting/auth"
    var eventNamespace: String = "App.Events.Broadcast"
    var headers: MutableMap<String, String> = HashMap()

    val auth: JSONObject
        @Throws(Exception::class)
        get() {
            val auth = JSONObject()
            val headers = JSONObject()

            for (header in this.headers.keys) {
                headers.put(header, this.headers[header])
            }

            auth.put("headers", headers)

            return auth
        }
}
