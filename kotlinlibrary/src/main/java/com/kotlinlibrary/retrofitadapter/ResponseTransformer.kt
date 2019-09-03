package com.kotlinlibrary.retrofitadapter

import com.google.gson.Gson
import okhttp3.Headers
import org.json.JSONException
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Informational1XX.Continue100
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Informational1XX.SwitchingProtocols101
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Informational1XX.Processing102
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Informational1XX.EarlyHints103
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Success2XX.Ok200
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Success2XX.Created201
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Success2XX.Accepted202
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Success2XX.NonAuthoritativeInformation203
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Success2XX.NoContent204
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Success2XX.ResetContent205
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Success2XX.PartialContent206
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Success2XX.MultiStatus207
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Success2XX.AlreadyReported208
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Success2XX.IMUsed226
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Redirection3XX.MultipleChoices300
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Redirection3XX.MovedPermanently301
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Redirection3XX.Found302
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Redirection3XX.SeeOther303
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Redirection3XX.NotModified304
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Redirection3XX.UseProxy305
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Redirection3XX.TemporaryRedirect307
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.Redirection3XX.PermanentRedirect308
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.BadRequest400
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.Unauthorized401
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.PaymentRequired402
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.Forbidden403
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.NotFound404
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.MethodNotAllowed405
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.NotAcceptable406
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.ProxyAuthenticationRequired407
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.RequestTimeout408
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.Conflict409
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.Gone410
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.LengthRequired411
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.PreconditionFailed412
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.PayloadTooLarge413
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.URITooLong414
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.UnsupportedMediaType415
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.RangeNotSatisfiable416
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.ExpectationFailed417
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.ImATeapot418
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.EnhanceYourCalm420
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.MisdirectedRequest421
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.UnprocessableEntry422
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.Locked423
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.FailedDependency424
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.TooEarly425
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.UpgradeRequired426
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.PreconditionRequired428
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.TooManyRequests429
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.RequestHeaderFieldsTooLarge431
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.NoResponse444
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.RetryWith449
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.BlockedByWindowsParentalControls450
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.UnavailableForLegalReasons451
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ClientError4XX.ClientClosedRequest499
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.InternalServerError500
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.NotImplementedError501
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.BadGateway502
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.ServiceUnavailable503
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.GatewayTimeout504
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.HTTPVersionNotSupported505
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.VariantAlsoNegotiates506
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.InsufficientStorage507
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.LoopDetected508
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.BandwidthLimitExceeded509
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.NotExtended510
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.NetworkAuthenticationRequired511
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.NetworkReadTimeoutError598
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.ServerError5xx.NetworkConnectTimeoutError599
import com.kotlinlibrary.retrofitadapter.SealedApiResult.Some.UnknownError.UnknownRequired
import com.kotlinlibrary.utils.ktx.logs

