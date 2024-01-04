package com.example.viewmodeldemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeldemo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Initialize ViewModelFactory with startingTotal value
        viewModelFactory = MainActivityViewModelFactory(0)
        // Get an instance of ViewModel using ViewModelProvider with ViewModelFactory
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]

        // Observe changes to the total value
        viewModel.totalData.observe(this, Observer { it ->
            // Update TextView with the current total
            binding.resultTextView.text = "Total: $it"
        })
        // Set up onClickListener for the button
        binding.insertButton.setOnClickListener {
            val inputText = binding.inputEditText.text.toString().trim()

            if (inputText.isNotEmpty()) {
                viewModel.set2Total(inputText.toInt())
            } else {
                showToast("Please input a valid number")
            }
        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
