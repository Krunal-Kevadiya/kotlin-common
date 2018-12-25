package com.kotlincommon.sample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.LocationRequest
import com.kotlinlibrary.location.GeoLocation

class LocationActivity: AppCompatActivity() {
    private val geoLocation = GeoLocation(this){
        rationaleText = "This is custom rationale text"
        blockedText = "The permission is blocked and this is custom blocked message"
        request {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
            fastestInterval = 1000
        }
    }
    /*
    * on fragment
    * lateinit var geoLocation: GeoLocation
    * override fun onAttach(context: Context?) {
    *     super.onAttach(context)
    *     this.geoLocation = GeoLocation(requireActivity())
    * }
    * */

    private val textView: TextView by lazy {
        findViewById<TextView>(R.id.textView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        checkLocation()
    }

    fun checkLocation() {
        geoLocation.getCurrentLocation({ location ->
            textView.text = "Latitude: ${location.latitude}\tLongitude: ${location.longitude}"
        }, { error ->
            textView.text = "Permission Denied: ${error.isPermissionDenied}\tThrowable: ${error.throwable?.message}"
        })

        /**
         * Continue
         * geoLocation.listenForLocation({ location ->
         *    textView.text = "Latitude: ${location.latitude}\tLongitude: ${location.longitude}"
         *}, { error ->
         *    textView.text = "Permission Denied: ${error.isPermissionDenied}\tThrowable: ${error.throwable?.message}"
         *})
         * Stop Receiving Location
         * geoLocation.stopTrackingLocation()
         */
    }
}