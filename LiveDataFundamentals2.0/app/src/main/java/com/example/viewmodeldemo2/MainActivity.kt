package com.example.viewmodeldemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        binding.myViewModel = viewModel

        // Observe changes to totalData and update the UI
        viewModel.totalData.observe(this, Observer { it ->
            binding.resultTextView.text = "Total: $it"
        })
    }
}
