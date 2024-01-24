package com.example.servicedemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService : Service() {

    // This block is executed when an instance of MyBackgroundService is created
    init {
        Log.i(TAG, "Service has been created")
    }

    // This method is called when the service is started
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // Log a message indicating that the service has started
        Log.i(TAG, "Service started")

        // Retrieve data from the Intent extras (if any)
        val name = intent?.getStringExtra(NAME)
        val marks = intent?.getIntExtra(MARKS, 0)

        // Log the retrieved data
        Log.i(TAG, "name is $name and marks is $marks")

        // Return START_STICKY to indicate that the service should be restarted if it gets terminated
        return START_STICKY
    }

    // This method is called when a client binds to the service (not used in this example)
    override fun onBind(intent: Intent?): IBinder? = null

    // This method is called when the service is being destroyed
    override fun onDestroy() {
        // Log a message indicating that the service is being destroyed
        Log.i(TAG, "Destroying ..........")

        // Call the superclass implementation of onDestroy
        super.onDestroy()
    }

    // Companion object to hold constant values shared by instances of the class
    companion object {
        const val TAG = "MYTAG"
        const val NAME = "NAME"
        const val MARKS = "TOTAL MARKS"
    }
}
