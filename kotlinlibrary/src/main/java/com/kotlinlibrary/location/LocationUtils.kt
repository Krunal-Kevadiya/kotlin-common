package com.kotlinlibrary.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.provider.Settings
import android.util.Log
import com.kotlinlibrary.utils.connectivityManager
import com.kotlinlibrary.utils.locationManager
import java.io.IOException
import java.util.Locale

fun isSameProvider(provider1: String?, provider2: String?): Boolean =
    if (provider1 == null) provider2 == null else provider1 == provider2

fun isGpsAndNetLocUsable(context: Context): Boolean =
    isGpsLocUsable(context) || isNetLocEnabled(context)

fun isGpsLocUsable(context: Context): Boolean =
    context.locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false

fun isNetLocUsable(context: Context): Boolean {
    return isNetLocEnabled(context) &&
        confirmAirplaneModeOff(context) &&
        confirmWiFiAvailable(context)
}

// Check that Network Location Provider reports enabled
fun isNetLocEnabled(context: Context): Boolean =
    context.locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ?: false

// Check Wi-Fi is on
@SuppressLint("MissingPermission")
@Suppress("DEPRECATION")
fun confirmWiFiAvailable(context: Context): Boolean {
    val wifiInfo = context.connectivityManager?.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    return wifiInfo?.isAvailable ?: false
}

// Check Airplane Mode - we want airplane mode off
@Suppress("DEPRECATION")
fun confirmAirplaneModeOff(context: Context): Boolean {
    val airplaneSetting = Settings.System.getInt(context.contentResolver, Settings.System.AIRPLANE_MODE_ON, 0)
    return airplaneSetting == 0
}

// Address
fun Context.getGeoCoderAddress(currentBestLocation: Location? = null): List<Address>? {
    val geoCoder = Geocoder(this, Locale.ENGLISH)
    try {
        return geoCoder.getFromLocation(currentBestLocation?.latitude
            ?: 0.0, currentBestLocation?.longitude ?: 0.0, 1)
    } catch (e: IOException) {
        Log.e(MyCurrentLocation::class.java.simpleName, "Impossible to connect to Geocoder: $e")
    }

    return null
}

fun Context.getFirstAddress(): Address? {
    val addresses = getGeoCoderAddress()
    return if (addresses != null && addresses.isNotEmpty())
        addresses[0]
    else null
}

fun Context.getAddressLine(): String = getFirstAddress()?.getAddressLine(0) ?: ""
fun Context.getLocality(): String = getFirstAddress()?.locality ?: ""
fun Context.getPostalCode(): String = getFirstAddress()?.postalCode ?: ""
fun Context.getCountryName(): String = getFirstAddress()?.countryName ?: ""
