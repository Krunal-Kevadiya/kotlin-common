package com.kotlinlibrary.location

/**
 * Holds all the constants used for the lib
 * */
internal object Constants {
    const val PERMISSION_NOTIFICATION_ID = 865
    internal const val DENIED = "denied"
    internal const val GRANTED = "granted"
    internal const val PERMANENTLY_DENIED = "permanently_denied"
    internal const val RESOLUTION_FAILED = "resolution_failed"
    internal const val LOCATION_SETTINGS_DENIED = "location_settings_denied"
    internal const val LOCATION_MANAGER_DISABLE = "location_manager_disable"
    internal const val INTENT_EXTRA_CONFIGURATION = "request"
    internal const val INTENT_EXTRA_IS_SINGLE_UPDATE = "isSingleUpdate"
    internal const val INTENT_EXTRA_IS_BACKGROUND = "is_background"
    internal const val INTENT_EXTRA_PERMISSION_RESULT = "permission_result"
}