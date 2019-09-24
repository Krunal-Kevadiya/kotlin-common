package com.kotlinlibrary.location

import android.location.Location

/**
 * Represents states of GeoLocationResult library
 * */
class GeoLocationResult private constructor(
    val location: Location? = null,
    val error: Throwable? = null
) {
    companion object {
        internal fun error(error: Throwable) = GeoLocationResult(error = error)
        internal fun success(location: Location) = GeoLocationResult(location = location)
    }
}