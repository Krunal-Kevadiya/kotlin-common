package com.kotlinlibrary.location

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationResult

internal var backgroundLocationLiveData = MutableLiveData<GeoLocationResult>()

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
        intent ?: return
        if (intent.action == ACTION_PROCESS_UPDATES) {
            LocationResult.extractResult(intent)?.let { result ->
                if (result.locations.isNotEmpty()) {
                    backgroundLocationLiveData.postValue(GeoLocationResult.Success(result.lastLocation))
                }
            }
        }
    }
}