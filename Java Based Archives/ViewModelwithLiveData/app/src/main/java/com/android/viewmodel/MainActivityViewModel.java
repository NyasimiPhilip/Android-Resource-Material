package com.android.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private int clickCount = 0;
    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>();

    // Method to retrieve initial count
    public MutableLiveData<Integer> getInitialCount() {
        // Setting initial count value to LiveData
        countLiveData.setValue(clickCount);
        return countLiveData;
    }

    // Method to increment count
    public void getCurrentCount() {
        // Incrementing count and updating LiveData
        clickCount += 1;
        countLiveData.setValue(clickCount);
    }
}
