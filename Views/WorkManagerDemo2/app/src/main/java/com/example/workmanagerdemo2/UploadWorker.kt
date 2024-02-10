package com.example.workmanagerdemo2

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.PeriodicWorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

// Extend Worker class and pass context and worker parameters to the constructor
class UploadWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object{
        const val KEY_WORKER="key_worker"
    }

    // This method is called when the WorkManager decides to run the task
    override fun doWork(): Result {
        return try {
            // Retrieve data passed to the worker
            val inputData = inputData
            val countValue = inputData.getInt(MainActivity.KEY_COUNT_VALUE, 0)
            Log.i("MYTAG", "Count value received: $countValue")

            // Simulate a time-consuming task, for example, uploading data
            for (i in 0..countValue) {
                Log.i("MYTAG", "Uploading $i")
            }
            val time = SimpleDateFormat("dd/M/yyy hh:mm:ss")
            val currentDate : String = time.format(Date())
            val outPutData = Data.Builder()
                .putString(KEY_WORKER, currentDate)
                .build()

            // Indicate that the work is completed successfully
            Result.success(outPutData)
        } catch (e: Exception) {
            // Handle exceptions and indicate that the work has failed
            Result.failure()
        }
    }
}
