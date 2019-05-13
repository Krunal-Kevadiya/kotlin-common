package com.kotlinlibrary.retrofitadapter.livedata

import androidx.lifecycle.LiveData
import com.kotlinlibrary.retrofitadapter.SealedApiResult
import com.kotlinlibrary.retrofitadapter.toSealedApiResult
import com.kotlinlibrary.retrofitadapter.networkBody
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response

class LiveDataCallAdapter<R, E>(
    private val responseType: Type,
    private val errorType: Type
) : CallAdapter<R, LiveData<SealedApiResult<R, E>>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): LiveData<SealedApiResult<R, E>> {
        return object : LiveData<SealedApiResult<R, E>>() {
            var started = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(response.toSealedApiResult(responseType, errorType))
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(networkBody(throwable))
                        }
                    })
                }
            }

            override fun onInactive() {
                call.cancel()
            }
        }
    }
}