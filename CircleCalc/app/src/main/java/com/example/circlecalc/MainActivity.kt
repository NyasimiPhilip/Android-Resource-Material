package com.example.circlecalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.circlecalc.databinding.ActivityMainBinding

// MainActivity responsible for initializing and setting up the UI and ViewModel
class MainActivity : AppCompatActivity() {

    // Data binding object for the activity layout
    private lateinit var binding: ActivityMainBinding

    // ViewModel instance for handling calculations
    private lateinit var viewModel: CalcViewModel

    // Factory for creating instances of CalcViewModel with dependencies
    lateinit var factory: CalcViewModelFactory

    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the data binding object using the generated binding class
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Create an instance of the CalcViewModelFactory with a MyCalc implementation
        factory = CalcViewModelFactory(MyCalc())

        // Create an instance of CalcViewModel using the ViewModelProvider with the factory
        viewModel = ViewModelProvider(this, factory)
            .get(CalcViewModel::class.java)

        // Set the ViewModel instance for data binding in the layout
        binding.myViewModel = viewModel

        // Set the lifecycle owner for observing LiveData updates
        binding.lifecycleOwner = this
    }
}
