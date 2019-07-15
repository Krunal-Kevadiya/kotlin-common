package com.kotlinlibrary.location

import com.google.android.gms.location.LocationRequest

/**
 * Data class to store location related configurations which includes dialog messages and instance of LocationRequest
 * class.
 * */
@GeoLocationMarker
class LocationOptions internal constructor() {

    var rationaleText: String =
        "Location permission is required in order to use this feature properly.Please grant the permission."
    var rationaleTitle: String = "Location permission required!"
    var blockedTitle: String = "Location Permission Blocked"
    var blockedText: String =
        "Location permission is blocked. Please allow permission from settings screen to use this feature"

    /**
     * Create an instance of LocationRequest class
     * @param func is a LocationRequest's lambda receiver which provide a block to configure LocationRequest
     * */
    fun request(func: (@GeoLocationMarker LocationRequest).() -> Unit) {
        locationRequest = LocationRequest().apply(func)
    }

    internal var locationRequest: LocationRequest = LocationRequest()

}