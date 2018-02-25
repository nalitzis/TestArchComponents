package ado.com.testarchcomponents.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import ado.com.testarchcomponents.model.GpsLocation;

public class LocationViewModel extends AndroidViewModel {

    private final LiveData<GpsLocation> mLocationLiveData;

    public LocationViewModel(@NonNull Application application) {
        super(application);
        mLocationLiveData = new LocationLiveData(new LocationManager(application));
    }

    public LiveData<GpsLocation> getLocationLiveData() {
        return mLocationLiveData;
    }


    private class LocationLiveData extends LiveData<GpsLocation> {
        private final LocationManager mListener2;

        private GpsLocationListener mGpsLocationListener = this::setValue;

        private LocationLiveData(final LocationManager listener) {
            mListener2 = listener;
        }

        @Override
        protected void onActive() {
            mListener2.start(mGpsLocationListener);
        }

        @Override
        protected void onInactive() {
            mListener2.stop();
        }
    }
}
