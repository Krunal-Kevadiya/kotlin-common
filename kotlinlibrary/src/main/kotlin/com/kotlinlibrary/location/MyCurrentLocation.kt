package com.kotlinlibrary.location

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Surface
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStates
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.kotlinlibrary.R
import com.kotlinlibrary.utils.locationManager
import com.kotlinlibrary.utils.sensorManager
import com.kotlinlibrary.utils.windowManager

class MyCurrentLocation(
    val context: Context,
    private val isFusedLocationApi: Boolean = false
) {
    private val sensorEventListener = SensorListener()
    private val fusedLocationListener = MyLocationListener()
    private val gpsLocationListener = LocationChangeListener()
    private val networkLocationListener = LocationChangeListener()

    companion object {
        const val REQUEST_CHECK_SETTINGS = 1011
        private const val TWO_MINUTES: Int = 10 * 1000 /* 10 secs */
        private const val MIN_BEARING_DIFF = 2.0f
        private const val FASTEST_INTERVAL_IN_MS: Long = 2000 /* 2 sec */
    }

    private var axisX: Int = 0
    private var axisY: Int = 0
    private var bearing: Float = 0f
    private var currentBestLocation: Location? = null
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var locationCallback: LocationCallback? = null
    private val mLocationRequest = LocationRequest()

    @SuppressLint("MissingPermission")
    fun stopCurrentLocation() {
        if (isFusedLocationApi) {
            mFusedLocationClient?.removeLocationUpdates(fusedLocationListener)
        } else {
            context.locationManager?.removeUpdates(gpsLocationListener)
            context.locationManager?.removeUpdates(networkLocationListener)
            context.sensorManager?.unregisterListener(sensorEventListener)
        }
        context.sensorManager?.unregisterListener(sensorEventListener)
    }

    @SuppressLint("MissingPermission")
    fun getLocation(callback: LocationCallback? = null) {
        callback?.let {
            locationCallback = it
        }

        if (isFusedLocationApi) {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        }

        mLocationRequest.interval = TWO_MINUTES.toLong()
        mLocationRequest.fastestInterval = FASTEST_INTERVAL_IN_MS
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        // or LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        builder.setAlwaysShow(true)
        val locationSettingsRequest = builder.build()
        val settingsClient = LocationServices.getSettingsClient(context)
        settingsClient.checkLocationSettings(locationSettingsRequest)
            .addOnSuccessListener {
                if (isFusedLocationApi) {
                    if (isGpsAndNetLocUsable(context)) {
                        getLastKnownLocation()
                    } else {
                        mFusedLocationClient?.requestLocationUpdates(mLocationRequest, fusedLocationListener, Looper.myLooper())
                    }
                } else {
                    val lastKnownGpsLocation = context.locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    val lastKnownNetworkLocation = context.locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    var bestLastKnownLocation = currentBestLocation

                    if (lastKnownGpsLocation != null && isBetterLocation(lastKnownGpsLocation, bestLastKnownLocation)) {
                        bestLastKnownLocation = lastKnownGpsLocation
                    }

                    if (lastKnownNetworkLocation != null && isBetterLocation(lastKnownNetworkLocation, bestLastKnownLocation)) {
                        bestLastKnownLocation = lastKnownNetworkLocation
                    }

                    currentBestLocation = bestLastKnownLocation

                    if (context.locationManager!!.allProviders.contains(LocationManager.GPS_PROVIDER) &&
                        context.locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        context.locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            FASTEST_INTERVAL_IN_MS, 0.0f, gpsLocationListener)
                    }

                    if (context.locationManager!!.allProviders.contains(LocationManager.NETWORK_PROVIDER) &&
                        context.locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                        context.locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            FASTEST_INTERVAL_IN_MS, 0.0f, networkLocationListener)
                    }

                    bestLastKnownLocation?.bearing = bearing
                    successLocation(currentBestLocation as Location)
                }
            }
            .addOnFailureListener {
                val statusCode = (it as ApiException).statusCode
                when (statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        resolveSetting(it as ResolvableApiException)
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        failLocation(Exception(context.getString(R.string.error_setting_location)))
                    }
                    else -> {
                        failLocation(Exception(context.getString(R.string.error_get_location)))
                    }
                }
            }
        val mSensor = context.sensorManager?.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        context.sensorManager?.registerListener(sensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL * 5)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_CANCELED) {
                val locationSettingsStates = LocationSettingsStates.fromIntent(data)
                if (locationSettingsStates.isLocationPresent && locationSettingsStates.isLocationUsable) {
                    getLocation()
                } else {
                    failLocation(Exception(context.getString(R.string.error_get_location)))
                }
            } else if (resultCode == Activity.RESULT_OK) {
                getLocation()
            }
        }
    }

    private fun successLocation(location: Location?) {
        if (location != null) {
            locationCallback?.onSuccess(location)
        } else {
            failLocation(Exception(context.getString(R.string.error_get_location)))
        }
    }

    private fun failLocation(e: Exception) {
        locationCallback?.onFailure(e)
    }

    private fun resolveSetting(e: ResolvableApiException) {
        try {
            e.startResolutionForResult((context as Activity), REQUEST_CHECK_SETTINGS)
        } catch (e: IntentSender.SendIntentException) {
            failLocation(e)
        }
    }

    private fun isBetterLocation(location: Location, currentBestLocation: Location?): Boolean {
        if (currentBestLocation == null)
            return true
        val timeDelta = location.time - currentBestLocation.time
        val isSignificantlyNewer = timeDelta > TWO_MINUTES
        val isSignificantlyOlder = timeDelta < -TWO_MINUTES
        val isNewer = timeDelta > 0

        if (isSignificantlyNewer) {
            return true
        } else if (isSignificantlyOlder) {
            return false
        }
        val accuracyDelta = (location.accuracy - currentBestLocation.accuracy).toInt()
        val isLessAccurate = accuracyDelta > 0
        val isMoreAccurate = accuracyDelta < 0
        val isSignificantlyLessAccurate = accuracyDelta > 200
        val isFromSameProvider = isSameProvider(location.provider, currentBestLocation.provider)

        if (isMoreAccurate) {
            return true
        } else if (isNewer && !isLessAccurate) {
            return true
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true
        }
        return false
    }

    private fun isSameProvider(provider1: String?, provider2: String?): Boolean =
        if (provider1 == null) provider2 == null else provider1 == provider2

    private inner class LocationChangeListener : android.location.LocationListener {
        var isFirstTime: Boolean = true
        override fun onLocationChanged(location: Location?) {
            if (location == null)
                return

            if (isFirstTime || (
                    location.distanceTo(currentBestLocation) > 200 &&
                        isBetterLocation(location, currentBestLocation))) {
                isFirstTime = false
                currentBestLocation = location
                currentBestLocation?.bearing = bearing
                successLocation(currentBestLocation as Location)
            }
        }

        override fun onProviderDisabled(provider: String?) {}
        override fun onProviderEnabled(provider: String?) {}
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    }

    private inner class SensorListener : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            if (sensor?.type == Sensor.TYPE_ROTATION_VECTOR) {
                Log.e(MyCurrentLocation::class.java.simpleName, "Rotation sensor accuracy changed to: $accuracy")
            }
        }

        override fun onSensorChanged(event: SensorEvent?) {
            val rotationMatrix = FloatArray(16)
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event?.values)
            val orientationValues = FloatArray(3)

            readDisplayRotation()

            SensorManager.remapCoordinateSystem(rotationMatrix, axisX, axisY, rotationMatrix)
            SensorManager.getOrientation(rotationMatrix, orientationValues)
            val azimuth = Math.toDegrees(orientationValues[0].toDouble())
            val abs = Math.abs(bearing.minus(azimuth).toFloat()) > MIN_BEARING_DIFF

            if (abs) {
                bearing = azimuth.toFloat()
                currentBestLocation?.bearing = bearing
            }
        }
    }

    private inner class MyLocationListener : com.google.android.gms.location.LocationCallback() {
        var isFirstTime: Boolean = true
        override fun onLocationResult(location: LocationResult?) {
            super.onLocationResult(location)
            if (location == null)
                return

            if (isFirstTime || (
                    location.lastLocation.distanceTo(currentBestLocation) > 200 &&
                        isBetterLocation(location.lastLocation, currentBestLocation))) {
                isFirstTime = false
                currentBestLocation = location.lastLocation
                currentBestLocation?.bearing = bearing
                successLocation(currentBestLocation as Location)
            }
        }

        override fun onLocationAvailability(p0: LocationAvailability?) {
            super.onLocationAvailability(p0)
            Log.e("Error", p0.toString())
        }
    }

    private fun readDisplayRotation() {
        axisX = SensorManager.AXIS_X
        axisY = SensorManager.AXIS_Y
        when (context.windowManager?.defaultDisplay?.rotation) {
            Surface.ROTATION_90 -> {
                axisX = SensorManager.AXIS_Y
                axisY = SensorManager.AXIS_MINUS_X
            }
            Surface.ROTATION_180 -> axisY = SensorManager.AXIS_MINUS_Y
            Surface.ROTATION_270 -> {
                axisX = SensorManager.AXIS_MINUS_Y
                axisY = SensorManager.AXIS_X
            }
            else -> {}
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        val locationAvailability = mFusedLocationClient?.locationAvailability
        locationAvailability?.addOnSuccessListener { locationAvailable ->
            if (locationAvailable.isLocationAvailable) {
                val locationTask = mFusedLocationClient?.lastLocation
                locationTask?.addOnSuccessListener { location ->
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        successLocation(location)
                    } else {
                        failLocation(Exception(context.getString(R.string.error_get_location)))
                    }
                }
                locationTask?.addOnFailureListener { exception ->
                    failLocation(exception)
                }
            } else {
                mFusedLocationClient?.requestLocationUpdates(mLocationRequest, fusedLocationListener, null)
            }
        }
        locationAvailability?.addOnFailureListener { exception ->
            failLocation(exception)
        }
    }
}
