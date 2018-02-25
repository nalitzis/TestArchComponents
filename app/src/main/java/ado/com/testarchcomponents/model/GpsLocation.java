package ado.com.testarchcomponents.model;

public class GpsLocation {

    private final double mLatitude;
    private final double mLongitude;

    public GpsLocation(double latitude, double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public String getLatitude() {
        return String.valueOf(mLatitude);
    }

    public String getLongitude() {
        return String.valueOf(mLongitude);
    }

}
