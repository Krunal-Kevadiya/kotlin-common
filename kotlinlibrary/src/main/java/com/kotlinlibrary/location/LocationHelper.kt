package com.kotlinlibrary.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.kotlinlibrary.R
import com.kotlinlibrary.utils.ktx.logs

/**
 * Helper headless fragment to handle permission model and location retrieval process.
 *
 * */
class LocationHelper : Fragment() {
    private var isOneTime = false
    private var isRationaleDisplayed = false
    private var isJustBlocked = true
    private var options: LocationOptions = LocationOptions()

    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            locationResult?.let {
                if (it.locations.isNotEmpty()) {
                    success(it.locations.first())
                    if (isOneTime) {
                        stopContinuousLocation()
                    }
                }
            }
        }
    }

    companion object {
        val TAG: String = this::class.java.simpleName
        private const val REQUEST_CODE_LOCATION_SETTINGS = 123
        private const val LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION
        private const val REQUEST_LOCATION_CODE = 7

        /**
         * Creates a new instance o this class and returns it.
         * */
        fun newInstance(): LocationHelper {
            return LocationHelper()
        }
    }

    var success: (Location) -> Unit = {}
    var failure: (LocationError) -> Unit = {}

    /**
     * retainInstance if set to true, makes sure that this fragment instance persist though configuration changes.
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    /**
     * Initiates main location process by initiating permission model.
     *
     * @param options provides the dialog messages and LocationRequest instance to configure location request process.
     * @param success is a function which will be called when the location is retrieved successfully.
     * @param failure is a function which will be called when the user denies the permission or if there's any error
     * getting location.
     * @param isOneTime is a flag that indicates whether the location update should be sent only once or continuous.
     *
     * */
    fun startLocationProcess(
        options: LocationOptions = LocationOptions(),
        success: (Location) -> Unit,
        failure: (LocationError) -> Unit,
        isOneTime: Boolean
    ) {
        this.options = options
        this.success = success
        this.failure = failure
        this.isOneTime = isOneTime
        initPermissionModel()
    }

    /**
     * Initiates permission model to request location permission in order to retrieve location successfully.=
     * */
    private fun initPermissionModel() {
        when (hasLocationPermission()) {
            //has permission to access location
            true -> initLocationTracking()
            false -> {
                //doesn't have permission, checking if user has been asked for permission earlier
                when (isFirstRequest()) {
                    //permission is requested first time, directly prompt user for permission
                    true -> requestLocationPermission()
                    false -> {
                        //permission is not asked for first time, display rationaleText and then prompt user for permission
                        displayRationale()
                        isRationaleDisplayed = true
                    }
                }
            }
        }
    }

    /**
     * Displays a rational dialog to provide more information about the necessity of location permission
     * */
    private fun displayRationale() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.permission_required_title))
            .setMessage(options.rationaleText)
            .setPositiveButton(getString(R.string.grant)) { dialog, _ ->
                requestLocationPermission()
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
                failure(LocationError(true))
            }.create().show()
    }

    /**
     * Requests user for location permission
     * */
    private fun requestLocationPermission() {
        requestPermissions(arrayOf(LOCATION_PERMISSION), REQUEST_LOCATION_CODE)
    }

    /**
     * Checks whether the location permission is requested for the first time or not.
     *
     * @return true if the location permission is requested for the first time, false otherwise.
     *
     * */
    private fun isFirstRequest(): Boolean {
        return if (isApiLevelAbove23())
            !requireActivity().shouldShowRequestPermissionRationale(LOCATION_PERMISSION)
        else
            false
    }

    /**
     * Checks whether the app has location permission or not
     *
     * @return true is the app has location permission, false otherwise.
     *
     * */
    private fun hasLocationPermission(): Boolean {
        return if (isApiLevelAbove23())
            requireActivity().checkSelfPermission(LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED
        else
            true
    }

    /**
     * Checks whether the android os version is 23+ or not.
     *
     * @return true is the android version is 23 or above, false otherwise.
     *
     * */
    private fun isApiLevelAbove23(): Boolean {
        return Build.VERSION.SDK_INT >= 23
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_LOCATION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initLocationTracking()
                } else {
                    if (!shouldShowRequestPermissionRationale(LOCATION_PERMISSION)) {
                        //means permission is permanently blockedText by user
                        if (!isJustBlocked) {
                            showPermissionBlockedDialog()
                        } else
                            isJustBlocked = false
                    }
                    failure(LocationError(true))
                }
            }
        }
    }

    /**
     * This function is used to show a 'Permission Blocked' dialog when the permission is permanently denied.
     * */
    private fun showPermissionBlockedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.permission_blocked_title))
            .setMessage(options.blockedText)
            .setPositiveButton(getString(R.string.enable)) { dialog, _ ->
                dialog.dismiss()
                openSettings()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    /**
     * Opens app settings screen
     * */
    private fun openSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", requireActivity().packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_LOCATION_SETTINGS) {
            data?.let {
                val locationSettingsStates = LocationSettingsStates.fromIntent(it)
                when (resultCode) {
                    Activity.RESULT_OK -> initPermissionModel()
                    Activity.RESULT_CANCELED ->
                        // The user was asked to change settings, but chose not to
                        onResolveLocationSettingCancelled(locationSettingsStates)
                }
            }
        }
    }

    /**
     * Called when resolution of location setting is cancelled
     *
     * @param locationSettingsStates a settings state instance that determines if the current location settings are
     * usable or not.
     *
     * */
    private fun onResolveLocationSettingCancelled(locationSettingsStates: LocationSettingsStates) {
        if (locationSettingsStates.isLocationPresent && locationSettingsStates.isLocationUsable) {
            initPermissionModel()
        }
    }

    /**
     * This function initiates location tracking if the permission model succeeds.
     *
     * */
    private fun initLocationTracking() {
        //init location here
        initializeFusedLocationProviderClient()
        checkIfLocationSettingsAreEnabled()
    }

    /**
     * Initiates FusedLocationProviderClient instance
     * */
    private fun initializeFusedLocationProviderClient() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    /**
     * Checks whether the current location settings allows retrieval of location or not.
     * If settings are enabled then retrieves the location, otherwise initiate the process of settings resolution
     *
     * */
    private fun checkIfLocationSettingsAreEnabled() {
        if (checkIfRequiredLocationSettingsAreEnabled()) {
            getLastKnownLocation()
        } else {
            val builder = LocationSettingsRequest.Builder()
            builder.addLocationRequest(options.locationRequest)
            builder.setAlwaysShow(true)

            val client = LocationServices.getSettingsClient(requireContext())
            val locationSettingsResponseTask = client.checkLocationSettings(builder.build())
            locationSettingsResponseTask.addOnSuccessListener {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                getLastKnownLocation()
            }
            locationSettingsResponseTask.addOnFailureListener { exception ->
                if (exception is ResolvableApiException) {
                    onResolutionNeeded(exception)
                } else {
                    failure(LocationError(false, exception))
                }
            }
        }
    }

    /**
     * This function is called when resolution of location settings is needed.
     * Shows a dialog that location resolution is needed.
     *
     * @param exception is an instance of ResolvableApiException which determines whether the resolution
     * is possible or not
     *
     * */
    private fun onResolutionNeeded(exception: ResolvableApiException) {
        exception.printStackTrace()
        if (!shouldBeAllowedToProceed()) return
        if (!requireActivity().isFinishing) {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.location_is_currently_disabled))
                .setMessage(getString(R.string.please_enable_access_to_location))
                .setPositiveButton(getString(R.string.btn_settings)) { dialog, _ ->
                    resolveLocationSettings(exception)
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.btn_cancel)) { _, _ ->
                }.create().show()
        }
    }

    /**
     * Initiates location settings resolution process.
     *
     * @param exception is used to resolve location settings
     *
     * */
    private fun resolveLocationSettings(exception: Exception) {
        val resolvable = exception as ResolvableApiException
        try {
            startIntentSenderForResult(
                resolvable.resolution.intentSender,
                REQUEST_CODE_LOCATION_SETTINGS,
                null,
                0,
                0,
                0,
                null
            )
        } catch (e1: IntentSender.SendIntentException) {
            logs(e1)
        }
    }

    /**
     * Checks whether to continue the process or not. Makes sure the fragment is in foreground.
     *
     * */
    private fun shouldBeAllowedToProceed(): Boolean {
        return lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)
    }

    /**
     * Checks whether the device location settings match with what the user requested
     *
     * @return true is the current location settings satisfies the requirement, false otherwise.
     *
     * */
    private fun checkIfRequiredLocationSettingsAreEnabled(): Boolean {
        val locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    /**
     * Retrieves the last known location using FusedLocationProviderClient.
     * In case of no last known location, initiates continues location to get a result.
     *
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
                success(location)
            } else {
                startContinuousLocation()
            }
        }?.addOnFailureListener { exception ->
            failure(LocationError(false, exception))
        }
    }

    /**
     * Starts continuous location tracking using FusedLocationProviderClient
     *
     * */
    @SuppressLint("MissingPermission")
    private fun startContinuousLocation() {
        mFusedLocationProviderClient?.requestLocationUpdates(
            options.locationRequest,
            mLocationCallback,
            Looper.getMainLooper()
        )?.addOnFailureListener { exception ->
            failure(LocationError(false, exception))
        }
    }

    /**
     * Stops location tracking by removing location callback from FusedLocationProviderClient
     * */
    internal fun stopContinuousLocation() {
        mFusedLocationProviderClient?.removeLocationUpdates(mLocationCallback)
    }
}

/**
 * Helper class to handle errors which can be passed to the top abstraction layer.
 * */
class LocationError(val isPermissionDenied: Boolean, val throwable: Throwable? = null)