package com.android.di.views;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.di.App;
import com.android.di.Battery;
import com.android.di.R;
import com.android.di.SmartPhone;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    SmartPhone smartPhone;
    @Inject
    Battery battery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*smartPhone = smartPhoneComponent.getSmartPhone();*/
        App.getApp().getSmartPhoneComponent().inject(this);
        smartPhone.makeACall();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);


            }
        });

    }
}
