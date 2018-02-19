package ado.com.testarchcomponents;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

class LocationViewModel extends ViewModel {
    final MutableLiveData<Location> locationLiveData = new MutableLiveData<>();
}
