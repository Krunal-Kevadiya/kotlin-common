package com.kotlinlibrary.retrofitadapter.coroutines

import com.kotlinlibrary.retrofitadapter.SealedApiResult
import com.kotlinlibrary.retrofitadapter.toSealedApiResult
import com.kotlinlibrary.retrofitadapter.networkBody
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class CoroutinesCallAdapter<T>(private val responseType: Type) : CallAdapter<T, Deferred<SealedApiResult<T>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T>): Deferred<SealedApiResult<T>> {
        val deferred = CompletableDeferred<SealedApiResult<T>>()

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                call.cancel()
            }
        }

        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, throwable: Throwable) {
                deferred.complete(networkBody(throwable))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                deferred.complete(response.toSealedApiResult(responseType()))
            }
        })

        return deferred
    }
}