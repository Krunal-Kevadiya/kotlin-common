package com.kotlinlibrary.retrofitadapter.coroutines

import com.google.gson.Gson
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs
import okhttp3.Headers
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

class CoroutinesApiResult<T> {
    var code: Int
    var headers: Headers? = null
    var body: T? = null
    var errorBody: T? = null

    val isSuccessful: Boolean
        get() = code == 200

    constructor(responseType: Type, error: Throwable) {
        code = 500
        errorBody = errorBody(responseType, error)
    }

    constructor(responseType: Type, response: Response<T>) {
        code = response.code()
        headers = response.headers()
        if (response.isSuccessful) {
            body = response.body()
        } else {
            errorBody = response.errorBody(response.code(), responseType)
        }
    }
}

fun <T> Response<T>.errorBody(code: Int, responseType: Type): T {
    try {
        var message = ""
        try {
            message = errorBody()!!.string()
        } catch (e: IOException) {
            logs(e, LogType.ERROR)
        }
        return if (message.isNotEmpty()) {
            Gson().fromJson(message, responseType)
        } else {
            Gson().fromJson(getErrorJson(httpCode = code), responseType)
        }
    } catch (e: JSONException) {
        logs(e, LogType.ERROR)
        return Gson().fromJson(getErrorJson(httpCode = code), responseType)
    }
}

fun <T> errorBody(responseType: Type, exception: Throwable): T {
    try {
        var message = ""
        try {
            message = exception.message.toString()
        } catch (e: IOException) {
            e.logs(e, LogType.ERROR)
        }
        return if (message.isNotEmpty()) {
            Gson().fromJson(
                getErrorJson(
                    httpCode = 600,
                    msg = message
                ), responseType
            )
        } else {
            Gson().fromJson(
                getErrorJson(httpCode = 600),
                responseType
            )
        }
    } catch (e: JSONException) {
        e.logs(e, LogType.ERROR)
        return Gson().fromJson(
            getErrorJson(httpCode = 600),
            responseType
        )
    }
}

fun getErrorJson(code: String = "UNKNOWN", httpCode: Int = 0, msg: String = "Error while parsing response"): String {
    val jsonError = JSONObject()
    jsonError.put("code", code)
    jsonError.put("http_code", httpCode)
    jsonError.put("message", msg)

    val jsonData = JSONObject()
    jsonData.put("error", jsonError)
    return jsonData.toString()
}