package ado.com.testarchcomponents;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Ado on 29/01/2018.
 */

public class StartActivity extends AppCompatActivity implements Observer<Location> {
    private LocationListener myLocationListener;
    private LocationViewModel mModel;

    private TextView mLatitudeValue;
    private TextView mLongitudeValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLatitudeValue = findViewById(R.id.latitude_value);
        mLongitudeValue = findViewById(R.id.longitude_value);

        mModel = ViewModelProviders.of(this).get(LocationViewModel.class);
        mModel.locationLiveData.observe(this, this);

        myLocationListener = new LocationListener(this.getApplicationContext(), mModel);
        getLifecycle().addObserver(myLocationListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(myLocationListener);
        mModel.locationLiveData.removeObserver(this);
    }

    @Override
    public void onChanged(@Nullable Location location) {
        if (location != null) {
            mLatitudeValue.setText(String.valueOf(location.getLatitude()));
            mLongitudeValue.setText(String.valueOf(location.getLongitude()));
        }
    }
}
