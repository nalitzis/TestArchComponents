package ado.com.testarchcomponents;

public class Location {

    private final double mLatitude;
    private final double mLongitude;

    Location(double latitude, double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    double getLatitude() {
        return mLatitude;
    }

    double getLongitude() {
        return mLongitude;
    }

}
