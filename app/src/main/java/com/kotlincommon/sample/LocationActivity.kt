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
import com.kotlinlibrary.location.GeoLocation

class LocationActivity : AppCompatActivity() {
    private val textView: TextView by lazy {
        findViewById<TextView>(R.id.textView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        checkLocation()
    }

    fun checkLocation() {
        GeoLocation.getCurrentLocation(this) {result ->
            result.location?.let { textView.text = "Latitude: ${it.latitude}\tLongitude: ${it.longitude}" }
            result.error?.let { textView.text = "Permission Denied: ${it.message}" }
        }
    }

    override fun onPause() {
        super.onPause()
        //stop receiving location when app is not in foreground.
        GeoLocation.stopLocationUpdates()
    }
}