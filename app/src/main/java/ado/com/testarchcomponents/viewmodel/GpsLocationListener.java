package ado.com.testarchcomponents.viewmodel;

import ado.com.testarchcomponents.model.GpsLocation;

public interface GpsLocationListener {
    /**
     * Called when a new GPS location is available
     * @param location new location
     */
    void onLocationChange(GpsLocation location);

}
