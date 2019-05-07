package com.kotlinlibrary.socket.connector

import com.kotlinlibrary.socket.SocketIOCallback
import com.kotlinlibrary.socket.SocketIOException
import com.kotlinlibrary.socket.SocketIOOptions
import com.kotlinlibrary.socket.channel.AbstractChannel
import com.kotlinlibrary.socket.channel.SocketIOChannel
import com.kotlinlibrary.socket.channel.SocketIOPresenceChannel
import com.kotlinlibrary.socket.channel.SocketIOPrivateChannel
import com.kotlinlibrary.utils.ktx.logs
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException
import java.util.ConcurrentModificationException
import java.util.HashMap

class SocketIOConnector(options: SocketIOOptions) : AbstractConnector(options) {
    private var socket: Socket? = null
    private val channels: MutableMap<String, SocketIOChannel> = HashMap()
    override val isConnected: Boolean
        get() = socket?.connected() ?: false

    override fun connect(success: ((Array<out Any?>) -> Unit)?, error: ((Array<out Any?>) -> Unit)?) {
        try {
            socket = IO.socket(this.options.host)
            socket?.connect()

            if (success != null) {
                socket?.on(Socket.EVENT_CONNECT, object : SocketIOCallback {
                    override fun call(vararg args: Any?) {
                        success(args)
                    }
                })
            }

            if (error != null) {
                socket?.on(Socket.EVENT_CONNECT_ERROR, object : SocketIOCallback {
                    override fun call(vararg args: Any?) {
                        error(args)
                    }
                })
            }
        } catch (e: URISyntaxException) {
            logs(e)
            error?.invoke(arrayOf())
        }
    }

    fun on(eventName: String, callback: (Array<out Any?>) -> Unit) {
        socket?.on(eventName, object : SocketIOCallback {
            override fun call(vararg args: Any?) {
                callback(args)
            }
        })
    }

    fun off(eventName: String) {
        socket?.off(eventName)
    }

    fun listen(channel: String, event: String, callback: (Array<out Any?>) -> Unit): SocketIOChannel {
        return this.channel(channel)?.listen(event, callback) as SocketIOChannel
    }

    override fun channel(channel: String): AbstractChannel? {
        if (!channels.containsKey(channel)) {
            channels[channel] = SocketIOChannel(socket, channel, options)
        }
        return channels[channel]
    }

    override fun privateChannel(channel: String): AbstractChannel? {
        val name = "private-$channel"

        if (!channels.containsKey(name)) {
            channels[name] = SocketIOPrivateChannel(socket, name, options)
        }
        return channels[name]
    }

    override fun presenceChannel(channel: String): AbstractChannel? {
        val name = "presence-$channel"

        if (!channels.containsKey(name)) {
            channels[name] = SocketIOPresenceChannel(socket, name, options)
        }
        return channels[name]
    }

    override fun leave(channel: String) {
        val privateChannel = "private-$channel"
        val presenceChannel = "presence-$channel"

        for (subscribed in channels.keys) {
            if (subscribed == channel || subscribed == privateChannel || subscribed == presenceChannel) {
                try {
                    channels[subscribed]?.unsubscribe(null)
                    // channels.remove(subscribed)
                } catch (e: SocketIOException) {
                    logs(e)
                } catch (e: ConcurrentModificationException) {
                    logs(e)
                }
            }
        }
    }

    override fun disconnect() {
        for (subscribed in channels.keys) {
            try {
                channels[subscribed]?.unsubscribe(null)
            } catch (e: SocketIOException) {
                logs(e)
            }
        }

        channels.clear()
        socket?.disconnect()
    }
}
