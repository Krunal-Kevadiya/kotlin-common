package com.kotlinlibrary.location

import android.os.Parcelable
import com.google.android.gms.location.LocationRequest
import kotlinx.android.parcel.Parcelize

/**
 * Data class to store location related configurations which includes dialog messages and instance of LocationRequest
 * class.
 * */
@GeoLocationMarker
@Parcelize
data class Configuration(
    var rationaleText: String =
        "Location permission is required in order to use this feature properly.Please grant the permission.",
    var rationaleTitle: String = "Location permission required!",
    var blockedTitle: String = "Location Permission Blocked",
    var blockedText: String =
        "Location permission is blocked. Please allow permission from settings screen to use this feature",
    var resolutionTitle: String = "Location is currently disabled",
    var resolutionText: String = "Please enable access to device location to proceed further.",
    internal var locationRequest: LocationRequest = getDefaultRequest(),
    var shouldResolveRequest: Boolean = true
) : Parcelable {

    companion object {
        internal const val INTERVAL_IN_MS = 1000L
        internal const val FASTEST_INTERVAL_IN_MS = 1000L
        internal const val MAX_WAIT_TIME_IN_MS = 1000L
    }

    /**
     * Create an instance of LocationRequest class
     * @param func is a LocationRequest's lambda receiver which provide a block to configure LocationRequest
     * */
    fun request(func: (@GeoLocationMarker LocationRequest).() -> Unit) {
        locationRequest = LocationRequest().apply(func)
    }

}

/**
 * Creates [LocationRequest] instance with default settings
 * @return LocationRequest
 */
private fun getDefaultRequest(): LocationRequest {
    return LocationRequest().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        interval = Configuration.INTERVAL_IN_MS
        fastestInterval = Configuration.FASTEST_INTERVAL_IN_MS
        maxWaitTime = Configuration.MAX_WAIT_TIME_IN_MS
    }
}
