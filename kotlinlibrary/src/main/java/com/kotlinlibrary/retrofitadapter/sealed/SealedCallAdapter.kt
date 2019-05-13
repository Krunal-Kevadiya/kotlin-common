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

internal class SealedCallAdapter<R, E>(
    private val responseType: Type,
    private val errorType: Type
) : CallAdapter<R, SealedApiResult<R, E>> {
    override fun responseType() = responseType
    override fun adapt(call: Call<R>): SealedApiResult<R, E> {
        return try {
            call.execute().toSealedApiResult(responseType, errorType)
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
