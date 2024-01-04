// Define the package where this class belongs
package com.example.bindingdemo2

// Import necessary classes from the Android framework
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.bindingdemo2.databinding.ActivityMainBinding

// Define the MainActivity class that extends AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Declare variables for data binding and view model
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    // Override the onCreate method to initialize the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        // Call the superclass onCreate method
        super.onCreate(savedInstanceState)

        // Use DataBindingUtil to set the content view for the activity
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Initialize the MainViewModel
        viewModel = MainViewModel()

        // Set the view model in the data binding
        binding.viewModel = viewModel

        // Set the lifecycle owner for observing LiveData in the data binding
        binding.lifecycleOwner = this

        // Set a click listener for the controlButton in the layout
        binding.controlButton.setOnClickListener {
            // Call the onButtonClicked method in the view model when the button is clicked
            viewModel.onButtonClicked()
        }
    }
}
