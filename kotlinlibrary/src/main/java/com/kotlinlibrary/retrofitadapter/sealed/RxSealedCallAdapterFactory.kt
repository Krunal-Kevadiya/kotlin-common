package com.kotlinlibrary.retrofitadapter.sealed

import io.reactivex.Scheduler
import retrofit2.CallAdapter
import retrofit2.CallAdapter.Factory
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class RxSealedCallAdapterFactory(private val defaultScheduler: Scheduler? = null) : Factory() {
    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val newType = WrappedType(returnType)
        return RxSealedCallAdapter(annotations, newType, retrofit, defaultScheduler)
    }
}

class WrappedType(private val returnType: Type) : ParameterizedType {
    override fun getRawType(): Type {
        return (returnType as ParameterizedType).rawType
    }

    override fun getOwnerType(): Type {
        return (returnType as ParameterizedType).ownerType
    }

    override fun getActualTypeArguments(): Array<Type> {
        return arrayOf(object : ParameterizedType {
            override fun getRawType(): Type {
                return Response::class.java
            }

            override fun getOwnerType(): Type? {
                return null
            }

            override fun getActualTypeArguments(): Array<Type> {
                return ((returnType as ParameterizedType).actualTypeArguments[0] as ParameterizedType).actualTypeArguments
            }
        })
    }
}