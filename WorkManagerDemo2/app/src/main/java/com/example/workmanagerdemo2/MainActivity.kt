package com.example.workmanagerdemo2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_COUNT_VALUE = "key_count"
    }

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for this activity using the XML layout file (activity_main.xml)
        setContentView(R.layout.activity_main)

        // Initialize TextView and Button by finding them using their respective IDs
        textView = findViewById(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        // Set an OnClickListener on the Button
        button.setOnClickListener {
            // When the Button is clicked, invoke the setOneTimeWorkRequest function
           // setOneTimeWorkRequest()
            setPeriodicWorkRequest()
        }
    }

    // Function to set up and enqueue a one-time work request
    private fun setOneTimeWorkRequest() {

        // Get an instance of WorkManager
        val workManager: WorkManager = WorkManager.getInstance(applicationContext)

        // Create input data for the work request
        val data: Data = Data.Builder()
            .putInt(KEY_COUNT_VALUE, 125)
            .build()

        // Define constraints for the work request
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // Create a OneTimeWorkRequest for the UploadWorker class with specified constraints and input data
        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        // Create separate work requests for Filtering, Compression, and Downloading workers
        val filteringRequest: OneTimeWorkRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
            .build()

        val compressingRequest: OneTimeWorkRequest = OneTimeWorkRequest.Builder(CompressionWorker::class.java)
            .build()

        val downloadingWorker: OneTimeWorkRequest = OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
            .build()

        // Create a list to hold parallel work requests
        val parallelWorks: MutableList<OneTimeWorkRequest> = mutableListOf<OneTimeWorkRequest>()

        // Add Filtering and Downloading workers to the parallelWorks list
        parallelWorks.add(downloadingWorker)
        parallelWorks.add(filteringRequest)

        // Enqueue the work requests using WorkManager in the specified order
        workManager
            .beginWith(parallelWorks) // Run Filtering and Downloading workers in parallel
            .then(compressingRequest) // Run Compression worker after the parallel workers
            .then(uploadRequest) // Run Upload worker after the Compression worker
            .enqueue()

        // Observe the WorkInfo for the Upload worker
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this, Observer<WorkInfo> { workInfo ->
                textView.text = workInfo?.state?.name ?: "WorkInfo is null"
                if (workInfo.state.isFinished) {
                    // When the work is finished, display a Toast with the output message
                    val data = workInfo.outputData
                    val message: String? = data.getString(UploadWorker.KEY_WORKER)
                    Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                }
            })
    }
    private fun setPeriodicWorkRequest(){
        val periodicWorkRequest = PeriodicWorkRequest.Builder(DownloadingWorker::class.java, 15, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
    }
}
