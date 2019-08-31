package com.kotlinlibrary.location

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.kotlinlibrary.R
import com.kotlinlibrary.utils.ktx.logs
import java.util.concurrent.atomic.AtomicBoolean

internal val isRequestingPermission = AtomicBoolean().apply {
    set(false)
}

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
class GeoLocation(func: Configuration.() -> Unit = {}) {
    private var options = Configuration()
    private lateinit var locationprovider: LocationProvider
    private val permissionBroadcastReceiver = PermissionBroadcastReceiver()
    private var runnable: Runnable? = null

    init {
        configure(func)
    }

    /**
     * creates Configuration object from user configuration
     * @param func is a lambda receiver for Configuration which is used to build Configuration object
     * */
    fun configure(func: Configuration.() -> Unit) {
        func(options)
    }

    fun startBackgroundLocationUpdates(
        service: Service,
        func: Location.() -> Unit
    ): BlockExecution {
        initLocationProvider(service)
        val blockExecution = BlockExecution()
        runnable = Runnable {
            backgroundLocationLiveData.observeForever { result ->
                when (result) {
                    is GeoLocationResult.Success -> {
                        func(result.location)
                    }
                    is GeoLocationResult.Failure -> {
                        blockExecution(result.error)
                    }
                }
            }
        }
        checkAndStartBackgroundUpdates(service)
        return blockExecution
    }

    /**
     * Initiates location retrieval process to receive only one location result.
     *
     * This method internally handles runtime location permission which is needed for Android M and above.
     * It also handles rationale dialogs and permission blocked dialogs also.
     * These dialogs can be configured using [configure] method.
     * It also handles location resolution process for requested location settings.
     * It shows setting resolution dialog if needed and ask for user's permission to change location settings.
     * Please note that these success and failure callbacks are lifecycle aware so no updates will be dispatched if the Activity is not in right state.
     *
     * @param activity FragmentActivity is the Activity instance from where the location request is triggered
     * @param func [@kotlin.ExtensionFunctionType] Function1<Location, Unit> provides a success block which will grant access to retrieved location
     * @return BlockExecution that can be used to handle exceptions and failures during location retrieval process
     */
    fun getCurrentLocation(activity: FragmentActivity, func: Location.() -> Unit): BlockExecution {
        initLocationProvider(activity)
        val blockExecution = observeOneTimeUpdates(activity, func)

        checkAndStartUpdates(activity)
        return blockExecution
    }

    /**
     * Initiates location retrieval process to receive only one location result.
     *
     * This method internally handles runtime location permission which is needed for Android M and above.
     * It also handles rationale dialogs and permission blocked dialogs also.
     * These dialogs can be configured using [configure] method.
     * It also handles location resolution process for requested location settings.
     * It shows setting resolution dialog if needed and ask for user's permission to change location settings.
     * Please note that these success and failure callbacks are lifecycle aware so no updates will be dispatched if the Fragment is not in right state.
     *
     * @param fragment Fragment is the Fragment from which the location request is initiated
     * @param func [@kotlin.ExtensionFunctionType] Function1<Location, Unit> provides a success block which will grant access to retrieved location
     * @return BlockExecution that can be used to handle exceptions and failures during location retrieval process
     */
    fun getCurrentLocation(fragment: Fragment, func: Location.() -> Unit): BlockExecution {
        if (!fragment.isAdded || fragment.activity == null) {
            Log.e("GeoLocation", "Cannot start location updates, Fragment is not attached yet.")
            return BlockExecution()
        }
        initLocationProvider(fragment.requireContext())
        val blockExecution = observeOneTimeUpdates(fragment, func)

        checkAndStartUpdates(fragment.requireContext())
        return blockExecution
    }

    /**
     * Initiates location retrieval process to receive continuous location updates.
     *
     * This method internally handles runtime location permission which is needed for Android M and above.
     * It also handles rationale dialogs and permission blocked dialogs also.
     * These dialogs can be configured using [configure] method.
     * It also handles location resolution process for requested location settings.
     * It shows setting resolution dialog if needed and ask for user's permission to change location settings.
     * Please note that these success and failure callbacks are lifecycle aware so no updates will be dispatched if the Activity is not in right state.
     *
     * @param activity FragmentActivity is the Activity instance from where the location request is triggered
     * @param func [@kotlin.ExtensionFunctionType] Function1<Location, Unit> provides a success block which will grant access to retrieved location
     * @return BlockExecution that can be used to handle exceptions and failures during location retrieval process
     */
    fun listenForLocation(activity: FragmentActivity, func: Location.() -> Unit): BlockExecution {
        initLocationProvider(activity)
        val blockExecution = observeForContinuesUpdates(activity, func)

        checkAndStartUpdates(activity)
        return blockExecution
    }

