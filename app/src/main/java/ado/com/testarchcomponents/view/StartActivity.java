package ado.com.testarchcomponents.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ado.com.testarchcomponents.model.GpsLocation;
import ado.com.testarchcomponents.viewmodel.LocationViewModel;
import ado.com.testarchcomponents.R;
import ado.com.testarchcomponents.databinding.ActivityMainBinding;

public class StartActivity extends AppCompatActivity implements Observer<GpsLocation> {
    private LocationViewModel mViewModel;
    private ActivityMainBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(LocationViewModel.class);
        //instead of pushing data to the UI, let the UI observe changes to it.
        mViewModel.getLocationLiveData().observe(this, this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.getLocationLiveData().removeObserver(this);
    }

    @Override
    public void onChanged(@Nullable GpsLocation location) {
        if (location != null) {
            mBinding.included.setLocation(location);
        }
    }
}
