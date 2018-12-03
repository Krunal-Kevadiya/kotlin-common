package com.kotlinlibrary.socket.channel

import com.kotlinlibrary.socket.SocketIOCallback
import com.kotlinlibrary.socket.SocketIOException
import com.kotlinlibrary.socket.SocketIOOptions
import com.kotlinlibrary.socket.util.EventFormatter
import io.socket.client.Socket
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

open class SocketIOChannel(
    protected var socket: Socket?,
    val name: String,
    protected var options: SocketIOOptions
) : AbstractChannel() {
    private var formatter: EventFormatter = EventFormatter(options.eventNamespace)
    private val eventsCallbacks: MutableMap<String, MutableList<SocketIOCallback>>

    init {
        this.eventsCallbacks = HashMap()

        try {
            this.subscribe(null)
        } catch (e: SocketIOException) {
            e.printStackTrace()
        }

        configureReconnector()
    }

    @Throws(SocketIOException::class)
    fun subscribe(callback: ((Array<out Any?>) -> Unit)?) {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("channel", name)
            jsonObject.put("auth", options.auth)

            if (callback == null) {
                socket?.emit("subscribe", jsonObject)
            } else {
                socket?.emit("subscribe", jsonObject, object : SocketIOCallback {
                    override fun call(vararg args: Any?) {
                        callback(args)
                    }
                })
            }
        } catch (e: Exception) {
            throw SocketIOException("Cannot subscribe to channel '" + name + "' : " + e.message)
        }
    }

    @Throws(SocketIOException::class)
    fun unsubscribe(callback: ((Array<out Any?>) -> Unit)?) {
        unbind()
        val jsonObject = JSONObject()
        try {
            jsonObject.put("channel", name)
            jsonObject.put("auth", options.auth)

            if (callback == null) {
                socket?.emit("unsubscribe", jsonObject)
            } else {
                socket?.emit("unsubscribe", jsonObject, object : SocketIOCallback {
                    override fun call(vararg args: Any?) {
                        callback(args)
                    }
                })
            }
        } catch (e: Exception) {
            throw SocketIOException("Cannot unsubscribe to channel '" + name + "' : " + e.message)
        }
    }

    override fun listen(event: String, callback: (Array<out Any?>) -> Unit): AbstractChannel {
        on(formatter.format(event), callback)

        return this
    }

    protected fun on(event: String, callback: (Array<out Any?>) -> Unit) {
        val listener = object : SocketIOCallback {
            override fun call(vararg args: Any?) {
                if (args.isNotEmpty() && args[0] is String) {
                    val channel = args[0] as String

                    if (channel == name) {
                        callback(args)
                    }
                }
            }
        }
        socket?.on(event, listener)
        bind(event, listener)
    }

    private fun configureReconnector() {
        val callback = object : SocketIOCallback {
            override fun call(vararg args: Any?) {
                try {
                    subscribe(null)
                } catch (e: SocketIOException) {
                    e.printStackTrace()
                }
            }
        }

        socket?.on(Socket.EVENT_RECONNECT, callback)
        bind(Socket.EVENT_RECONNECT, callback)
    }

    private fun bind(event: String, callback: SocketIOCallback) {
        if (!eventsCallbacks.containsKey(event)) {
            eventsCallbacks[event] = ArrayList()
        }

        eventsCallbacks[event]?.add(callback)
    }

    fun unbind() {
        for (event in eventsCallbacks.keys) {
            socket?.off(event)
            //eventsCallbacks.remove(event);
        }

        eventsCallbacks.clear()
    }
}
