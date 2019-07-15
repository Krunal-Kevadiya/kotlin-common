package com.kotlinlibrary.location

import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.gms.location.LocationRequest

/**
 * Marker class for GeoLocation Extensions
 * */
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE, AnnotationTarget.PROPERTY)
internal annotation class GeoLocationMarker

/**
 * A helper class for location extension which provides dsl extensions for getting location
 * */
@GeoLocationMarker
class GeoLocation(func: LocationOptions.() -> Unit = {}) {

    private var options = LocationOptions()

    init {
        configure(func)
    }

    /**
     * This class is used to create a default LocationRequest object if not provided externally
     * */
    private object Defaults {
        internal const val INTERVAL_IN_MS = 1000L
        internal const val FASTEST_INTERVAL_IN_MS = 1000L
        internal const val MAX_WAIT_TIME_IN_MS = 1000L
    }

    /**
     * Initializes GeoLocation class with default LocationOptions or user specific if provided externally
     * */
    init {
        options.locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        options.locationRequest.interval = Defaults.INTERVAL_IN_MS
        options.locationRequest.fastestInterval = Defaults.FASTEST_INTERVAL_IN_MS
        options.locationRequest.maxWaitTime = Defaults.MAX_WAIT_TIME_IN_MS
    }

    /**
     * creates LocationOptions object from user configuration
     * @param func is a lambda receiver for LocationOptions which is used to build LocationOptions object
     * */
    fun configure(func: LocationOptions.() -> Unit) {
        func(options)
    }

    /**
     * This function is used to get location for one time only. It handles most of the errors internally though
     * it doesn't any mechanism to handle errors externally.
     * */
    fun getCurrentLocation(activity: FragmentActivity, func: Location.() -> Unit): BlockExecution {
        val helper = getOrInitLocationHelper(activity.supportFragmentManager, true)
        val blockExecution = BlockExecution()
        helper.reset()
        helper.locationLiveData.watch(activity) { geo ->
            when (geo) {
                is GeoLocationResult.Success -> {
                    func(geo.location)
                }
                is GeoLocationResult.Failure -> {
                    blockExecution(geo.error)
                }
            }
        }
        Handler().post { helper.initPermissionModel() }
        return blockExecution
    }

    /**
     * This function is used to get location for one time only. It handles most of the errors internally though
     * it doesn't any mechanism to handle errors externally.
     * */
    fun getCurrentLocation(fragment: Fragment, func: Location.() -> Unit): BlockExecution {
        val helper = getOrInitLocationHelper(fragment.childFragmentManager, true)
        val blockExecution = BlockExecution()
        helper.reset()
        helper.locationLiveData.watch(fragment) { geo ->
            when (geo) {
                is GeoLocationResult.Success -> {
                    func(geo.location)
                }
                is GeoLocationResult.Failure -> {
                    blockExecution(geo.error)
                }
            }
        }
        Handler().post { helper.initPermissionModel() }
        return blockExecution
    }

    /**
     * This function is used to get location updates continuously. It handles most of the errors internally though
     * it doesn't any mechanism to handle errors externally.
     * */
    fun listenForLocation(activity: FragmentActivity, func: Location.() -> Unit): BlockExecution {
        val helper = getOrInitLocationHelper(activity.supportFragmentManager)
        val blockExecution = BlockExecution()
        helper.reset()
        helper.locationLiveData.watch(activity) { geo ->
            when (geo) {
                is GeoLocationResult.Success -> {
                    func(geo.location)
                }
                is GeoLocationResult.Failure -> {
                    blockExecution(geo.error)
                }
            }
        }
        Handler().post { helper.initPermissionModel() }
        return blockExecution
    }

    /**
     * This function is used to get location updates continuously. It handles most of the errors internally though
     * it doesn't any mechanism to handle errors externally.
     * */
    fun listenForLocation(fragment: Fragment, func: Location.() -> Unit): BlockExecution {
        val helper = getOrInitLocationHelper(fragment.childFragmentManager)
        val blockExecution = BlockExecution()
        helper.reset()
        helper.locationLiveData.watch(fragment) { geo ->
            when (geo) {
                is GeoLocationResult.Success -> {
                    func(geo.location)
                }
                is GeoLocationResult.Failure -> {
                    blockExecution(geo.error)
                }
            }
        }
        Handler().post { helper.initPermissionModel() }
        return blockExecution
    }

    /**
     * Getter method to get an Instance of LocationHelper. It always return an existing instance if there's any,
     * otherwise creates a new instance.
     * @return Instance of LocationHelper class which can be used to initiate Location Retrieval process.
     * */
    private fun getOrInitLocationHelper(manager: FragmentManager, isOneTime: Boolean = false): LocationHelper {
        var helper = manager.findFragmentByTag(LocationHelper.TAG) as? LocationHelper
        if (helper == null) {
            Log.e("LocationHelper", "No instance found so creating new")
            helper = LocationHelper.newInstance(options)
            helper.arguments = Bundle().apply {
                putBoolean(Constants.IS_ONE_TIME_BUNDLE_KEY, isOneTime)
            }
            manager.beginTransaction().add(helper, LocationHelper.TAG).commitNow()
        }
        return helper
    }

    /**
     * This function is used to stop receiving location updates.
     * */
    fun stopTrackingLocation(fragment: Fragment) {
        getOrInitLocationHelper(fragment.childFragmentManager).stopContinuousLocation()
    }

    /**
     * This function is used to stop receiving location updates.
     * */
    fun stopTrackingLocation(activity: FragmentActivity) {
        getOrInitLocationHelper(activity.supportFragmentManager).stopContinuousLocation()
    }
}