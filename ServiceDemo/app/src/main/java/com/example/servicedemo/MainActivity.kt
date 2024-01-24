package com.example.servicedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.servicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create an intent to start the background service
        val serviceIntent = Intent(this, MyBackgroundService::class.java)

        // Add extra data to the intent (optional)
        serviceIntent.putExtra(MyBackgroundService.NAME, "ALEX")
        serviceIntent.putExtra(MyBackgroundService.MARKS, 70)

        // Set up a click listener for the "Start Service" button
        binding.btnStart.setOnClickListener {
            // Log a message indicating the service is starting
            Log.i(MyBackgroundService.TAG, "Starting Service")

            // Start the background service using the intent
            startService(serviceIntent)
        }

        // Set up a click listener for the "Stop Service" button
        binding.btnStop.setOnClickListener {
            // Log a message indicating the service is stopping
            Log.i(MyBackgroundService.TAG, "Stopping Service")

            // Stop the background service using the intent
            stopService(serviceIntent)
        }
    }
}
