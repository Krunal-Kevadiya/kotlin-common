/*
 *
 *  Copyright 2018 Captain App for Linker Logic Technologies, Inc.
 *  Develop By Krunal Kevadiya
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */
package com.kotlinlibrary.utils

import android.content.Context
import android.content.SharedPreferences
import android.hardware.SensorManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

@Suppress("unchecked_cast")
fun <T> Context.getService(name: String): T? = getSystemService(name) as? T

val Context.connectivityManager: ConnectivityManager?
    get() = getService(Context.CONNECTIVITY_SERVICE)
val Context.inputMethodManager: InputMethodManager?
    get() = getService(Context.INPUT_METHOD_SERVICE)
val Context.locationManager: LocationManager?
    get() = getService(Context.LOCATION_SERVICE)
val Context.sensorManager: SensorManager?
    get() = getService(Context.SENSOR_SERVICE)
val Context.windowManager: WindowManager?
    get() = getService(Context.WINDOW_SERVICE)
