package com.kotlinlibrary.socket.channel

interface IPresenceChannel {
    fun here(callback: (Array<out Any?>) -> Unit): IPresenceChannel
    fun joining(callback: (Array<out Any?>) -> Unit): IPresenceChannel
    fun leaving(callback: (Array<out Any?>) -> Unit): IPresenceChannel
}
