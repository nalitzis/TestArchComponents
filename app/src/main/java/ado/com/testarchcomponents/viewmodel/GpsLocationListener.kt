package ado.com.testarchcomponents.viewmodel

import ado.com.testarchcomponents.model.GpsLocation

interface GpsLocationListener {
    /**
     * Called when a new GPS location is available
     * @param location new location
     */
    fun onLocationChange(location: GpsLocation)

}
