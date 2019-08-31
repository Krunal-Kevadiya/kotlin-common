package com.kotlinlibrary.location

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Responsible for starting and stopping location updates=
 * @property isRequestOngoing AtomicBoolean which indicates that whether a location request is ongoing or not
 * @property mFusedLocationProviderClient (com.google.android.gms.location.FusedLocationProviderClient..com.google.android.gms.location.FusedLocationProviderClient?) used to request location
 * @property locationLiveData MutableLiveData<GeoLocationResult> contains location results
 * @property mLocationCallback <no name provided>
 * @constructor
 */
internal class LocationProvider(context: Context) {

    private val pendingIntent: PendingIntent by lazy {
        LocationBroadcastReceiver.getPendingIntent(context)
    }
    private val isRequestOngoing = AtomicBoolean().apply { set(false) }

    private var mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    internal var locationLiveData = MutableLiveData<GeoLocationResult>()

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            locationResult?.let { result ->
                if (result.locations.isNotEmpty()) {
                    sendResult(GeoLocationResult.Success(result.locations.first()))
                }
            }
        }
    }

    /**
     * Starts continuous location tracking using FusedLocationProviderClient
     *
     * If somehow continuous location retrieval fails then it tries to retrieve last known location.
     * */
    @SuppressLint("MissingPermission")
    internal fun startContinuousLocation(request: LocationRequest) {
        if (isRequestOngoing.getAndSet(true)) return
        mFusedLocationProviderClient?.requestLocationUpdates(
            request,
            mLocationCallback,
            Looper.getMainLooper()
        )?.addOnFailureListener {
            mFusedLocationProviderClient?.lastLocation?.addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                it.result?.let { location ->
                    sendResult(GeoLocationResult.Success(location))
                }
            }?.addOnFailureListener {
                sendResult(GeoLocationResult.Failure(it))
            }
        }
    }

    /**
     * Starts continuous location tracking using FusedLocationProviderClient
     *
     * If somehow continuous location retrieval fails then it tries to retrieve last known location.
     * */
    @SuppressLint("MissingPermission")
    internal fun startBackgroundLocationUpdates(context: Context, request: LocationRequest) {
        if (isRequestOngoing.getAndSet(true)) return
        mFusedLocationProviderClient?.requestLocationUpdates(
            request,
            pendingIntent
        )?.addOnFailureListener {
            mFusedLocationProviderClient?.lastLocation?.addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                it.result?.let { location ->
                    backgroundLocationLiveData.postValue(GeoLocationResult.Success(location))
                }
            }?.addOnFailureListener {
                backgroundLocationLiveData.postValue(GeoLocationResult.Failure(it))
            }
        }
    }

    /**
     * Sets result into live data synchronously
     * */
    private fun sendResult(result: GeoLocationResult) {
        locationLiveData.postValue(result)
    }

    /**
     * Stops location tracking by removing location callback from FusedLocationProviderClient
     * */
    internal fun stopContinuousLocation() {
        isRequestOngoing.set(false)
        locationLiveData.postValue(null)
        locationLiveData = MutableLiveData()
        mFusedLocationProviderClient?.removeLocationUpdates(mLocationCallback)
    }

    /**
     * Stops location tracking by removing location callback from FusedLocationProviderClient
     * */
    internal fun stopBackgroundLocation() {
        isRequestOngoing.set(false)
        backgroundLocationLiveData = MutableLiveData()
        mFusedLocationProviderClient?.removeLocationUpdates(pendingIntent)
    }
}