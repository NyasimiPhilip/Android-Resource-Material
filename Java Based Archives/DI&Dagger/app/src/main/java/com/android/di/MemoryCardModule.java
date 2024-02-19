package com.android.di;

import android.util.Log;

import dagger.Module;
import dagger.Provides;

@Module
public class MemoryCardModule {
    private int memorySize;
    private static final String TAG = "SmartPhone";

    public MemoryCardModule(int memorySize) {
        this.memorySize = memorySize;
    }

    @Provides
    MemoryCard provideMemoryCard(){
        Log.d(TAG, "memory card size is"+ memorySize);
        return new MemoryCard();
    }
}
