package com.kotlinlibrary.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest

class LocationProvider(context: Context, val options: LocationOptions, val isOneTime: Boolean) {

    private var mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    internal val locationLiveData = MutableLiveData<GeoLocationResult>()

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            locationResult?.let { result ->
                if (result.locations.isNotEmpty()) {
                    sendResult(GeoLocationResult.Success(result.locations.first()))
                    if (isOneTime) {
                        stopContinuousLocation()
                        locationLiveData.value = null
                    }
                }
            }
        }
    }

    init {
        checkIfLocationSettingsAreEnabled(context, options)
    }

    /**
     * Checks whether the current location settings allows retrieval of location or not.
     * If settings are enabled then retrieves the location, otherwise initiate the process of settings resolution
     * */
    private fun checkIfLocationSettingsAreEnabled(context: Context, options: LocationOptions) {
        if (checkIfRequiredLocationSettingsAreEnabled(context)) {
            getLastKnownLocation()
        } else {
            val builder = LocationSettingsRequest.Builder()
            builder.addLocationRequest(options.locationRequest)
            builder.setAlwaysShow(true)

            val client = LocationServices.getSettingsClient(context)
            val locationSettingsResponseTask = client.checkLocationSettings(builder.build())
            locationSettingsResponseTask.addOnSuccessListener {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                getLastKnownLocation()
            }
            locationSettingsResponseTask.addOnFailureListener { exception ->
                sendResult(GeoLocationResult.Failure(exception))
            }
        }
    }

    /**
     * Checks whether the device location settings match with what the user requested
     * @return true is the current location settings satisfies the requirement, false otherwise.
     * */
    private fun checkIfRequiredLocationSettingsAreEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    /**
     * Retrieves the last known location using FusedLocationProviderClient.
     * In case of no last known location, initiates continues location to get a result.
     * */
    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        if (!isOneTime) {
            startContinuousLocation()
            return
        }
        mFusedLocationProviderClient?.lastLocation?.addOnSuccessListener { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                sendResult(GeoLocationResult.Success(location))
                stopContinuousLocation()
            } else {
                startContinuousLocation()
            }
        }?.addOnFailureListener { exception ->
            sendResult(GeoLocationResult.Failure(exception))
        }
    }

    /**
     * Starts continuous location tracking using FusedLocationProviderClient
     * */
    @SuppressLint("MissingPermission")
    private fun startContinuousLocation() {
        mFusedLocationProviderClient?.requestLocationUpdates(
            options.locationRequest,
            mLocationCallback,
            Looper.getMainLooper()
        )?.addOnFailureListener { exception ->
            sendResult(GeoLocationResult.Failure(exception))
        }
    }

    /**
     * Sets result into live data synchronously
     * */
    private fun sendResult(result: GeoLocationResult) {
        locationLiveData.value = result
    }

    /**
     * Stops location tracking by removing location callback from FusedLocationProviderClient
     * */
    internal fun stopContinuousLocation() {
        mFusedLocationProviderClient?.removeLocationUpdates(mLocationCallback)
    }
}