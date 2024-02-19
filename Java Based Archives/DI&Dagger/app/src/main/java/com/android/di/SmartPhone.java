package com.android.di;

import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
public class SmartPhone {

    public Battery battery;
    private MemoryCard memoryCard;
    private SIMCard simCard;
    private static final String TAG = "SmartPhone";
    private String time;
    private DateFormat df;


    @Inject
    public SmartPhone(Battery battery, MemoryCard memoryCard, SIMCard simCard) {
        this.battery = battery;
        this.memoryCard = memoryCard;
        this.simCard = simCard;
        battery.showType();
        df = DateFormat.getTimeInstance();
        time = df.format(Calendar.getInstance().getTime());

        // Log the time of initialization
        Log.d(TAG, "SmartPhone initialized at: " + time);

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
