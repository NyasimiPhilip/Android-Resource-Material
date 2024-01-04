package com.example.viewmodeldemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.viewmodeldemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Initialize the ViewModel
        viewModel = MainActivityViewModel()

        // Observe the LiveData and update the UI when it changes
        viewModel.count.observe(this, Observer { count ->
            binding.countText.text = count.toString()
        })

        // Set initial count text
        binding.countText.text = viewModel.getCurrentCount().toString()

        // Set a click listener for the button to increment the count in the ViewModel
        binding.button.setOnClickListener {
            // Update the count through the ViewModel
            viewModel.getUpdatedCount()
        }
    }
}
