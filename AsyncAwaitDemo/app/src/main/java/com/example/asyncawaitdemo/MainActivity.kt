package com.example.asyncawaitdemo

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var totalStockTextView: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        totalStockTextView = findViewById(R.id.totalStockTextView)
        progressBar = findViewById(R.id.progressBar)

        CoroutineScope(Dispatchers.Main).launch {
            // CoroutineScope(Dispatchers.Main) specifies that the coroutine runs on the main thread.

            Log.i("MyTag", "Calculation started....")

            // Start the progress bar
            progressBar.visibility = ProgressBar.VISIBLE

            // Use async to execute getStock1 and getStock2 concurrently
            val stock1 = async(Dispatchers.IO) {
                getStock1()
            }
            val stock2 = async(Dispatchers.IO) {
                getStock2()
            }

            // Wait for the results of both async calls using await and calculate the total
            val total = stock1.await() + stock2.await()

            // Show a toast message with the total value
            Toast.makeText(applicationContext, "Total is $total", Toast.LENGTH_LONG).show()

            // Update the TextView with the total stock value
            totalStockTextView.text = "Total Stock: $total"

            // Hide the progress bar when the calculation is complete
            progressBar.visibility = ProgressBar.INVISIBLE
        }
    }

    // Coroutine function to simulate fetching stock1 with a delay
    private suspend fun getStock1(): Int {
        delay(3000)
        Log.i("MyTag", " stock 1 returned ")
        return 55000
    }

    // Coroutine function to simulate fetching stock2 with a delay
    private suspend fun getStock2(): Int {
        delay(500)
        Log.i("MyTag", " stock 2 returned ")
        return 35000
    }
}
