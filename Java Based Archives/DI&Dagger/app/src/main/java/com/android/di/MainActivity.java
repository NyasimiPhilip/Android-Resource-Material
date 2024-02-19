package com.android.di;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    SmartPhone smartPhone;
    @Inject
    Battery battery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SmartPhoneComponent smartPhoneComponent = DaggerSmartPhoneComponent.builder()
                                                             .memoryCardModule(new MemoryCardModule(100))
                                                             .build();
        /*smartPhone = smartPhoneComponent.getSmartPhone();*/
        smartPhoneComponent.inject(this);
        smartPhone.makeACall();
    }
}
