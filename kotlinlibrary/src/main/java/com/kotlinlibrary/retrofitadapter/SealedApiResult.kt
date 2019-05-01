package com.kotlinlibrary.retrofitadapter

import okhttp3.Headers

sealed class SealedApiResult<out T> {
    abstract fun <R> map(map: ((T) -> R)): SealedApiResult<R>

    sealed class Some<out T>(val code: Int, val headers: Headers? = null, val errorBody: T? = null)
        : SealedApiResult<T>() {
        override fun toString() = "Some(code=$code, headers=$headers)"
        /* 1XX Informational results */
        sealed class Informational1XX<out T>(code: Int, headers: Headers? = null, errorBody: T? = null)
            : Some<T>(code, headers, errorBody) {
            class Continue100<out T>(headers: Headers? = null, errorBody: T? = null)
                : Informational1XX<T>(HttpStatusCode.CONTINUE, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    Continue100(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "Continue100(headers=$headers)"
            }

            class SwitchingProtocols101<out T>(headers: Headers? = null, errorBody: T? = null)
                : Informational1XX<T>(HttpStatusCode.SWITCHING_PROTOCOLS, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    SwitchingProtocols101(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "SwitchingProtocols101(headers=$headers)"
            }

            class Processing102<out T>(headers: Headers? = null, errorBody: T? = null)
                : Informational1XX<T>(HttpStatusCode.PROCESSING, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    Processing102(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "Processing102(headers=$headers)"
            }

            class EarlyHints103<out T>(headers: Headers? = null, errorBody: T? = null)
                : Informational1XX<T>(HttpStatusCode.EARLY_HINTS, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    EarlyHints103(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "EarlyHints103(headers=$headers)"
            }
        }

        /* 2XX Success results */
        sealed class Success2XX<out T>(code: Int, headers: Headers? = null)
            : Some<T>(code, headers) {
            class Ok200<T>(headers: Headers? = null, val body: T? = null)
                : Success2XX<T>(HttpStatusCode.OK, headers) {
                override fun <R> map(map: ((T) -> R)) =
                    Ok200(
                        headers,
                        body?.let { map(it) })
                override fun toString() = "Ok200(body=$body, headers=$headers)"
            }

            class Created201<T>(headers: Headers? = null, val body: T? = null)
                : Success2XX<T>(HttpStatusCode.CREATED, headers) {
                override fun <R> map(map: ((T) -> R)) =
                    Created201(
                        headers,
                        body?.let { map(it) })
                override fun toString() = "Created201(body = $body, headers=$headers)"
            }

            class Accepted202<T>(headers: Headers? = null, val body: T? = null)
                : Success2XX<T>(HttpStatusCode.ACCEPTED, headers) {
                override fun <R> map(map: ((T) -> R)) =
                    Accepted202(
                        headers,
                        body?.let { map(it) })
                override fun toString() = "Accepted202(body=$body, headers=$headers)"
            }

            class NonAuthoritativeInformation203<T>(headers: Headers? = null, val body: T? = null)
                : Success2XX<T>(HttpStatusCode.NON_AUTHORITATIVE_INFORMATION, headers) {
                override fun <R> map(map: ((T) -> R)) =
                    NonAuthoritativeInformation203(
                        headers,
                        body?.let { map(it) })
                override fun toString() = "NonAuthoritativeInformation203(body=$body, headers=$headers)"
            }

            class NoContent204<out T>(headers: Headers? = null, val body: T? = null)
                : Success2XX<T>(HttpStatusCode.NO_CONTENT, headers) {
                override fun <R> map(map: ((T) -> R)) =
                    NoContent204(
                        headers,
                        body?.let { map(it) })
                override fun toString() = "NoContent204(headers=$headers)"
            }

            class ResetContent205<out T>(headers: Headers? = null, val body: T? = null)
                : Success2XX<T>(HttpStatusCode.RESET_CONTENT, headers) {
                override fun <R> map(map: ((T) -> R)) =
                    ResetContent205(
                        headers,
                        body?.let { map(it) })
                override fun toString() = "ResetContent205(headers=$headers)"
            }

            class PartialContent206<T>(headers: Headers? = null, val body: T? = null)
                : Success2XX<T>(HttpStatusCode.PARTIAL_CONTENT, headers) {
                override fun <R> map(map: ((T) -> R)) =
                    PartialContent206(
                        headers,
                        body?.let { map(it) })
                override fun toString() = "PartialContent206(body=$body, headers=$headers)"
            }

            class MultiStatus207<T>(headers: Headers? = null, val body: T? = null)
                : Success2XX<T>(HttpStatusCode.MULTI_STATUS, headers) {
                override fun <R> map(map: ((T) -> R)) =
                    MultiStatus207(
                        headers,
                        body?.let { map(it) })
                override fun toString() = "MultiStatus207(body=$body, headers=$headers)"
            }

            class AlreadyReported208<T>(headers: Headers? = null, val body: T? = null)
                : Success2XX<T>(HttpStatusCode.ALREADY_REPORTED, headers) {
                override fun <R> map(map: ((T) -> R)) =
                    AlreadyReported208(
                        headers,
                        body?.let { map(it) })
                override fun toString() = "AlreadyReported208(body=$body, headers=$headers)"
            }

            class IMUsed226<T>(headers: Headers? = null, val body: T? = null)
                : Success2XX<T>(HttpStatusCode.IM_USED, headers) {
                override fun <R> map(map: ((T) -> R)) =
                    IMUsed226(
                        headers,
                        body?.let { map(it) })
                override fun toString() = "IMUsed226(body=$body, headers=$headers)"
            }
        }

        /* 3XX Redirection results */
        sealed class Redirection3XX<out T>(code: Int, headers: Headers? = null, errorBody: T? = null)
            : Some<T>(code, headers, errorBody) {
            override fun toString() = "Redirection3XX(headers=$headers)"
            class MultipleChoices300<out T>(headers: Headers? = null, errorBody: T? = null)
                : Redirection3XX<T>(HttpStatusCode.MULTIPLE_CHOICES, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    MultipleChoices300(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "MultipleChoices300(headers=$headers)"
            }

            class MovedPermanently301<out T>(headers: Headers? = null, errorBody: T? = null)
                : Redirection3XX<T>(HttpStatusCode.MOVED_PERMANENTLY, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    MovedPermanently301(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "MovedPermanently301(headers=$headers)"
            }

            class Found302<out T>(headers: Headers? = null, errorBody: T? = null)
                : Redirection3XX<T>(HttpStatusCode.FOUND, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    Found302(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "Found302(headers=$headers)"
            }

            class SeeOther303<out T>(headers: Headers? = null, errorBody: T? = null)
                : Redirection3XX<T>(HttpStatusCode.SEE_OTHER, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    SeeOther303(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "SeeOther303(headers=$headers)"
            }

            class NotModified304<out T>(headers: Headers? = null, errorBody: T? = null)
                : Redirection3XX<T>(HttpStatusCode.NOT_MODIFIED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    NotModified304(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "NotModified304(headers=$headers)"
            }

            class UseProxy305<out T>(headers: Headers? = null, errorBody: T? = null)
                : Redirection3XX<T>(HttpStatusCode.USE_PROXY, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    UseProxy305(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "UseProxy305(headers=$headers)"
            }

            class TemporaryRedirect307<out T>(headers: Headers? = null, errorBody: T? = null)
                : Redirection3XX<T>(HttpStatusCode.TEMPORARY_REDIRECT, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    TemporaryRedirect307(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "TemporaryRedirect307(headers=$headers)"
            }

            class PermanentRedirect308<out T>(headers: Headers? = null, errorBody: T? = null)
                : Redirection3XX<T>(HttpStatusCode.PERMANENT_REDIRECT, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    PermanentRedirect308(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "PermanentRedirect308(headers=$headers)"
            }
        }

        /* 4XX Client error results */
        sealed class ClientError4XX<out T>(code: Int, headers: Headers? = null, errorBody: T? = null)
            : Some<T>(code, headers, errorBody) {
            override fun toString() = "ClientError4XX(headers=$headers)"
            class BadRequest400<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.BAD_REQUEST, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    BadRequest400(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "BadRequest400(headers=$headers)"
            }

            class Unauthorized401<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.UNAUTHORIZED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    Unauthorized401(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "Unauthorized401(headers=$headers)"
            }

            class PaymentRequired402<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.PAYMENT_REQUIRED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    PaymentRequired402(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "PaymentRequired403(headers=$headers)"
            }

            class Forbidden403<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.FORBIDDEN, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    Forbidden403(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "Forbidden403(headers=$headers)"
            }

            class NotFound404<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.NOT_FOUND, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    NotFound404(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "NotFound404(headers=$headers)"
            }

            class MethodNotAllowed405<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.METHOD_NOT_ALLOWED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    MethodNotAllowed405(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "MethodNotAllowed405(headers=$headers)"
            }

            class NotAcceptable406<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.NOT_ACCEPTABLE, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    NotAcceptable406(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "NotAcceptable406(headers=$headers)"
            }

            class ProxyAuthenticationRequired407<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.PROXY_AUTHENTICATION_REQUIRED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    ProxyAuthenticationRequired407(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "ProxyAuthenticationRequired407(headers=$headers)"
            }

            class RequestTimeout408<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.REQUEST_TIMEOUT, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    RequestTimeout408(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "RequestTimeout408(headers=$headers)"
            }

            class Conflict409<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.CONFLICT, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    Conflict409(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "Conflict409(headers=$headers)"
            }

            class Gone410<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.GONE, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    Gone410(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "Gone410(headers=$headers)"
            }

            class LengthRequired411<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.LENGTH_REQUIRED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    LengthRequired411(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "LengthRequired411(headers=$headers)"
            }

            class PreconditionFailed412<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.PRECONDITION_FAILED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    PreconditionFailed412(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "PreconditionFailed412(headers=$headers)"
            }

            class PayloadTooLarge413<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.PAYLOAD_TOO_LARGE, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    PayloadTooLarge413(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "PayloadTooLarge413(headers=$headers)"
            }

            class URITooLong414<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.URI_TOO_LONG, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    URITooLong414(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "URITooLong414(headers=$headers)"
            }

            class UnsupportedMediaType415<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.UNSUPPORTED_MEDIA_TYPE, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    UnsupportedMediaType415(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "UnsupportedMediaType415(headers=$headers)"
            }

            class RangeNotSatisfiable416<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.RANGE_NOT_SATISFIABLE, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    RangeNotSatisfiable416(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "RangeNotSatisfiable416(headers=$headers)"
            }

            class ExpectationFailed417<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.EXPECTATION_FAILED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    ExpectationFailed417(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "ExpectationFailed417(headers=$headers)"
            }

            class ImATeapot418<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.IM_A_TEAPOT, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    ImATeapot418(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "ImATeapot417(headers=$headers)"
            }

            class EnhanceYourCalm420<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.ENHANCE_YOUR_CALM, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    EnhanceYourCalm420(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "EnhanceYourCalm417(headers=$headers)"
            }

            class MisdirectedRequest421<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.MISDIRECTED_REQUEST, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    MisdirectedRequest421(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "MisdirectedRequest421(headers=$headers)"
            }

            class UnprocessableEntry422<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.UN_PROCESSABLE_ENTRY, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    UnprocessableEntry422(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "UnprocessableEntry422(headers=$headers)"
            }

            class Locked423<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.LOCKED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    Locked423(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "Locked423(headers=$headers)"
            }

            class FailedDependency424<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.FAILED_DEPENDENCY, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    FailedDependency424(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "FailedDependency424(headers=$headers)"
            }

            class TooEarly425<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.TOO_EARLY, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    TooEarly425(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "TooEarly424(headers=$headers)"
            }

            class UpgradeRequired426<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.UPGRADE_REQUIRED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    UpgradeRequired426(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "UpgradeRequired426(headers=$headers)"
            }

            class PreconditionRequired428<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.PRECONDITION_REQUIRED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    PreconditionRequired428(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "PreconditionRequired428(headers=$headers)"
            }

            class TooManyRequests429<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.TOO_MANY_REQUESTS, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    TooManyRequests429(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "TooManyRequests429(headers=$headers)"
            }

            class RequestHeaderFieldsTooLarge431<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.REQUEST_HEADER_FIELDS_TOO_LARGE, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    RequestHeaderFieldsTooLarge431(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "RequestHeaderFieldsTooLarge431(headers=$headers)"
            }

            class NoResponse444<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.NO_RESPONSE, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    NoResponse444(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "NoResponse444(headers=$headers)"
            }

            class RetryWith449<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.RETRY_WITH, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    RetryWith449(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "RetryWith449(headers=$headers)"
            }

            class BlockedByWindowsParentalControls450<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.BLOCKED_BY_WINDOWS_PARENTAL_CONTROLS, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    BlockedByWindowsParentalControls450(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "BlockedByWindowsParentalControls450(headers=$headers)"
            }

            class UnavailableForLegalReasons451<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.UNAVAILABLE_FOR_LEGAL_REASONS, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    UnavailableForLegalReasons451(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "UnavailableForLegalReasons451(headers=$headers)"
            }

            class ClientClosedRequest499<out T>(headers: Headers? = null, errorBody: T? = null)
                : ClientError4XX<T>(HttpStatusCode.CLIENT_CLOSED_REQUEST, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    ClientClosedRequest499(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "ClientClosedRequest499(headers=$headers)"
            }
        }

        /* 5XX Server error results */
        sealed class ServerError5xx<out T>(code: Int, headers: Headers? = null, errorBody: T? = null)
            : Some<T>(code, headers, errorBody) {
            override fun toString() = "ServerError5xx(headers=$headers)"
            class InternalServerError500<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.INTERNAL_SERVER_ERROR, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    InternalServerError500(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "InternalServerError500(headers=$headers)"
            }

            class NotImplementedError501<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.NOT_IMPLEMENTED_ERROR, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    NotImplementedError501(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "NotImplementedError501(headers=$headers)"
            }

            class BadGateway502<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.BAD_GATEWAY, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    BadGateway502(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "BadGateway501(headers=$headers)"
            }

            class ServiceUnavailable503<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.SERVICE_UNAVAILABLE, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    ServiceUnavailable503(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "ServiceUnavailable503(headers=$headers)"
            }

            class GatewayTimeout504<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.GATEWAY_TIMEOUT, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    GatewayTimeout504(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "GatewayTimeout504(headers=$headers)"
            }

            class HTTPVersionNotSupported505<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.HTTP_VERSION_NOT_SUPPORTED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    HTTPVersionNotSupported505(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "HTTPVersionNotSupported505(headers=$headers)"
            }

            class VariantAlsoNegotiates506<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.VARIANT_ALSO_NEGOTIATES, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    VariantAlsoNegotiates506(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "VariantAlsoNegotiates506(headers=$headers)"
            }

            class InsufficientStorage507<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.INSUFFICIENT_STORAGE, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    InsufficientStorage507(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "InsufficientStorage507(headers=$headers)"
            }

            class LoopDetected508<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.LOOP_DETECTED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    LoopDetected508(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "LoopDetected508(headers=$headers)"
            }

            class BandwidthLimitExceeded509<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.BANDWIDTH_LIMIT_EXCEEDED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    BandwidthLimitExceeded509(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "BandwidthLimitExceeded509(headers=$headers)"
            }

            class NotExtended510<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.NOT_EXTENDED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    NotExtended510(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "NotExtended510(headers=$headers)"
            }

            class NetworkAuthenticationRequired511<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.NETWORK_AUTHENTICATION_REQUIRED, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    NetworkAuthenticationRequired511(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "NetworkAuthenticationRequired511(headers=$headers)"
            }

            class NetworkReadTimeoutError598<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.NETWORK_READ_TIMEOUT_ERROR, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    NetworkReadTimeoutError598(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "NetworkReadTimeoutError598(headers=$headers)"
            }

            class NetworkConnectTimeoutError599<out T>(headers: Headers? = null, errorBody: T? = null)
                : ServerError5xx<T>(HttpStatusCode.NETWORK_CONNECT_TIMEOUT_ERROR, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    NetworkConnectTimeoutError599(
                        headers,
                        errorBody?.let { map(it) })
                override fun toString() = "NetworkConnectTimeoutError599(headers=$headers)"
            }
        }

        /* Unknown error results */
        sealed class UnknownError<out T>(code: Int, headers: Headers? = null, errorBody: T? = null)
            : Some<T>(code, headers, errorBody) {
            override fun toString() = "UnknownError(headers=$headers)"

            class UnknownRequired<out T>(code: Int, headers: Headers? = null, val body: T, errorBody: T? = null)
                : UnknownError<T>(code, headers, errorBody) {
                override fun <R> map(map: ((T) -> R)) =
                    UnknownRequired(
                        code,
                        headers,
                        map(body),
                        errorBody?.let { map(it) })
                override fun toString() = "UnknownRequired(headers=$headers)"
            }
        }
    }

    /* Network error */
    class NetworkError<T>(val e: Throwable) : SealedApiResult<T>() {
        override fun <R> map(map: ((T) -> R)) = NetworkError<R>(e)
        override fun toString() = "NetworkError(e=${e.message})"
    }
}
