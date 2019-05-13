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

class CoRoutinesCallAdapter<R, E>(
    private val responseType: Type,
    private val errorType: Type
) : CallAdapter<R, Deferred<SealedApiResult<R, E>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): Deferred<SealedApiResult<R, E>> {
        val deferred = CompletableDeferred<SealedApiResult<R, E>>()

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                call.cancel()
            }
        }

        call.enqueue(object : Callback<R> {
            override fun onFailure(call: Call<R>, throwable: Throwable) {
                deferred.complete(networkBody(throwable))
            }

            override fun onResponse(call: Call<R>, response: Response<R>) {
                deferred.complete(response.toSealedApiResult(responseType, errorType))
            }
        })

        return deferred
    }
}