package com.android.di;

import android.util.Log;

import javax.inject.Inject;

public class SmartPhone {

    public Battery battery;
    private MemoryCard memoryCard;
    private SIMCard simCard;
    private static final String TAG = "SmartPhone";

    @Inject
    public SmartPhone(Battery battery, MemoryCard memoryCard, SIMCard simCard) {
        this.battery = battery;
        this.memoryCard = memoryCard;
        this.simCard = simCard;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public void setMemoryCard(MemoryCard memoryCard) {
        this.memoryCard = memoryCard;
    }

    public void setSimCard(SIMCard simCard) {
        this.simCard = simCard;
    }

    public void makeACall(){
        Log.d(TAG, " making a call......... ");
    }
}
