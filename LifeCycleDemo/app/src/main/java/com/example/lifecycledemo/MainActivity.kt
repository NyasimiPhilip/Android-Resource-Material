package com.example.lifecycledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope  // Importing lifecycleScope for coroutine support
import com.example.lifecycledemo.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up data binding for the layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Check if the activity is being created for the first time (not from a configuration change)
        if (savedInstanceState == null) {
            // If it's the first creation, replace the container with an instance of MainFragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        // Coroutine launched in the lifecycleScope of the activity
        lifecycleScope.launch {
            // Delay for 5 seconds
            delay(5000)

            // Set the visibility of the progress bar to VISIBLE after the delay
            binding.progressBar.visibility = View.VISIBLE

            // Delay for an additional 10 seconds
            delay(10000)

            // Set the visibility of the progress bar to GONE after the second delay
            binding.progressBar.visibility = View.GONE
        }

        // Example of launching a coroutine with a specified dispatcher (commented out)
//        lifecycleScope.launch(Dispatchers.IO) {
//            Log.i("MyTag","Thread name is : ${Thread.currentThread().name}")
//        }
    }
}
