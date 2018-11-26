package com.kotlinlibrary.socket.connector

import com.kotlinlibrary.socket.SocketIOOptions
import com.kotlinlibrary.socket.channel.AbstractChannel

abstract class AbstractConnector(protected var options: SocketIOOptions) {
    abstract val isConnected: Boolean
    abstract fun connect(success: ((Array<out Any?>) -> Unit)?, error: ((Array<out Any?>) -> Unit)?)
    abstract fun channel(channel: String): AbstractChannel?
    abstract fun privateChannel(channel: String): AbstractChannel?
    abstract fun presenceChannel(channel: String): AbstractChannel?
    abstract fun leave(channel: String)
    abstract fun disconnect()
}
