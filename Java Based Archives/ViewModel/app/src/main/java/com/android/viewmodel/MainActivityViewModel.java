package com.android.viewmodel;

// Import necessary classes
import androidx.lifecycle.ViewModel;

// Define MainActivityViewModel class, extending ViewModel
public class MainActivityViewModel extends ViewModel {

    // Member variable to hold the count of clicks
    private int clickCount = 0;

    // Method to get the initial count value
    public int getInitialCount() {
        // Return the initial value of clickCount
        return clickCount;
    }

    // Method to get the current count value and increment the count
    public int getCurrentCount() {
        // Increment the clickCount by 1
        clickCount += 1;
        // Return the updated value of clickCount
        return clickCount;
    }
}
