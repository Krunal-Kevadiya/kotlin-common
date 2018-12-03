package com.kotlincommon

import android.Manifest
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kotlinlibrary.location.LocationCallback
import com.kotlinlibrary.location.MyCurrentLocation
import com.kotlinlibrary.permission.KotlinPermissions

class LocationActivity: AppCompatActivity() {
    private lateinit var myCurrentLocation: MyCurrentLocation

    private val textView: TextView by lazy {
        findViewById<TextView>(R.id.textView)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        myCurrentLocation = MyCurrentLocation(this, true)
        checkLocation()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        myCurrentLocation.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStop() {
        super.onStop()
        myCurrentLocation.stopCurrentLocation()
    }

    fun checkLocation() {
        KotlinPermissions.with(this)
            .permissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
            .onAccepted {
                myCurrentLocation.getLocation(object : LocationCallback {
                    override fun onSuccess(location: Location) {
                        textView.text = "${location.latitude}, ${location.longitude}"
                    }

                    override fun onFailure(e: Exception) {
                        textView.text = e.message
                    }
                })
            }
            .onDenied {
                textView.text = "Permission -> Denied"
            }
            .onForeverDenied {
                textView.text = "Permission -> Forever Denied"
            }
            .ask()
    }
}