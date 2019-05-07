/*
 * Copyright 2018 Captain App for Linker Logic Technologies, Inc.
 * Develop By Krunal Kevadiya
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.kotlincommon.sample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.LocationRequest
import com.kotlinlibrary.location.GeoLocation

class LocationActivity : AppCompatActivity() {
    private val geoLocation = GeoLocation(this) {
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