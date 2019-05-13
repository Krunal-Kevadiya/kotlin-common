package com.kotlinlibrary.retrofitadapter.exception

import android.content.Context
import com.google.gson.stream.MalformedJsonException
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.net.UnknownHostException

class RxErrorHandlingCallAdapterFactory(val context: Context) : CallAdapter.Factory() {
    private val original by lazy {
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    companion object {
        fun create(context: Context): CallAdapter.Factory =
            RxErrorHandlingCallAdapterFactory(context)
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *> {
        var wrapped: CallAdapter<out Any, *>? = null
        if (original.get(returnType, annotations, retrofit) is CallAdapter<out Any, *>) {
            wrapped = original.get(returnType, annotations, retrofit)
        }
        return RxCallAdapterWrapper(
            retrofit,
            wrapped,
            context
        )
    }

    private class RxCallAdapterWrapper<R>(
        val retrofit: Retrofit, val wrappedCallAdapter: CallAdapter<R, *>?,
        val context: Context
    ) : CallAdapter<R, Any> {

        override fun responseType(): Type? = wrappedCallAdapter?.responseType()
        override fun adapt(call: Call<R>): Any? {
            val result = wrappedCallAdapter?.adapt(call)
            if (result is Single<*>) {
                return result.onErrorResumeNext { throwable: Throwable ->
                    Single.error(asRetrofitException(throwable, retrofit))
                }
            }
            if (result is Observable<*>) {
                return result.onErrorResumeNext { throwable: Throwable ->
                    Observable.error(asRetrofitException(throwable, retrofit))
                }
            }
            if (result is Completable) {
                return result.onErrorResumeNext { throwable ->
                    Completable.error(asRetrofitException(throwable, retrofit))
                }
            }
            return result
        }

        private fun asRetrofitException(throwable: Throwable, retrofit: Retrofit): RetrofitException {
            return when (throwable) {
                is UnknownHostException -> RetrofitException.networkError(throwable)
                is MalformedJsonException -> RetrofitException.malformedJsonError(throwable)
                is HttpException -> {
                    val response = throwable.response()
                    RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit)
                }
                is IOException -> RetrofitException.networkError(throwable)
                else -> RetrofitException.unexpectedError(throwable)
            }
        }
    }
}
