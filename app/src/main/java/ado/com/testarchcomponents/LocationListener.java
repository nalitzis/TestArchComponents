package ado.com.testarchcomponents;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Ado on 29/01/2018.
 */

public class LocationListener implements LifecycleObserver {
    private static final String TAG = "LocationListener";

    private final Context mApplicationContext;
    private final MyLocationListener mMyLocationListener;
    private final LocationViewModel mModel;

    LocationListener(final Context applicationContext, LocationViewModel viewModel) {
        mApplicationContext = applicationContext;
        mMyLocationListener = new MyLocationListener();
        mModel = viewModel;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void connectListener() {
        Log.d(TAG, "connecting listener");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void disconnectListener() {
        Log.d(TAG, "disconnecting listener");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        LocationManager locationManager = (LocationManager) mApplicationContext.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            //TODO check permissions
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mMyLocationListener);
            } catch (SecurityException e) {
                Log.e(TAG, "no permission given for GPS updates!");
            }
        }
    }

    private class MyLocationListener implements android.location.LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "onLocationChanged() " + location);
            mModel.locationLiveData.setValue(
                    new ado.com.testarchcomponents.Location(location.getLatitude(), location.getLongitude()));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {
            Log.d(TAG, "onProviderEnabled() " + s);
        }

        @Override
        public void onProviderDisabled(String s) {
            Log.d(TAG, "onProviderDisabled() " + s);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        LocationManager locationManager = (LocationManager) mApplicationContext.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            locationManager.removeUpdates(mMyLocationListener);
        }
    }

}
