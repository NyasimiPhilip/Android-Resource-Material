package com.example.servicedemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.Timer
import java.util.TimerTask

class StopWatchService : Service() {

    // Initialization block, logs a message when the service is created
    init {
        Log.i(TAG, "Service has been created")
    }

    // onBind method required by Service class, returns null as this service doesn't support binding
    override fun onBind(intent: Intent?): IBinder? = null

    // Timer for scheduling tasks
    private val timer = Timer()

    // onStartCommand method called when the service is started
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Retrieve the time value from the intent
        val time = intent.getDoubleExtra(CURRENT_TIME, 0.0)

        // Schedule a task (StopWatchTimerTask) to run at fixed intervals
        timer.scheduleAtFixedRate(StopWatchTimerTask(time), 0, 1000)

        // Return START_NOT_STICKY to indicate that if the system kills the service, it should not be restarted
        return START_NOT_STICKY
    }

    // onDestroy method called when the service is being destroyed
    override fun onDestroy() {
        // Log a message indicating that the service is being destroyed
        Log.i(TAG, "Service is being destroyed")

        // Cancel the timer to stop scheduled tasks
        timer.cancel()

        // Call the superclass onDestroy method
        super.onDestroy()
    }

    // Companion object containing constants and a TAG for logging
    companion object {
        private val TAG = "MYTAG"
        const val CURRENT_TIME = "current time"
        const val UPDATED_TIME = "Updated_time"
    }

    // Inner class representing the task to be scheduled by the timer
    private inner class StopWatchTimerTask(private var time: Double) : TimerTask() {
        // run method executed when the timer task is triggered
        override fun run() {
            // Create an intent with the action UPDATED_TIME
            val intent = Intent(UPDATED_TIME)

            // Increment the time value
            time++

            // Put the updated time value into the intent
            intent.putExtra(UPDATED_TIME, time)

            // Send a broadcast with the intent
            sendBroadcast(intent)
        }
    }
}