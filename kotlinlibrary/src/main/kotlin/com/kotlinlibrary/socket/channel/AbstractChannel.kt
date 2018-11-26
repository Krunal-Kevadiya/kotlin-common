package com.kotlinlibrary.socket.channel

abstract class AbstractChannel {
    abstract fun listen(event: String, callback: (Array<out Any?>) -> Unit): AbstractChannel
    fun notification(callback: (Array<out Any?>) -> Unit): AbstractChannel {
        return listen(".Illuminate\\Notifications\\Events\\BroadcastNotificationCreated", callback)
    }

    fun listenForWhisper(event: String, callback: (Array<out Any?>) -> Unit): AbstractChannel {
        return listen(".client-$event", callback)
    }
}
