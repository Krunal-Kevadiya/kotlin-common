package com.kotlinlibrary.location

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import com.kotlinlibrary.utils.ktx.locationManager
import com.kotlinlibrary.utils.ktx.logs
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Responsible for starting and stopping location updates=
 * @property isRequestOngoing AtomicBoolean which indicates that whether a location request is ongoing or not
 * @property mFusedLocationProviderClient (com.google.android.gms.location.FusedLocationProviderClient..com.google.android.gms.location.FusedLocationProviderClient?) used to request location
 * @constructor
 */
internal class LocationProvider(
    private val context: Context,
    private val fusedLocationApiUse: Boolean
) {

    private val pendingIntent: PendingIntent by lazy {
        LocationBroadcastReceiver.getPendingIntent(context)
    }
    private val isRequestOngoing = AtomicBoolean().apply { set(false) }
    private val mFusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    /**
     * Starts continuous location tracking using FusedLocationProviderClient
     *
     * If somehow continuous location retrieval fails then it tries to retrieve last known location.
     * */
    @SuppressLint("MissingPermission")
    internal fun startUpdates(request: LocationRequest) {
        if (isRequestOngoing.getAndSet(true)) return
        if (fusedLocationApiUse) {
            mFusedLocationProviderClient.requestLocationUpdates(request, pendingIntent)
                ?.addOnFailureListener { e ->
                    mFusedLocationProviderClient.lastLocation?.addOnCompleteListener {
                        if (!it.isSuccessful) return@addOnCompleteListener
                        it.result?.let { location ->
                            locationLiveData.postValue(GeoLocationResult.success(location))
                        }
                    }?.addOnFailureListener {
                        locationLiveData.postValue(GeoLocationResult.error(error = it))
                    }
                }
        } else {
            val locationManager = context.locationManager
            if(locationManager != null) {
                if(locationManager.allProviders.contains(LocationManager.GPS_PROVIDER) &&
                    locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        request.fastestInterval, 0.0f, pendingIntent)
                } else if (locationManager.allProviders.contains(LocationManager.NETWORK_PROVIDER) &&
                    locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        request.fastestInterval, 0.0f, pendingIntent)
                } else {
                    locationLiveData.postValue(GeoLocationResult.error(Throwable(Constants.LOCATION_MANAGER_DISABLE)))
                }
            } else {
                locationLiveData.postValue(GeoLocationResult.error(Throwable(Constants.LOCATION_MANAGER_DISABLE)))
            }
        }
    }

    /**
     * Initiates process to retrieve single location update
     * @param request LocationRequest instance that will be used to get location
     * @param onUpdate Called on success/failure result of the single update retrieval process
     */
    @SuppressLint("MissingPermission")
    internal fun getSingleUpdate(
        request: LocationRequest,
        onUpdate: (GeoLocationResult) -> Unit
    ) {
        fun startUpdates() {
            if(fusedLocationApiUse) {
                val callback = object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult?) {
                        result?.lastLocation?.let { location ->
                            onUpdate(GeoLocationResult.success(location))
                            mFusedLocationProviderClient.removeLocationUpdates(this)
                        }
                    }
                }
                mFusedLocationProviderClient.requestLocationUpdates(
                    request.apply { numUpdates = 1 },
                    callback,
                    Looper.getMainLooper()
                ).addOnFailureListener { error ->
                    onUpdate(GeoLocationResult.error(error = error))
                }
            } else {
                val locationManager = context.locationManager
                if(locationManager != null) {
                    val callback = object : LocationListener {
                        override fun onLocationChanged(result: Location?) {
                            result?.let { location ->
                                onUpdate(GeoLocationResult.success(location))
                                locationManager.removeUpdates(this)
                            }
                        }
                        override fun onStatusChanged(result: String?, status: Int, extras: Bundle?) {
                            logs("$result - $status - $extras")
                        }
                        override fun onProviderEnabled(result: String?) {
                            logs("$result")
                        }
                        override fun onProviderDisabled(result: String?) {
                            logs("$result")}
                    }
                    if(locationManager.allProviders.contains(LocationManager.GPS_PROVIDER) &&
                        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            request.fastestInterval, 0.0f, callback)
                    } else if (locationManager.allProviders.contains(LocationManager.NETWORK_PROVIDER) &&
                        locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            request.fastestInterval, 0.0f, callback)
                    } else {
                        onUpdate(GeoLocationResult.error(Throwable(Constants.LOCATION_MANAGER_DISABLE)))
                    }
                } else {
                    onUpdate(GeoLocationResult.error(Throwable(Constants.LOCATION_MANAGER_DISABLE)))
                }
            }
        }
        if (fusedLocationApiUse) {
            mFusedLocationProviderClient.lastLocation?.addOnSuccessListener { result ->
                result?.let { location ->
                    onUpdate(GeoLocationResult.success(location))
                } ?: startUpdates()
            }?.addOnFailureListener {
                startUpdates()
            }
        } else {
            val gpsLocation = context.locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if(gpsLocation != null) {
                onUpdate(GeoLocationResult.success(gpsLocation))
            } else {
                val networkLocation = context.locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if(networkLocation != null) {
                    onUpdate(GeoLocationResult.success(networkLocation))
                } else {
                    startUpdates()
                }
            }
        }
    }

    /**
     * Stops location tracking by removing location callback from FusedLocationProviderClient
     * */
    internal fun stopUpdates() {
        isRequestOngoing.set(false)
        locationLiveData = MutableLiveData()
        if (fusedLocationApiUse)
            mFusedLocationProviderClient.removeLocationUpdates(pendingIntent)
    }
}