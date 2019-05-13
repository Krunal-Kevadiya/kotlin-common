package com.kotlinlibrary.retrofitadapter

import okhttp3.Headers

sealed class SealedApiResult<out R, out E> {

    sealed class Some<out R, out E>(
        val code: Int, val headers: Headers? = null, val body: R?= null, val errorBody: E? = null
    ) : SealedApiResult<R, E>() {
        /* 1XX Informational results */
        sealed class Informational1XX<out R, out E>(
            code: Int, headers: Headers? = null, body: R?= null, errorBody: E? = null
        ): Some<R, E>(code, headers, body, errorBody) {
            class Continue100<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Informational1XX<R, E>(HttpStatusCode.CONTINUE, headers, body, errorBody) {
                override fun toString() = "Continue100[headers=$headers, body=$body, error=$errorBody]"
            }

            class SwitchingProtocols101<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Informational1XX<R, E>(HttpStatusCode.SWITCHING_PROTOCOLS, headers, body, errorBody) {
                override fun toString() = "SwitchingProtocols101[headers=$headers, body=$body, error=$errorBody]"
            }

            class Processing102<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Informational1XX<R, E>(HttpStatusCode.PROCESSING, headers, body, errorBody) {
                override fun toString() = "Processing102[headers=$headers, body=$body, error=$errorBody]"
            }

            class EarlyHints103<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Informational1XX<R, E>(HttpStatusCode.EARLY_HINTS, headers, body, errorBody) {
                override fun toString() = "EarlyHints103[headers=$headers, body=$body, error=$errorBody]"
            }
        }

        /* 2XX Success results */
        sealed class Success2XX<out R, out E>(
            code: Int, headers: Headers? = null, body: R?= null, errorBody: E? = null
        ): Some<R, E>(code, headers, body, errorBody) {
            class Ok200<R, E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Success2XX<R, E>(HttpStatusCode.OK, headers, body, errorBody) {
                override fun toString() = "Ok200[headers=$headers, body=$body, error=$errorBody]"
            }

            class Created201<R, E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Success2XX<R, E>(HttpStatusCode.CREATED, headers, body, errorBody) {
                override fun toString() = "Created201[headers=$headers, body=$body, error=$errorBody]"
            }

            class Accepted202<R, E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Success2XX<R, E>(HttpStatusCode.ACCEPTED, headers, body, errorBody) {
                override fun toString() = "Accepted202[headers=$headers, body=$body, error=$errorBody]"
            }

            class NonAuthoritativeInformation203<R, E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Success2XX<R, E>(HttpStatusCode.NON_AUTHORITATIVE_INFORMATION, headers, body, errorBody) {
                override fun toString() = "NonAuthoritativeInformation203[headers=$headers, body=$body, error=$errorBody]"
            }

            class NoContent204<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Success2XX<R, E>(HttpStatusCode.NO_CONTENT, headers, body, errorBody) {
                override fun toString() = "NoContent204[headers=$headers, body=$body, error=$errorBody]"
            }

            class ResetContent205<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Success2XX<R, E>(HttpStatusCode.RESET_CONTENT, headers, body, errorBody) {
                override fun toString() = "ResetContent205[headers=$headers, body=$body, error=$errorBody]"
            }

            class PartialContent206<R, E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Success2XX<R, E>(HttpStatusCode.PARTIAL_CONTENT, headers, body, errorBody) {
                override fun toString() = "PartialContent206[headers=$headers, body=$body, error=$errorBody]"
            }

            class MultiStatus207<R, E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Success2XX<R, E>(HttpStatusCode.MULTI_STATUS, headers, body, errorBody) {
                override fun toString() = "MultiStatus207[headers=$headers, body=$body, error=$errorBody]"
            }

            class AlreadyReported208<R, E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Success2XX<R, E>(HttpStatusCode.ALREADY_REPORTED, headers, body, errorBody) {
                override fun toString() = "AlreadyReported208[headers=$headers, body=$body, error=$errorBody]"
            }

            class IMUsed226<R, E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Success2XX<R, E>(HttpStatusCode.IM_USED, headers, body, errorBody) {
                override fun toString() = "IMUsed226[headers=$headers, body=$body, error=$errorBody]"
            }
        }

        /* 3XX Redirection results */
        sealed class Redirection3XX<out R, out E>(
            code: Int, headers: Headers? = null, body: R?= null, errorBody: E? = null
        ): Some<R, E>(code, headers, body, errorBody) {
            class MultipleChoices300<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Redirection3XX<R, E>(HttpStatusCode.MULTIPLE_CHOICES, headers, body, errorBody) {
                override fun toString() = "MultipleChoices300[headers=$headers, body=$body, error=$errorBody]"
            }

            class MovedPermanently301<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Redirection3XX<R, E>(HttpStatusCode.MOVED_PERMANENTLY, headers, body, errorBody) {
                override fun toString() = "MovedPermanently301[headers=$headers, body=$body, error=$errorBody]"
            }

            class Found302<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Redirection3XX<R, E>(HttpStatusCode.FOUND, headers, body, errorBody) {
                override fun toString() = "Found302[headers=$headers, body=$body, error=$errorBody]"
            }

            class SeeOther303<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Redirection3XX<R, E>(HttpStatusCode.SEE_OTHER, headers, body, errorBody) {
                override fun toString() = "SeeOther303[headers=$headers, body=$body, error=$errorBody]"
            }

            class NotModified304<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Redirection3XX<R, E>(HttpStatusCode.NOT_MODIFIED, headers, body, errorBody) {
                override fun toString() = "NotModified304[headers=$headers, body=$body, error=$errorBody]"
            }

            class UseProxy305<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Redirection3XX<R, E>(HttpStatusCode.USE_PROXY, headers, body, errorBody) {
                override fun toString() = "UseProxy305[headers=$headers, body=$body, error=$errorBody]"
            }

            class TemporaryRedirect307<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Redirection3XX<R, E>(HttpStatusCode.TEMPORARY_REDIRECT, headers, body, errorBody) {
                override fun toString() = "TemporaryRedirect307[headers=$headers, body=$body, error=$errorBody]"
            }

            class PermanentRedirect308<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : Redirection3XX<R, E>(HttpStatusCode.PERMANENT_REDIRECT, headers, body, errorBody) {
                override fun toString() = "PermanentRedirect308[headers=$headers, body=$body, error=$errorBody]"
            }
        }

        /* 4XX Client error results */
        sealed class ClientError4XX<out R, out E>(
            code: Int, headers: Headers? = null, body: R?= null, errorBody: E? = null
        ): Some<R, E>(code, headers, body, errorBody) {
            class BadRequest400<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.BAD_REQUEST, headers, body, errorBody) {
                override fun toString() = "BadRequest400[headers=$headers, body=$body, error=$errorBody]"
            }

            class Unauthorized401<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.UNAUTHORIZED, headers, body, errorBody) {
                override fun toString() = "Unauthorized401[headers=$headers, body=$body, error=$errorBody]"
            }

