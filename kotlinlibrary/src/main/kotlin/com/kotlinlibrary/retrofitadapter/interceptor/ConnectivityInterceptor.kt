package com.kotlinlibrary.retrofitadapter.interceptor

import android.content.Context
import com.kotlinlibrary.R
import com.kotlinlibrary.retrofitadapter.hasConnection
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.hasConnection) {
            throw NoConnectivityException(context.getString(R.string.msg_no_internet_available))
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    inner class NoConnectivityException(msg: String) : IOException(msg)
}