    /**
     * Initiates location retrieval process to receive continuous location updates.
     *
     * This method internally handles runtime location permission which is needed for Android M and above.
     * It also handles rationale dialogs and permission blocked dialogs also.
     * These dialogs can be configured using [configure] method.
     * It also handles location resolution process for requested location settings.
     * It shows setting resolution dialog if needed and ask for user's permission to change location settings.
     * Please note that these success and failure callbacks are lifecycle aware so no updates will be dispatched if the Fragment is not in right state.
     *
     * @param fragment Fragment is the Fragment from which the location request is initiated
     * @param func [@kotlin.ExtensionFunctionType] Function1<Location, Unit> provides a success block which will grant access to retrieved location
     * @return BlockExecution that can be used to handle exceptions and failures during location retrieval process
     */
    fun listenForLocation(fragment: Fragment, func: Location.() -> Unit): BlockExecution {
        if (!fragment.isAdded || fragment.activity == null) {
            Log.e("GeoLocation", "Cannot start location updates, Fragment is not attached yet.")
            return BlockExecution()
        }
        initLocationProvider(fragment.requireContext())
        val blockExecution = observeForContinuesUpdates(fragment, func)

        checkAndStartUpdates(fragment.requireContext())
        return blockExecution
    }

    /**
     * Initiates location retrieval process to receive one time location update without executing permission model.
     *
     * This method can be used to receive one time location update from services or from background.
     * It doesn't handle location permission model so before calling this method, ensure that the location permission is granted.
     *
     * @param context context used to initialize FusedLocationProviderClient
     * @param func [@kotlin.ExtensionFunctionType] Function1<Location, Unit> provides a success block which will grant access to retrieved location
     * @return BlockExecution that can be used to handle exceptions and failures during location retrieval process
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
    fun fetchCurrentLocation(context: Context, func: Location.() -> Unit): BlockExecution {
        initLocationProvider(context)
        val blockExecution = BlockExecution()
        runnable = Runnable {
            locationprovider.locationLiveData.observeOnce { result ->
                when (result) {
                    is GeoLocationResult.Success -> {
                        func(result.location)
                        locationprovider.stopContinuousLocation()
                    }
                    is GeoLocationResult.Failure -> {
                        blockExecution(result.error)
                    }
                }
                locationprovider.stopContinuousLocation()
            }
        }
        checkAndStartUpdatesWithoutPermissionRequest(context)
        return blockExecution
    }

    /**
     * Initiates location retrieval process to receive continuous location updates without executing permission model.
     *
     * This method can be used to receive location updates from services or from background.
     * It doesn't handle location permission model so before calling this method, ensure that the location permission is granted.
     * Also, it doesn't observe location updates in lifecycle aware way. So don't forget to stop updates manually when needed to be stopped.
     *
     * @param context context is the Fragment from which the location request is initiated
     * @param func [@kotlin.ExtensionFunctionType] Function1<Location, Unit> provides a success block which will grant access to retrieved location
     * @return BlockExecution that can be used to handle exceptions and failures during location retrieval process
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
    fun observeLocationUpdates(context: Context, func: Location.() -> Unit): BlockExecution {
        initLocationProvider(context)
        val blockExecution = BlockExecution()
        runnable = Runnable {
            locationprovider.locationLiveData.observeForever { result ->
                when (result) {
                    is GeoLocationResult.Success -> {
                        func(result.location)
                    }
                    is GeoLocationResult.Failure -> {
                        blockExecution(result.error)
                    }
                }
            }
        }
        checkAndStartUpdatesWithoutPermissionRequest(context)
        return blockExecution
    }

    /**
     * Registers an observer on location results that observes continuously. This observation is done in lifecycle aware way.
     * That means that no updates will be dispatched if if it is not the right lifecycle state.
     * @param owner LifecycleOwner is the owner of the lifecycle that will be used to observe on location results
     * @param func [@kotlin.ExtensionFunctionType] Function1<Location, Unit> will called when a new result is available
     * @return BlockExecution provides a way to handle errors and exceptions occurred during this process
     */
    private fun observeForContinuesUpdates(
        owner: LifecycleOwner,
        func: Location.() -> Unit
    ): BlockExecution {
        val blockExecution = BlockExecution()
        runnable = Runnable {
            locationprovider.locationLiveData.removeObservers(owner)
            locationprovider.locationLiveData.watch(owner) { result ->
                when (result) {
                    is GeoLocationResult.Success -> {
                        func(result.location)
                    }
                    is GeoLocationResult.Failure -> {
                        blockExecution(result.error)
                    }
                }
            }
        }
        return blockExecution
    }

