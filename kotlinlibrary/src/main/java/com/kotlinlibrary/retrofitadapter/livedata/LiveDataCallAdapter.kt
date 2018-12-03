package com.kotlinlibrary.retrofitadapter.livedata

import androidx.lifecycle.LiveData
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response

class LiveDataCallAdapter<R>(private val responseType: Type) : CallAdapter<R, LiveData<LiveDataApiResult<R>>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): LiveData<LiveDataApiResult<R>> {
        return object : LiveData<LiveDataApiResult<R>>() {
            var started = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(LiveDataApiResult(responseType, response))
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(LiveDataApiResult(responseType, throwable))
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