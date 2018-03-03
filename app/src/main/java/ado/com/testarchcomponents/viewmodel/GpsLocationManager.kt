package ado.com.testarchcomponents.viewmodel

import android.arch.lifecycle.LifecycleObserver
import android.content.Context
import android.location.*
import android.os.Bundle
import android.util.Log

import ado.com.testarchcomponents.model.GpsLocation

class GpsLocationManager internal constructor(private val mApplicationContext: Context) : LocationListener, LifecycleObserver {

    companion object {
        private val TAG = "GpsLocationManager"
    }

    private var gpsLocationListener: GpsLocationListener? = null

    internal fun start(listener: GpsLocationListener) {
        gpsLocationListener = listener
        val locationManager = mApplicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        //TODO check permissions
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        } catch (e: SecurityException) {
            Log.e(TAG, "no permission given for GPS updates!")
        }
    }

    internal fun stop() {
        val locationManager = mApplicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.removeUpdates(this)
        gpsLocationListener = null
    }

    override fun onLocationChanged(location: Location) {
        Log.d(TAG, "onLocationChanged() " + location)
        gpsLocationListener?.onLocationChange(GpsLocation(location.latitude, location.longitude))
    }

    override fun onStatusChanged(s: String, i: Int, bundle: Bundle) {

    }

    override fun onProviderEnabled(s: String) {

    }

    override fun onProviderDisabled(s: String) {

    }
}