fun <R: Any?, E: Any?> Response<R>.toSealedApiResult(responseType: Type, errorType: Type): Some<R, E> {
    return when (code) {
        HttpStatusCode.CONTINUE -> Continue100(headers = headers, errorBody = errorBody(errorType))

        HttpStatusCode.SWITCHING_PROTOCOLS -> SwitchingProtocols101(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.PROCESSING -> Processing102(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.EARLY_HINTS -> EarlyHints103(headers = headers, errorBody = errorBody(errorType))

        HttpStatusCode.OK -> Ok200(headers = headers, body = body)
        HttpStatusCode.CREATED -> Created201(headers = headers, body = body)
        HttpStatusCode.ACCEPTED -> Accepted202(headers = headers, body = body)
        HttpStatusCode.NON_AUTHORITATIVE_INFORMATION -> NonAuthoritativeInformation203(headers = headers, body = body)
        HttpStatusCode.NO_CONTENT -> NoContent204(headers = headers, body = body)
        HttpStatusCode.RESET_CONTENT -> ResetContent205(headers = headers, body = body)
        HttpStatusCode.PARTIAL_CONTENT -> PartialContent206(headers = headers, body = body)
        HttpStatusCode.MULTI_STATUS -> MultiStatus207(headers = headers, body = body)
        HttpStatusCode.ALREADY_REPORTED -> AlreadyReported208(headers = headers, body = body)
        HttpStatusCode.IM_USED -> IMUsed226(headers = headers, body = body)

        HttpStatusCode.MULTIPLE_CHOICES -> MultipleChoices300(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.MOVED_PERMANENTLY -> MovedPermanently301(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.FOUND -> Found302(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.SEE_OTHER -> SeeOther303(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.NOT_MODIFIED -> NotModified304(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.USE_PROXY -> UseProxy305(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.TEMPORARY_REDIRECT -> TemporaryRedirect307(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.PERMANENT_REDIRECT -> PermanentRedirect308(headers = headers, errorBody = errorBody(errorType))

        HttpStatusCode.BAD_REQUEST -> BadRequest400(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.UNAUTHORIZED -> Unauthorized401(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.PAYMENT_REQUIRED -> PaymentRequired402(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.FORBIDDEN -> Forbidden403(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.NOT_FOUND -> NotFound404(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.METHOD_NOT_ALLOWED -> MethodNotAllowed405(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.NOT_ACCEPTABLE -> NotAcceptable406(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.PROXY_AUTHENTICATION_REQUIRED -> ProxyAuthenticationRequired407(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.REQUEST_TIMEOUT -> RequestTimeout408(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.CONFLICT -> Conflict409(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.GONE -> Gone410(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.LENGTH_REQUIRED -> LengthRequired411(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.PRECONDITION_FAILED -> PreconditionFailed412(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.PAYLOAD_TOO_LARGE -> PayloadTooLarge413(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.URI_TOO_LONG -> URITooLong414(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.UNSUPPORTED_MEDIA_TYPE -> UnsupportedMediaType415(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.RANGE_NOT_SATISFIABLE -> RangeNotSatisfiable416(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.EXPECTATION_FAILED -> ExpectationFailed417(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.IM_A_TEAPOT -> ImATeapot418(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.ENHANCE_YOUR_CALM -> EnhanceYourCalm420(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.MISDIRECTED_REQUEST -> MisdirectedRequest421(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.UN_PROCESSABLE_ENTRY -> UnprocessableEntry422(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.LOCKED -> Locked423(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.FAILED_DEPENDENCY -> FailedDependency424(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.TOO_EARLY -> TooEarly425(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.UPGRADE_REQUIRED -> UpgradeRequired426(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.PRECONDITION_REQUIRED -> PreconditionRequired428(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.TOO_MANY_REQUESTS -> TooManyRequests429(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.REQUEST_HEADER_FIELDS_TOO_LARGE -> RequestHeaderFieldsTooLarge431(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.NO_RESPONSE -> NoResponse444(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.RETRY_WITH -> RetryWith449(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.BLOCKED_BY_WINDOWS_PARENTAL_CONTROLS -> BlockedByWindowsParentalControls450(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.UNAVAILABLE_FOR_LEGAL_REASONS -> UnavailableForLegalReasons451(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.CLIENT_CLOSED_REQUEST -> ClientClosedRequest499(headers = headers, errorBody = errorBody(errorType))

        HttpStatusCode.INTERNAL_SERVER_ERROR -> InternalServerError500(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.NOT_IMPLEMENTED_ERROR -> NotImplementedError501(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.BAD_GATEWAY -> BadGateway502(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.SERVICE_UNAVAILABLE -> ServiceUnavailable503(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.GATEWAY_TIMEOUT -> GatewayTimeout504(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.HTTP_VERSION_NOT_SUPPORTED -> HTTPVersionNotSupported505(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.VARIANT_ALSO_NEGOTIATES -> VariantAlsoNegotiates506(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.INSUFFICIENT_STORAGE -> InsufficientStorage507(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.LOOP_DETECTED -> LoopDetected508(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.BANDWIDTH_LIMIT_EXCEEDED -> BandwidthLimitExceeded509(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.NOT_EXTENDED -> NotExtended510(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.NETWORK_AUTHENTICATION_REQUIRED -> NetworkAuthenticationRequired511(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.NETWORK_READ_TIMEOUT_ERROR -> NetworkReadTimeoutError598(headers = headers, errorBody = errorBody(errorType))
        HttpStatusCode.NETWORK_CONNECT_TIMEOUT_ERROR -> NetworkConnectTimeoutError599(headers = headers, errorBody = errorBody(errorType))

        else -> UnknownRequired(code, headers, body, errorBody(errorType))
    }
}

private val Response<*>.code: Int
    get() = code()
private val <R> Response<R>.body: R
    get() = body()!!
private val Response<*>.headers: Headers
    get() = headers()

fun <R, E> Response<R>.errorBody(responseType: Type): E? {
    try {
        var message = ""
        try {
            message = errorBody()!!.string()
        } catch (e: IOException) {
            logs(e)
        }
        return if (message.isNotEmpty()) {
            Gson().fromJson(message, responseType)
        } else {
            null
        }
    } catch (e: JSONException) {
        logs(e)
        return null
    }
}

fun <R, E> networkBody(exception: Throwable): SealedApiResult.NetworkError<R, E> {
    return SealedApiResult.NetworkError(exception)
}
