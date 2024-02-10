// Import necessary packages for the application
package com.example.viewmodeldemo2

// Import necessary classes from AndroidX and Android SDK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
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

         // Update TextView with initial total
         updateTotal()

        // Set up onClickListener for the button
        binding.insertButton.setOnClickListener {
            // Simulating a button click that updates the total
            viewModel.total += binding.inputEditText.text.toString().toIntOrNull() ?: 0
            updateTotal()
        }

       
    }

    private fun updateTotal() {
        // Update the TextView with the current total
        binding.resultTextView.text = "Total: ${viewModel.total}"
    }
}
