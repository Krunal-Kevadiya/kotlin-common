package com.kotlinlibrary.location

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * LiveData extension to observer it in a null safe way
 * */
fun <T : Any> LiveData<T?>.watch(owner: LifecycleOwner, func: (T) -> Unit) {
    this.observe(owner, Observer { result ->
        result?.apply(func)
    })
}

val Throwable.isDenied: Boolean
    get() = this.message?.let { message ->
        message == Constants.DENIED
    } ?: false

val Throwable.isPermanentlyDenied: Boolean
    get() = this.message?.let { message ->
        message == Constants.PERMANENTLY_DENIED
    } ?: false

/**
 * Extension Function for initializing [MutableLiveData] with some initial value
 * @param data is the initial value
 * */
internal fun <T> MutableLiveData<T>.initWith(data: T): MutableLiveData<T> = this.apply {
    value = data
}