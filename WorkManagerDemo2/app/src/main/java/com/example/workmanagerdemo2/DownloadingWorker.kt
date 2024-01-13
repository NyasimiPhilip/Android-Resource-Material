package com.example.workmanagerdemo2

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.Date

// Extend Worker class and pass context and worker parameters to the constructor
class DownloadingWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    // This method is called when the WorkManager decides to run the task
    override fun doWork(): Result {
        return try {
            // Retrieve data passed to the worker

            // Simulate a time-consuming task, for example, uploading data
            for (i in 0..300) {
                Log.i("MYTAG", "Downloading $i")
            }
            val time = SimpleDateFormat("dd/M/yyy hh:mm:ss")
            val currentDate: String = time.format(Date())
            Log.i("MyTag", "Completed${currentDate}")

            // Indicate that the work is completed successfully
            Result.success()
        } catch (e: Exception) {
            // Handle exceptions and indicate that the work has failed
            Result.failure()
        }
    }
}
