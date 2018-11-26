package com.kotlinlibrary.retrofitadapter.sealed

import com.kotlinlibrary.retrofitadapter.interceptor.ConnectivityInterceptor
import io.reactivex.Scheduler
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.TimeoutException

internal class RxSealedCallAdapter(
    annotations: Array<out Annotation>,
    newType: WrappedType,
    retrofit: Retrofit,
    defaultScheduler: Scheduler?
) : CallAdapter<Any, Any> {
    @Suppress("UNCHECKED_CAST")
    private val delegate =
        if (defaultScheduler != null)
            RxJava2CallAdapterFactory.createWithScheduler(defaultScheduler).get(
                newType,
                annotations,
                retrofit
            ) as CallAdapter<Any, Single<Response<*>>>
        else
            RxJava2CallAdapterFactory.create().get(
                newType,
                annotations,
                retrofit
            ) as CallAdapter<Any, Single<Response<*>>>

    override fun adapt(call: Call<Any>): Any {
        val adapt = delegate.adapt(call)
        return adapt
            .map {
                @Suppress("USELESS_CAST")
                it.toSealedApiResult(responseType()) as SealedApiResult<Any>
            }
            .onErrorResumeNext { t ->
                return@onErrorResumeNext when (t) {
                    is TimeoutException -> Single.just(errorBody(responseType(), t))
                    is IOException -> Single.just(errorBody(responseType(), t))
                    is ConnectivityInterceptor.NoConnectivityException -> Single.just(errorBody(responseType(), t))
                    else -> Single.error(t)
                }
            }
    }

    override fun responseType(): Type {
        return delegate.responseType()
    }
}