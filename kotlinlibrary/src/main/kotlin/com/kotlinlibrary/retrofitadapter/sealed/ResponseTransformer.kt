package com.kotlinlibrary.retrofitadapter.sealed

import com.google.gson.Gson
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs
import okhttp3.Headers
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Informational1XX.Continue100
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Informational1XX.SwitchingProtocols101
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Informational1XX.Processing102
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Informational1XX.EarlyHints103
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Success2XX.Ok200
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Success2XX.Created201
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Success2XX.Accepted202
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Success2XX.NonAuthoritativeInformation203
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Success2XX.NoContent204
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Success2XX.ResetContent205
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Success2XX.PartialContent206
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Success2XX.MultiStatus207
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Success2XX.AlreadyReported208
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Success2XX.IMUsed226
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Redirection3XX.MultipleChoices300
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Redirection3XX.MovedPermanently301
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Redirection3XX.Found302
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Redirection3XX.SeeOther303
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Redirection3XX.NotModified304
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Redirection3XX.UseProxy305
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Redirection3XX.TemporaryRedirect307
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.Redirection3XX.PermanentRedirect308
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.BadRequest400
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.Unauthorized401
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.PaymentRequired402
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.Forbidden403
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.NotFound404
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.MethodNotAllowed405
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.NotAcceptable406
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.ProxyAuthenticationRequired407
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.RequestTimeout408
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.Conflict409
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.Gone410
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.LengthRequired411
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.PreconditionFailed412
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.PayloadTooLarge413
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.URITooLong414
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.UnsupportedMediaType415
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.RangeNotSatisfiable416
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.ExpectationFailed417
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.ImATeapot418
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.EnhanceYourCalm420
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.MisdirectedRequest421
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.UnprocessableEntry422
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.Locked423
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.FailedDependency424
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.TooEarly425
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.UpgradeRequired426
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.PreconditionRequired428
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.TooManyRequests429
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.RequestHeaderFieldsTooLarge431
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.NoResponse444
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.RetryWith449
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.BlockedByWindowsParentalControls450
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.UnavailableForLegalReasons451
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ClientError4XX.ClientClosedRequest499
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.InternalServerError500
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.NotImplementedError501
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.BadGateway502
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.ServiceUnavailable503
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.GatewayTimeout504
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.HTTPVersionNotSupported505
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.VariantAlsoNegotiates506
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.InsufficientStorage507
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.LoopDetected508
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.BandwidthLimitExceeded509
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.NotExtended510
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.NetworkAuthenticationRequired511
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.NetworkReadTimeoutError598
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.ServerError5xx.NetworkConnectTimeoutError599
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.UnknownError.UnknownRequired
import com.kotlinlibrary.retrofitadapter.sealed.SealedApiResult.Some.UnknownError.NetworkError

