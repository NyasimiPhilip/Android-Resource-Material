package com.example.dependencyInjection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workmanagerdemo2.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val smartPhone = SmartPhone(
            Battery(),
            SIMCard(ServiceProvider()),
            MemoryCard()
        )
            .makeACallWithRecording()


    }
}
