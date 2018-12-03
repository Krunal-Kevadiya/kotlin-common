package com.kotlinlibrary.socket

import com.kotlinlibrary.socket.channel.SocketIOChannel
import com.kotlinlibrary.socket.channel.SocketIOPresenceChannel
import com.kotlinlibrary.socket.channel.SocketIOPrivateChannel
import com.kotlinlibrary.socket.connector.SocketIOConnector

class SocketIO constructor(options: SocketIOOptions = SocketIOOptions()) {
    private val connector: SocketIOConnector = SocketIOConnector(options)

    val isConnected: Boolean
        get() = connector.isConnected

    fun connect(success: ((Array<out Any?>) -> Unit), error: ((Array<out Any?>) -> Unit)) {
        connector.connect(success, error)
    }

    fun on(eventName: String, callback: ((Array<out Any?>) -> Unit)) {
        connector.on(eventName, callback)
    }

    fun off(eventName: String) {
        connector.off(eventName)
    }

    fun listen(channel: String, event: String, callback: ((Array<out Any?>) -> Unit)): SocketIOChannel {
        return connector.listen(channel, event, callback)
    }

    fun channel(channel: String): SocketIOChannel? {
        return connector.channel(channel) as? SocketIOChannel
    }

    fun privateChannel(channel: String): SocketIOPrivateChannel? {
        return connector.privateChannel(channel) as? SocketIOPrivateChannel
    }

    fun presenceChannel(channel: String): SocketIOPresenceChannel? {
        return connector.presenceChannel(channel) as? SocketIOPresenceChannel
    }

    fun leave(channel: String) {
        connector.leave(channel)
    }

    fun disconnect() {
        connector.disconnect()
    }
}