fun <R : Any?> Response<R>.toSealedApiResult(responseType: Type): SealedApiResult.Some<R> {
    return when (code) {
        HttpStatusCode.CONTINUE -> Continue100(headers, errorBody(responseType))
        HttpStatusCode.SWITCHING_PROTOCOLS -> SwitchingProtocols101(headers, errorBody(responseType))
        HttpStatusCode.PROCESSING -> Processing102(headers, errorBody(responseType))
        HttpStatusCode.EARLY_HINTS -> EarlyHints103(headers, errorBody(responseType))

        HttpStatusCode.OK -> Ok200(headers, body)
        HttpStatusCode.CREATED -> Created201(headers, body)
        HttpStatusCode.ACCEPTED -> Accepted202(headers, body)
        HttpStatusCode.NON_AUTHORITATIVE_INFORMATION -> NonAuthoritativeInformation203(headers, body)
        HttpStatusCode.NO_CONTENT -> NoContent204(headers, body)
        HttpStatusCode.RESET_CONTENT -> ResetContent205(headers, body)
        HttpStatusCode.PARTIAL_CONTENT -> PartialContent206(headers, body)
        HttpStatusCode.MULTI_STATUS -> MultiStatus207(headers, body)
        HttpStatusCode.ALREADY_REPORTED -> AlreadyReported208(headers, body)
        HttpStatusCode.IM_USED -> IMUsed226(headers, body)

        HttpStatusCode.MULTIPLE_CHOICES -> MultipleChoices300(headers, errorBody(responseType))
        HttpStatusCode.MOVED_PERMANENTLY -> MovedPermanently301(headers, errorBody(responseType))
        HttpStatusCode.FOUND -> Found302(headers, errorBody(responseType))
        HttpStatusCode.SEE_OTHER -> SeeOther303(headers, errorBody(responseType))
        HttpStatusCode.NOT_MODIFIED -> NotModified304(headers, errorBody(responseType))
        HttpStatusCode.USE_PROXY -> UseProxy305(headers, errorBody(responseType))
        HttpStatusCode.TEMPORARY_REDIRECT -> TemporaryRedirect307(headers, errorBody(responseType))
        HttpStatusCode.PERMANENT_REDIRECT -> PermanentRedirect308(headers, errorBody(responseType))

        HttpStatusCode.BAD_REQUEST -> BadRequest400(headers, errorBody(responseType))
        HttpStatusCode.UNAUTHORIZED -> Unauthorized401(headers, errorBody(responseType))
        HttpStatusCode.PAYMENT_REQUIRED -> PaymentRequired402(headers, errorBody(responseType))
        HttpStatusCode.FORBIDDEN -> Forbidden403(headers, errorBody(responseType))
        HttpStatusCode.NOT_FOUND -> NotFound404(headers, errorBody(responseType))
        HttpStatusCode.METHOD_NOT_ALLOWED -> MethodNotAllowed405(headers, errorBody(responseType))
        HttpStatusCode.NOT_ACCEPTABLE -> NotAcceptable406(headers, errorBody(responseType))
        HttpStatusCode.PROXY_AUTHENTICATION_REQUIRED -> ProxyAuthenticationRequired407(headers, errorBody(responseType))
        HttpStatusCode.REQUEST_TIMEOUT -> RequestTimeout408(headers, errorBody(responseType))
        HttpStatusCode.CONFLICT -> Conflict409(headers, errorBody(responseType))
        HttpStatusCode.GONE -> Gone410(headers, errorBody(responseType))
        HttpStatusCode.LENGTH_REQUIRED -> LengthRequired411(headers, errorBody(responseType))
        HttpStatusCode.PRECONDITION_FAILED -> PreconditionFailed412(headers, errorBody(responseType))
        HttpStatusCode.PAYLOAD_TOO_LARGE -> PayloadTooLarge413(headers, errorBody(responseType))
        HttpStatusCode.URI_TOO_LONG -> URITooLong414(headers, errorBody(responseType))
        HttpStatusCode.UNSUPPORTED_MEDIA_TYPE -> UnsupportedMediaType415(headers, errorBody(responseType))
        HttpStatusCode.RANGE_NOT_SATISFIABLE -> RangeNotSatisfiable416(headers, errorBody(responseType))
        HttpStatusCode.EXPECTATION_FAILED -> ExpectationFailed417(headers, errorBody(responseType))
        HttpStatusCode.IM_A_TEAPOT -> ImATeapot418(headers, errorBody(responseType))
        HttpStatusCode.ENHANCE_YOUR_CALM -> EnhanceYourCalm420(headers, errorBody(responseType))
        HttpStatusCode.MISDIRECTED_REQUEST -> MisdirectedRequest421(headers, errorBody(responseType))
        HttpStatusCode.UN_PROCESSABLE_ENTRY -> UnprocessableEntry422(headers, errorBody(responseType))
        HttpStatusCode.LOCKED -> Locked423(headers, errorBody(responseType))
        HttpStatusCode.FAILED_DEPENDENCY -> FailedDependency424(headers, errorBody(responseType))
        HttpStatusCode.TOO_EARLY -> TooEarly425(headers, errorBody(responseType))
        HttpStatusCode.UPGRADE_REQUIRED -> UpgradeRequired426(headers, errorBody(responseType))
        HttpStatusCode.PRECONDITION_REQUIRED -> PreconditionRequired428(headers, errorBody(responseType))
        HttpStatusCode.TOO_MANY_REQUESTS -> TooManyRequests429(headers, errorBody(responseType))
        HttpStatusCode.REQUEST_HEADER_FIELDS_TOO_LARGE -> RequestHeaderFieldsTooLarge431(headers, errorBody(responseType))
        HttpStatusCode.NO_RESPONSE -> NoResponse444(headers, errorBody(responseType))
        HttpStatusCode.RETRY_WITH -> RetryWith449(headers, errorBody(responseType))
        HttpStatusCode.BLOCKED_BY_WINDOWS_PARENTAL_CONTROLS -> BlockedByWindowsParentalControls450(headers, errorBody(responseType))
        HttpStatusCode.UNAVAILABLE_FOR_LEGAL_REASONS -> UnavailableForLegalReasons451(headers, errorBody(responseType))
        HttpStatusCode.CLIENT_CLOSED_REQUEST -> ClientClosedRequest499(headers, errorBody(responseType))

        HttpStatusCode.INTERNAL_SERVER_ERROR -> InternalServerError500(headers, errorBody(responseType))
        HttpStatusCode.NOT_IMPLEMENTED_ERROR -> NotImplementedError501(headers, errorBody(responseType))
        HttpStatusCode.BAD_GATEWAY -> BadGateway502(headers, errorBody(responseType))
        HttpStatusCode.SERVICE_UNAVAILABLE -> ServiceUnavailable503(headers, errorBody(responseType))
        HttpStatusCode.GATEWAY_TIMEOUT -> GatewayTimeout504(headers, errorBody(responseType))
        HttpStatusCode.HTTP_VERSION_NOT_SUPPORTED -> HTTPVersionNotSupported505(headers, errorBody(responseType))
        HttpStatusCode.VARIANT_ALSO_NEGOTIATES -> VariantAlsoNegotiates506(headers, errorBody(responseType))
        HttpStatusCode.INSUFFICIENT_STORAGE -> InsufficientStorage507(headers, errorBody(responseType))
        HttpStatusCode.LOOP_DETECTED -> LoopDetected508(headers, errorBody(responseType))
        HttpStatusCode.BANDWIDTH_LIMIT_EXCEEDED -> BandwidthLimitExceeded509(headers, errorBody(responseType))
        HttpStatusCode.NOT_EXTENDED -> NotExtended510(headers, errorBody(responseType))
        HttpStatusCode.NETWORK_AUTHENTICATION_REQUIRED -> NetworkAuthenticationRequired511(headers, errorBody(responseType))
        HttpStatusCode.NETWORK_READ_TIMEOUT_ERROR -> NetworkReadTimeoutError598(headers, errorBody(responseType))
        HttpStatusCode.NETWORK_CONNECT_TIMEOUT_ERROR -> NetworkConnectTimeoutError599(headers, errorBody(responseType))

        else -> UnknownRequired(code, headers, body, errorBody(responseType))
    }
}

private val Response<*>.code: Int
    get() = code()
private val <R> Response<R>.body: R
    get() = body()!!
private val Response<*>.headers: Headers
    get() = headers()

fun <R> Response<R>.errorBody(responseType: Type): R {
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

fun <R> errorBody(responseType: Type, exception: Exception): Some<R> {
    try {
        var message = ""
        try {
            message = exception.message.toString()
        } catch (e: IOException) {
            e.logs(e, LogType.ERROR)
        }
        return if (message.isNotEmpty()) {
            NetworkError(
                null,
                Gson().fromJson(getErrorJson(httpCode = HttpStatusCode.NETWORK_ERROR, msg = message), responseType)
            )

        } else {
            NetworkError(
                null,
                Gson().fromJson(getErrorJson(httpCode = HttpStatusCode.NETWORK_ERROR), responseType)
            )
        }
    } catch (e: JSONException) {
        e.logs(e, LogType.ERROR)
        return NetworkError(
            null,
            Gson().fromJson(getErrorJson(httpCode = HttpStatusCode.NETWORK_ERROR), responseType)
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