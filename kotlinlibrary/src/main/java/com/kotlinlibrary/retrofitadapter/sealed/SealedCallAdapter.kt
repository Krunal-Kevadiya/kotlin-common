package com.kotlinlibrary.retrofitadapter.sealed

import com.kotlinlibrary.retrofitadapter.SealedApiResult
import com.kotlinlibrary.retrofitadapter.networkBody
import com.kotlinlibrary.retrofitadapter.toSealedApiResult
import com.kotlinlibrary.utils.ktx.logs
import retrofit2.Call
import retrofit2.CallAdapter
import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.TimeoutException

internal class SealedCallAdapter<R>(private val responseType: Type) : CallAdapter<R, SealedApiResult<*>> {
    override fun responseType() = responseType
    override fun adapt(call: Call<R>): SealedApiResult<R> {
        return try {
            call.execute().toSealedApiResult(responseType)
        } catch (e: TimeoutException) {
            logs(e)
            networkBody(e)
        } catch (e: IOException) {
            logs(e)
            networkBody(e)
        } catch (e: Exception) {
            logs(e)
            networkBody(e)
        }
    }
}
