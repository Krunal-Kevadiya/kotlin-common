package com.kotlinlibrary.retrofitadapter.rx

import com.kotlinlibrary.retrofitadapter.SealedApiResult
import com.kotlinlibrary.retrofitadapter.toSealedApiResult
import com.kotlinlibrary.retrofitadapter.networkBody
import io.reactivex.Scheduler
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

internal class RxSealedCallAdapter(
    annotations: Array<out Annotation>,
    newType: WrappedType,
    retrofit: Retrofit,
    defaultScheduler: Scheduler?,
    private val errorType: Type
) : CallAdapter<Any, Any> {
    @Suppress("UNCHECKED_CAST")
    private val delegate =
        if (defaultScheduler != null)
            RxJava2CallAdapterFactory.createWithScheduler(defaultScheduler).get(
                newType,
                annotations,
                retrofit
            ) as CallAdapter<Any, Single<Response<Any>>>
        else
            RxJava2CallAdapterFactory.create().get(
                newType,
                annotations,
                retrofit
            ) as CallAdapter<Any, Single<Response<Any>>>

    override fun adapt(call: Call<Any>): Any {
        val adapt = delegate.adapt(call)
        return adapt
            .map {
                @Suppress("USELESS_CAST")
                it.toSealedApiResult<Any, Any>(responseType(), errorType) as SealedApiResult<Any, Any>
            }
            .onErrorResumeNext { throwable ->
                return@onErrorResumeNext Single.just(networkBody(throwable))
            }
    }

    override fun responseType(): Type {
        return delegate.responseType()
    }
}