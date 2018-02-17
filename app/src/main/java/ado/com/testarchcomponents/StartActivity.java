package ado.com.testarchcomponents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ado on 29/01/2018.
 */

public class StartActivity extends AppCompatActivity {
    private LocationListener myLocationListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLocationListener = new LocationListener(this);
        getLifecycle().addObserver(myLocationListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(myLocationListener);
    }
}