    /**
     * Checks for location permission and then initiates permission model if the location permission is not granted already.
     * @param context Context is the Android Context
     */
    private fun checkAndStartUpdatesWithoutPermissionRequest(context: Context) {
        if (hasLocationPermission(context) && isSettingsEnabled(context)) {
            locationprovider.startContinuousLocation(options.locationRequest)
            runnable?.run()
            runnable = null
        } else {
            locationprovider.locationLiveData.postValue(GeoLocationResult.Failure(Throwable("Cannot continue without location permission")))
        }
    }

    /**
     * Checks for location permission and then initiates permission model if the location permission is not granted already.
     * @param context Context is the Android Context
     */
    private fun checkAndStartUpdates(context: Context) {
        if (hasLocationPermission(context) && isSettingsEnabled(context)) {
            locationprovider.startContinuousLocation(options.locationRequest)
            runnable?.run()
            runnable = null
        } else {
            LocalBroadcastManager
                .getInstance(context)
                .registerReceiver(permissionBroadcastReceiver, IntentFilter(context.packageName))
            initPermissionRequest(context)
        }
    }

    /**
     * Checks for location permission and then initiates permission model
     * if the location permission is not granted already.
     * @param context Context is the Android Context
     */
    private fun checkAndStartBackgroundUpdates(context: Context) {
        if (hasLocationPermission(context) && isSettingsEnabled(context)) {
            locationprovider.startBackgroundLocationUpdates(context, options.locationRequest)
            runnable?.run()
            runnable = null
        } else {
            LocalBroadcastManager
                .getInstance(context)
                .registerReceiver(permissionBroadcastReceiver, IntentFilter(context.packageName))
            initPermissionRequestForBackgroundUpdates(context)
        }
    }

    /**
     * Registers an observer on location results that observes only for one time. This observation is done in lifecycle aware way.
     * That means that no updates will be dispatched if if it is not the right lifecycle state.
     * @param owner LifecycleOwner is the owner of the lifecycle that will be used to observe on location results
     * @param func [@kotlin.ExtensionFunctionType] Function1<Location, Unit> will called when a new result is available
     * @return BlockExecution provides a way to handle errors and exceptions occurred during this process
     */
    private fun observeOneTimeUpdates(
        owner: LifecycleOwner,
        func: Location.() -> Unit
    ): BlockExecution {
        val blockExecution = BlockExecution()

        runnable = Runnable {
            locationprovider.locationLiveData.removeObservers(owner)
            locationprovider.locationLiveData.observeOnce(owner) { result ->
                when (result) {
                    is GeoLocationResult.Success -> {
                        func(result.location)
                    }
                    is GeoLocationResult.Failure -> {
                        blockExecution(result.error)
                    }
                }
                locationprovider.stopContinuousLocation()
            }
        }
        return blockExecution
    }

    /**
     * Initiates permission request by starting [GeoLocationActivity] which is responsible to handle permission model and location settings resolution
     * @param context Context is the Android Context used to start the [GeoLocationActivity]
     */
    private fun initPermissionRequest(context: Context) {
        if (!isRequestingPermission.getAndSet(true)) {
            context.startActivity(Intent(context, GeoLocationActivity::class.java).apply {
                if (options.shouldResolveRequest) {
                    putExtra(Constants.INTENT_EXTRA_CONFIGURATION, options)
                }
            })
        } else {
            logs("A request is already ongoing")
        }
    }

