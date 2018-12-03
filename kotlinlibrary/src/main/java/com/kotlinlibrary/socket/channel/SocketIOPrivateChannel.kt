package com.kotlinlibrary.socket.channel

import com.kotlinlibrary.socket.SocketIOCallback
import com.kotlinlibrary.socket.SocketIOException
import com.kotlinlibrary.socket.SocketIOOptions
import io.socket.client.Socket
import org.json.JSONObject

open class SocketIOPrivateChannel(
    socket: Socket?,
    name: String,
    options: SocketIOOptions
) : SocketIOChannel(socket, name, options) {
    @Throws(SocketIOException::class)
    fun whisper(event: String, data: JSONObject?, callback: ((Array<out Any?>) -> Unit)?): SocketIOPrivateChannel {
        val jsonObject = JSONObject()

        try {
            jsonObject.put("channel", name)
            jsonObject.put("event", "client-$event")

            if (data != null) {
                jsonObject.put("data", data)
            }

            if (callback == null) {
                socket?.emit("client event", jsonObject)
            } else {
                socket?.emit("client event", jsonObject, object : SocketIOCallback {
                    override fun call(vararg args: Any?) {
                        callback(args)
                    }
                })
            }
        } catch (e: Exception) {
            throw SocketIOException("Cannot whisper o, channel '" + name + "' : " + e.message)
        }
        return this
    }
}
