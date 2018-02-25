package ado.com.testarchcomponents.viewmodel;

import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.location.*;
import android.os.Bundle;
import android.util.Log;

import ado.com.testarchcomponents.model.GpsLocation;

public class LocationManager implements LocationListener, LifecycleObserver {
    private static final String TAG = "LocationManager";
    private Context mApplicationContext;

    private GpsLocationListener gpsLocationListener;

    LocationManager(final Context applicationContext) {
        mApplicationContext = applicationContext;
    }

    void start(final GpsLocationListener listener) {
        gpsLocationListener = listener;
        android.location.LocationManager locationManager = (android.location.LocationManager) mApplicationContext.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            //TODO check permissions
            try {
                locationManager.requestLocationUpdates(android.location.LocationManager.GPS_PROVIDER, 0, 0, this);
            } catch (SecurityException e) {
                Log.e(TAG, "no permission given for GPS updates!");
            }
        }
    }

    void stop() {
        android.location.LocationManager locationManager = (android.location.LocationManager) mApplicationContext.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
        gpsLocationListener = null;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged() " + location);
        gpsLocationListener.onLocationChange(new GpsLocation(location.getLatitude(), location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