            class PaymentRequired402<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.PAYMENT_REQUIRED, headers, body, errorBody) {
                override fun toString() = "PaymentRequired403[headers=$headers, body=$body, error=$errorBody]"
            }

            class Forbidden403<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.FORBIDDEN, headers, body, errorBody) {
                override fun toString() = "Forbidden403[headers=$headers, body=$body, error=$errorBody]"
            }

            class NotFound404<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.NOT_FOUND, headers, body, errorBody) {
                override fun toString() = "NotFound404[headers=$headers, body=$body, error=$errorBody]"
            }

            class MethodNotAllowed405<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.METHOD_NOT_ALLOWED, headers, body, errorBody) {
                override fun toString() = "MethodNotAllowed405[headers=$headers, body=$body, error=$errorBody]"
            }

            class NotAcceptable406<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.NOT_ACCEPTABLE, headers, body, errorBody) {
                override fun toString() = "NotAcceptable406[headers=$headers, body=$body, error=$errorBody]"
            }

            class ProxyAuthenticationRequired407<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.PROXY_AUTHENTICATION_REQUIRED, headers, body, errorBody) {
                override fun toString() = "ProxyAuthenticationRequired407[headers=$headers, body=$body, error=$errorBody]"
            }

            class RequestTimeout408<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.REQUEST_TIMEOUT, headers, body, errorBody) {
                override fun toString() = "RequestTimeout408[headers=$headers, body=$body, error=$errorBody]"
            }

            class Conflict409<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.CONFLICT, headers, body, errorBody) {
                override fun toString() = "Conflict409[headers=$headers, body=$body, error=$errorBody]"
            }

            class Gone410<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.GONE, headers, body, errorBody) {
                override fun toString() = "Gone410[headers=$headers, body=$body, error=$errorBody]"
            }

            class LengthRequired411<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.LENGTH_REQUIRED, headers, body, errorBody) {
                override fun toString() = "LengthRequired411[headers=$headers, body=$body, error=$errorBody]"
            }

            class PreconditionFailed412<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.PRECONDITION_FAILED, headers, body, errorBody) {
                override fun toString() = "PreconditionFailed412[headers=$headers, body=$body, error=$errorBody]"
            }

            class PayloadTooLarge413<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.PAYLOAD_TOO_LARGE, headers, body, errorBody) {
                override fun toString() = "PayloadTooLarge413[headers=$headers, body=$body, error=$errorBody]"
            }

            class URITooLong414<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.URI_TOO_LONG, headers, body, errorBody) {
                override fun toString() = "URITooLong414[headers=$headers, body=$body, error=$errorBody]"
            }

            class UnsupportedMediaType415<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.UNSUPPORTED_MEDIA_TYPE, headers, body, errorBody) {
                override fun toString() = "UnsupportedMediaType415[headers=$headers, body=$body, error=$errorBody]"
            }

            class RangeNotSatisfiable416<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.RANGE_NOT_SATISFIABLE, headers, body, errorBody) {
                override fun toString() = "RangeNotSatisfiable416[headers=$headers, body=$body, error=$errorBody]"
            }

            class ExpectationFailed417<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.EXPECTATION_FAILED, headers, body, errorBody) {
                override fun toString() = "ExpectationFailed417[headers=$headers, body=$body, error=$errorBody]"
            }

            class ImATeapot418<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.IM_A_TEAPOT, headers, body, errorBody) {
                override fun toString() = "ImATeapot417[headers=$headers, body=$body, error=$errorBody]"
            }

            class EnhanceYourCalm420<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.ENHANCE_YOUR_CALM, headers, body, errorBody) {
                override fun toString() = "EnhanceYourCalm417[headers=$headers, body=$body, error=$errorBody]"
            }

            class MisdirectedRequest421<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.MISDIRECTED_REQUEST, headers, body, errorBody) {
                override fun toString() = "MisdirectedRequest421[headers=$headers, body=$body, error=$errorBody]"
            }

            class UnprocessableEntry422<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.UN_PROCESSABLE_ENTRY, headers, body, errorBody) {
                override fun toString() = "UnprocessableEntry422[headers=$headers, body=$body, error=$errorBody]"
            }

            class Locked423<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.LOCKED, headers, body, errorBody) {
                override fun toString() = "Locked423[headers=$headers, body=$body, error=$errorBody]"
            }

            class FailedDependency424<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.FAILED_DEPENDENCY, headers, body, errorBody) {
                override fun toString() = "FailedDependency424[headers=$headers, body=$body, error=$errorBody]"
            }

            class TooEarly425<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.TOO_EARLY, headers, body, errorBody) {
                override fun toString() = "TooEarly424[headers=$headers, body=$body, error=$errorBody]"
            }

            class UpgradeRequired426<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.UPGRADE_REQUIRED, headers, body, errorBody) {
                override fun toString() = "UpgradeRequired426[headers=$headers, body=$body, error=$errorBody]"
            }

            class PreconditionRequired428<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.PRECONDITION_REQUIRED, headers, body, errorBody) {
                override fun toString() = "PreconditionRequired428[headers=$headers, body=$body, error=$errorBody]"
            }

            class TooManyRequests429<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.TOO_MANY_REQUESTS, headers, body, errorBody) {
                override fun toString() = "TooManyRequests429[headers=$headers, body=$body, error=$errorBody]"
            }

            class RequestHeaderFieldsTooLarge431<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.REQUEST_HEADER_FIELDS_TOO_LARGE, headers, body, errorBody) {
                override fun toString() = "RequestHeaderFieldsTooLarge431[headers=$headers, body=$body, error=$errorBody]"
            }

            class NoResponse444<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.NO_RESPONSE, headers, body, errorBody) {
                override fun toString() = "NoResponse444[headers=$headers, body=$body, error=$errorBody]"
            }

            class RetryWith449<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.RETRY_WITH, headers, body, errorBody) {
                override fun toString() = "RetryWith449[headers=$headers, body=$body, error=$errorBody]"
            }

            class BlockedByWindowsParentalControls450<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.BLOCKED_BY_WINDOWS_PARENTAL_CONTROLS, headers, body, errorBody) {
                override fun toString() = "BlockedByWindowsParentalControls450[headers=$headers, body=$body, error=$errorBody]"
            }

            class UnavailableForLegalReasons451<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.UNAVAILABLE_FOR_LEGAL_REASONS, headers, body, errorBody) {
                override fun toString() = "UnavailableForLegalReasons451[headers=$headers, body=$body, error=$errorBody]"
            }

            class ClientClosedRequest499<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ClientError4XX<R, E>(HttpStatusCode.CLIENT_CLOSED_REQUEST, headers, body, errorBody) {
                override fun toString() = "ClientClosedRequest499[headers=$headers, body=$body, error=$errorBody]"
            }
        }

        /* 5XX Server error results */
        sealed class ServerError5xx<out R, out E>(
            code: Int, headers: Headers? = null, body: R?= null, errorBody: E? = null
        ): Some<R, E>(code, headers, body, errorBody) {
            class InternalServerError500<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.INTERNAL_SERVER_ERROR, headers, body, errorBody) {
                override fun toString() = "InternalServerError500[headers=$headers, body=$body, error=$errorBody]"
            }

            class NotImplementedError501<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.NOT_IMPLEMENTED_ERROR, headers, body, errorBody) {
                override fun toString() = "NotImplementedError501[headers=$headers, body=$body, error=$errorBody]"
            }

            class BadGateway502<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.BAD_GATEWAY, headers, body, errorBody) {
                override fun toString() = "BadGateway501[headers=$headers, body=$body, error=$errorBody]"
            }

            class ServiceUnavailable503<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.SERVICE_UNAVAILABLE, headers, body, errorBody) {
                override fun toString() = "ServiceUnavailable503[headers=$headers, body=$body, error=$errorBody]"
            }

            class GatewayTimeout504<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.GATEWAY_TIMEOUT, headers, body, errorBody) {
                override fun toString() = "GatewayTimeout504[headers=$headers, body=$body, error=$errorBody]"
            }

            class HTTPVersionNotSupported505<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.HTTP_VERSION_NOT_SUPPORTED, headers, body, errorBody) {
                override fun toString() = "HTTPVersionNotSupported505[headers=$headers, body=$body, error=$errorBody]"
            }

            class VariantAlsoNegotiates506<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.VARIANT_ALSO_NEGOTIATES, headers, body, errorBody) {
                override fun toString() = "VariantAlsoNegotiates506[headers=$headers, body=$body, error=$errorBody]"
            }

            class InsufficientStorage507<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.INSUFFICIENT_STORAGE, headers, body, errorBody) {
                override fun toString() = "InsufficientStorage507[headers=$headers, body=$body, error=$errorBody]"
            }

            class LoopDetected508<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.LOOP_DETECTED, headers, body, errorBody) {
                override fun toString() = "LoopDetected508[headers=$headers, body=$body, error=$errorBody]"
            }

            class BandwidthLimitExceeded509<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.BANDWIDTH_LIMIT_EXCEEDED, headers, body, errorBody) {
                override fun toString() = "BandwidthLimitExceeded509[headers=$headers, body=$body, error=$errorBody]"
            }

            class NotExtended510<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.NOT_EXTENDED, headers, body, errorBody) {
                override fun toString() = "NotExtended510[headers=$headers, body=$body, error=$errorBody]"
            }

            class NetworkAuthenticationRequired511<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.NETWORK_AUTHENTICATION_REQUIRED, headers, body, errorBody) {
                override fun toString() = "NetworkAuthenticationRequired511[headers=$headers, body=$body, error=$errorBody]"
            }

            class NetworkReadTimeoutError598<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.NETWORK_READ_TIMEOUT_ERROR, headers, body, errorBody) {
                override fun toString() = "NetworkReadTimeoutError598[headers=$headers, body=$body, error=$errorBody]"
            }

            class NetworkConnectTimeoutError599<out R, out E>(headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : ServerError5xx<R, E>(HttpStatusCode.NETWORK_CONNECT_TIMEOUT_ERROR, headers, body, errorBody) {
                override fun toString() = "NetworkConnectTimeoutError599[headers=$headers, body=$body, error=$errorBody]"
            }
        }

        /* Unknown error results */
        sealed class UnknownError<out R, out E>(
            code: Int, headers: Headers? = null, body: R?= null, errorBody: E? = null
        ): Some<R, E>(code, headers, body, errorBody) {
            class UnknownRequired<out R, out E>(code: Int, headers: Headers? = null, body: R?= null, errorBody: E? = null)
                : UnknownError<R, E>(code, headers, body, errorBody) {
                override fun toString() = "UnknownRequired[code=$code, headers=$headers, body=$body, error=$errorBody]"
            }
        }
    }

    /* Network error */
    class NetworkError<out R, out E>(val e: Throwable) : SealedApiResult<R, E>() {
        override fun toString() = "NetworkError(e=${e.message})"
    }
}
