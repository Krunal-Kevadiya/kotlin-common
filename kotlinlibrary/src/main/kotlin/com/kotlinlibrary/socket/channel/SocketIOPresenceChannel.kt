package com.kotlinlibrary.socket.channel

import com.kotlinlibrary.socket.SocketIOOptions
import io.socket.client.Socket

open class SocketIOPresenceChannel(
    socket: Socket?,
    name: String,
    options: SocketIOOptions
) : SocketIOPrivateChannel(socket, name, options), IPresenceChannel {
    override fun here(callback: (Array<out Any?>) -> Unit): IPresenceChannel {
        on("presence:joining", callback)
        return this
    }

    override fun joining(callback: (Array<out Any?>) -> Unit): IPresenceChannel {
        on("presence:subscribed", callback)
        return this
    }

    override fun leaving(callback: (Array<out Any?>) -> Unit): IPresenceChannel {
        on("presence:leaving", callback)
        return this
    }
}
