package ado.com.testarchcomponents.model

class GpsLocation(private val mLatitude: Double, private val mLongitude: Double) {

    val latitude: String
        get() = mLatitude.toString()

    val longitude: String
        get() = mLongitude.toString()

}
