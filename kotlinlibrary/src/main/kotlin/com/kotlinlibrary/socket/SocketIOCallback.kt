package com.kotlinlibrary.socket

import io.socket.client.Ack
import io.socket.emitter.Emitter

interface SocketIOCallback : Emitter.Listener, Ack
