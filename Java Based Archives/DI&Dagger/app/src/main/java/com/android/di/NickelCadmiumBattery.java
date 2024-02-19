package com.android.di;

import android.util.Log;

import javax.inject.Inject;

public class NickelCadmiumBattery implements Battery {
    private static final String TAG = "SmartPhone"; // Define TAG as a static final field
    @Inject
    public NickelCadmiumBattery() {
        // Default constructor
    }

    @Override
    public void showType() {
        Log.d(TAG, "This is a Nickel Cadmium Battery.");
    }
}