    /**
     * Initiates permission request by starting [GeoLocationActivity] which is responsible to handle permission model and location settings resolution
     * @param context Context is the Android Context used to start the [GeoLocationActivity]
     */
    private fun initPermissionRequestForBackgroundUpdates(context: Context) {
        if (!isRequestingPermission.getAndSet(true)) {
            val intent = Intent(context, GeoLocationActivity::class.java).apply {
                if (options.shouldResolveRequest) {
                    putExtra(Constants.INTENT_EXTRA_CONFIGURATION, options)
                    putExtra(Constants.INTENT_EXTRA_IS_BACKGROUND, true)
                }
            }
            val pendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            showPermissionNotification(context, pendingIntent)
        } else {
            logs("A request is already ongoing")
        }
    }

    private fun showPermissionNotification(context: Context, pendingIntent: PendingIntent) {
        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager ?: return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    "permission_channel",
                    "Permission Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            manager.createNotificationChannel(channel)
        }
        with(NotificationCompat.Builder(context, "permission_channel")) {
            setContentTitle("Require Location Permission")
            setContentText("This feature requires location permission to access device location. Please allow to access device location")
            setSmallIcon(R.drawable.ic_location_on)
            addAction(NotificationCompat.Action.Builder(0, "Grant", pendingIntent).build())
            priority = NotificationManager.IMPORTANCE_HIGH
            setAutoCancel(true)
            manager.notify(865, build())
        }
    }

    /**
     * Checks whether the app has location permission or not
     * @param context Context is the Android Context used to request a check for location permission
     * @return Boolean true if the location permission is already granted, false otherwise
     */
    private fun hasLocationPermission(context: Context) = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    /**
     * Checks whether the device location settings match with what the user requested
     * @return true is the current location settings satisfies the requirement, false otherwise.
     * */
    private fun isSettingsEnabled(context: Context): Boolean {
        if (!options.shouldResolveRequest) return true
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    /**
     * Initializes [LocationProvider] which is responsible to start and stop location updates
     * @param context Context is the Android Context used to initialize [LocationProvider]
     */
    private fun initLocationProvider(context: Context) {
        if (!::locationprovider.isInitialized) {
            locationprovider = LocationProvider(context)
        }
    }

    /**
     * Stops the ongoing location retrieval process.
     *
     * Note that this only stops location updates, it cannot stop ongoing permission request.
     *
     * @param owner LifecycleOwner is the same  Lifecycle owner that is used to start these location updates
     */
    fun stopTrackingLocation(owner: LifecycleOwner) {
        locationprovider.locationLiveData.removeObservers(owner)
        locationprovider.stopContinuousLocation()
    }

    /**
     * Stops the ongoing location retrieval process.
     * Note that this only stops location updates, it cannot stop ongoing permission request.
     */
    fun stopObservingLocation() {
        locationprovider.stopContinuousLocation()
    }

    /**
     * Stops the ongoing location retrieval process.
     * Note that this only stops location updates, it cannot stop ongoing permission request.
     */
    fun stopBackgroundLocation() {
        locationprovider.stopContinuousLocation()
    }

    /**
     * Resets location configs to default
     */
    fun setDefaultConfig() {
        options = Configuration()
    }

    /**
     * Receives local broadcasts related to permission model
     */
    inner class PermissionBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent?) {
            val status = intent?.getStringExtra(Constants.INTENT_EXTRA_PERMISSION_RESULT) ?: return
            val isBackground = intent.getBooleanExtra(Constants.INTENT_EXTRA_IS_BACKGROUND, false)
            isRequestingPermission.set(false)
            runnable?.run()
            runnable = null
            when (status) {
                Constants.GRANTED -> {
                    initLocationProvider(context)
                    if (isBackground) {
                        locationprovider.startBackgroundLocationUpdates(
                            context.applicationContext,
                            options.locationRequest
                        )
                    } else {
                        locationprovider.startContinuousLocation(options.locationRequest)
                    }
                }
                else -> {
                    locationprovider.locationLiveData.postValue(
                        GeoLocationResult.Failure(
                            Throwable(
                                status
                            )
                        )
                    )
                }
            }
            LocalBroadcastManager.getInstance(context)
                .unregisterReceiver(permissionBroadcastReceiver)
        }
    }
}

