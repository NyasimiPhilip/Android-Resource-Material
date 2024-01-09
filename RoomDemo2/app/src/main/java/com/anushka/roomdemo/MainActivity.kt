// Import necessary libraries and packages
package com.anushka.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anushka.roomdemo.databinding.ActivityMainBinding
import com.anushka.roomdemo.db.Subscriber
import com.anushka.roomdemo.db.SubscriberDatabase
import com.anushka.roomdemo.db.SubscriberRepository

// MainActivity class extending AppCompatActivity
class MainActivity : AppCompatActivity() {
    // Late-initialized variables for view binding, view model, and recycler view adapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapter: MyRecyclerViewAdapter

    // onCreate method called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up data binding for the activity
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Initialize DAO, repository, and view model with ViewModelFactory
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory)[SubscriberViewModel::class.java]

        // Set the ViewModel and lifecycle owner for data binding
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this

        // Initialize the RecyclerView
        initRecyclerView()

        // Observe the message LiveData in ViewModel and display toast message when it changes
        subscriberViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    // Initialize the RecyclerView with layout manager and adapter
    private fun initRecyclerView() {
        // Set LinearLayoutManager for the RecyclerView
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)

        // Create an instance of the adapter with a lambda function for item click handling
        adapter = MyRecyclerViewAdapter { selectedItem: Subscriber -> listItemClicked(selectedItem) }

        // Set the adapter for the RecyclerView
        binding.subscriberRecyclerView.adapter = adapter

        // Display the list of subscribers in the RecyclerView
        displaySubscribersList()
    }

    // Observe the LiveData for the list of subscribers and update the RecyclerView
    private fun displaySubscribersList() {
        subscriberViewModel.subscribers.observe(this, Observer {
            // Log the list of subscribers and update the adapter
            Log.i("MYTAG", it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    // Handle item click in the RecyclerView, display a toast, and call ViewModel method
    private fun listItemClicked(subscriber: Subscriber) {
        Toast.makeText(this, "selected name is ${subscriber.name}", Toast.LENGTH_LONG).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}
