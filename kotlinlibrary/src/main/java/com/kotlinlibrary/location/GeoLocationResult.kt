package com.kotlinlibrary.location

import android.location.Location

/**
 * Represents states of GeoLocationResult library
 * */
sealed class GeoLocationResult {

    /**
     * Represents success state for retrieving location
     * */
    data class Success internal constructor(val location: Location) : GeoLocationResult()

    /**
     * Represents failure state for location process
     * */
    data class Failure internal constructor(val error: Throwable) : GeoLocationResult()

}