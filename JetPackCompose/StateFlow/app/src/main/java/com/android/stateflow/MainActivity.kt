package com.android.stateflow

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.stateflow.databinding.ActivityMainBinding
import com.example.viewmodeldemo2.MainActivityViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModelFactory = MainActivityViewModelFactory(0)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainActivityViewModel::class.java)

       // viewModel.totalData.observe(this, Observer {
         //   binding.resultTextView.text = it.toString()
       // })
        lifecycleScope.launchWhenStarted {
            viewModel.flowTotal.collect{
                binding.resultTextView.text= it.toString()
            }

        }
        lifecycleScope.launchWhenStarted {
            viewModel.message.collect{
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()

        } }

        binding.insertButton.setOnClickListener {
            viewModel.setTotal(binding.inputEditText.text.toString().toInt())
        }
    }
}