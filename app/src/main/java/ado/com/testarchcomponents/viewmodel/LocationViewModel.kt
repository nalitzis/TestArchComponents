package ado.com.testarchcomponents.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import ado.com.testarchcomponents.model.GpsLocation

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    val locationLiveData: LiveData<GpsLocation>

    init {
        locationLiveData = LocationLiveData(GpsLocationManager(application))
    }

    private class LocationLiveData
    constructor(private val mGpsLocationManager: GpsLocationManager) : LiveData<GpsLocation>() {

        //private val mGpsLocationListener = GpsLocationListener { this.value = it }

        private val mGpsLocationListener = object : GpsLocationListener {
            override fun onLocationChange(location: GpsLocation) { value = location }
        }

        override fun onActive() {
            mGpsLocationManager.start(mGpsLocationListener)
        }

        override fun onInactive() {
            mGpsLocationManager.stop()
        }
    }
}
