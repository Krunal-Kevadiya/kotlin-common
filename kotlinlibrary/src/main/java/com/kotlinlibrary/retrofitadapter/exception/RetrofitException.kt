package com.kotlinlibrary.retrofitadapter.exception

import com.google.gson.stream.MalformedJsonException
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class RetrofitException(
    message: String?,
    private val url: String?,
    private val response: Response<*>?,
    private val kind: Kind,
    exception: Throwable?,
    private val retrofit: Retrofit?
) : RuntimeException(message, exception) {

    companion object {
        fun httpError(url: String?, response: Response<*>?, retrofit: Retrofit): RetrofitException {
            val message = response?.code()?.toString() + " " + response?.message()
            return RetrofitException(message, url, response, Kind.HTTP, null, retrofit)
        }

        fun malformedJsonError(exception: MalformedJsonException): RetrofitException {
            return RetrofitException(exception.message, null, null, Kind.NETWORK, exception, null)
        }

        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(exception.message, null, null, Kind.NETWORK, exception, null)
        }

        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(exception.message, null, null, Kind.UNEXPECTED, exception, null)
        }
    }

    fun getUrl() = url

    fun getResponse() = response

    fun getKind() = kind

    fun getRetrofit() = retrofit

    @Throws(IOException::class)
    private fun <T> getErrorBodyAs(type: Class<T>): T? {
        return if (response?.errorBody() == null || retrofit == null) {
            null
        } else {
            val converter: Converter<ResponseBody, T> =
                retrofit.responseBodyConverter(type, arrayOfNulls<Annotation>(0))
            converter.convert(response.errorBody()!!)
        }
    }

    enum class Kind {
        NETWORK,
        HTTP,
        HTTP_422_WITH_DATA,
        UNEXPECTED
    }
}
