package ado.com.testarchcomponents.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import ado.com.testarchcomponents.model.GpsLocation
import ado.com.testarchcomponents.viewmodel.LocationViewModel
import ado.com.testarchcomponents.R
import ado.com.testarchcomponents.databinding.ActivityMainBinding

class StartActivity : AppCompatActivity(), Observer<GpsLocation> {
    private var mViewModel: LocationViewModel? = null
    private var mBinding: ActivityMainBinding? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupList()
        mViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        //instead of pushing data to the UI, let the UI observe changes to it.
        mViewModel?.locationLiveData?.observe(this, this)
    }

    private fun setupList() {
        mBinding?.included?.coordinatesList?.adapter = CoordinatesAdapter()
        subscribeToListChanges()
    }

    private fun subscribeToListChanges() {
        //TODO subscribe for changes to the list. use a view-model which will observe the db. (try use room)
    }

    override fun onChanged(location: GpsLocation?) {
        if (location != null) {
            mBinding?.included?.location = location
        }
    }
}
