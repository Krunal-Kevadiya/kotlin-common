package com.kotlinlibrary.retrofitadapter.coroutines

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class CoroutinesCallAdapter<T>(private val responseType: Type) : CallAdapter<T, Deferred<CoroutinesApiResult<T>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T>): Deferred<CoroutinesApiResult<T>> {
        val deferred = CompletableDeferred<CoroutinesApiResult<T>>()

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                call.cancel()
            }
        }

        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, throwable: Throwable) {
                deferred.complete(CoroutinesApiResult(responseType, throwable))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                deferred.complete(CoroutinesApiResult(responseType, response))
            }
        })

        return deferred
    }
}