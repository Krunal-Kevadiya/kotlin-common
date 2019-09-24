package com.kotlinlibrary.location

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import com.google.android.gms.location.LocationResult
import com.kotlinlibrary.utils.ktx.logs

class LocationBroadcastReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_PROCESS_UPDATES =
            "com.kotlinlibrary.location.GeoLocation.LocationProvider.LocationBroadcastReceiver.action.PROCESS_UPDATES"

        fun getPendingIntent(context: Context): PendingIntent {
            val intent = Intent(context, LocationBroadcastReceiver::class.java)
            intent.action = ACTION_PROCESS_UPDATES
            return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        logs("onReceive -> ${intent?.extras}")
        if (intent != null && intent.action == ACTION_PROCESS_UPDATES) {
            if(intent.hasExtra(LocationManager.KEY_LOCATION_CHANGED)) {
                val location = intent.extras?.get(LocationManager.KEY_LOCATION_CHANGED) as? Location
                location?.let {
                    locationLiveData.postValue(GeoLocationResult.success(it))
                }
            } else {
                LocationResult.extractResult(intent)?.let { result ->
                    if (result.locations.isNotEmpty()) {
                        locationLiveData.postValue(GeoLocationResult.success(result.lastLocation))
                    }
                }
            }
        }
    }
}