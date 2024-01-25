// Import necessary Android and Kotlin libraries
package com.example.servicedemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.servicedemo.databinding.ActivityMainBinding
import kotlin.math.roundToInt

// Define the main activity class that extends AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Declare variables
    private lateinit var binding: ActivityMainBinding
    private var isStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    // Override the onCreate method to set up the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listeners for the start and reset buttons
        binding.btnStart.setOnClickListener {
            startOrStop()
        }
        binding.btnReset.setOnClickListener {
            reset()
            binding.btnStart.setBackgroundResource(R.drawable.rounded_button_start)

        }

        // Create an Intent to start the Stopwatch service
        serviceIntent = Intent(applicationContext, StopWatchService::class.java)

        // Register a BroadcastReceiver to receive updates from the Stopwatch service
        registerReceiver(updateTime, IntentFilter(StopWatchService.UPDATED_TIME))
    }

    // Function to start or stop the stopwatch based on its current state
    private fun startOrStop() {
        if (isStarted){
            stop()
            // Change the button color to the "Stopped" state
            binding.btnStart.setBackgroundResource(R.drawable.rounded_button_start)

        }else {
            start()
            // Change the button color to the "Started" state
            binding.btnStart.setBackgroundResource(R.drawable.rounded_button_stop)
           /** binding.btnStart.setBackgroundColor(
                getColor(R.color.startButtonColorStarted)
            )*/
        }
    }

    // Function to start the Stopwatch service
    private fun start() {
        // Pass the current time to the service
        serviceIntent.putExtra(StopWatchService.CURRENT_TIME, time)

        // Start the service
        startService(serviceIntent)

        // Update UI and state
        binding.btnStart.text = " Stop "
        isStarted = true
    }

    // Function to stop the Stopwatch service
    private fun stop() {
        // Stop the service
        stopService(serviceIntent)

        // Update UI and state
        binding.btnStart.text = "Start"
        isStarted = false
    }

    // Function to reset the stopwatch
    private fun reset() {
        // Stop the stopwatch, reset the time, and update UI
        stop()
        time = 0.0
        binding.tvTime.text = getFormattedTime(time)
    }

    // BroadcastReceiver to receive updates from the Stopwatch service
    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Update the time from the received broadcast
            time = intent.getDoubleExtra(StopWatchService.CURRENT_TIME, 0.0)

            // Update the UI with the formatted time
            binding.tvTime.text = getFormattedTime(time)
        }
    }

    // Function to format the time in hours, minutes, and seconds
    private fun getFormattedTime(time: Double): String {
        // Convert time to integer and calculate hours, minutes, and seconds
        val timeInt = time.roundToInt()
        val hours = timeInt % 86400 / 3600
        val minutes = timeInt % 86400 % 3600 / 60
        val seconds = timeInt % 86400 % 3600 % 60

        // Return the formatted time string
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}
