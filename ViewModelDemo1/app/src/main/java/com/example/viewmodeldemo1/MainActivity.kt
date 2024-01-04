package com.example.viewmodeldemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.viewmodeldemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Initialize the ViewModel
        viewModel = MainActivityViewModel()

        // Set initial count text
        binding.countText.text = viewModel.getCurrentCount().toString()

        // Set a click listener for the button to increment the count in the ViewModel
        binding.button.setOnClickListener {
            // Update the count and set the new count text
            binding.countText.text = viewModel.getUpdatedCount().toString()
        }
    }
}
