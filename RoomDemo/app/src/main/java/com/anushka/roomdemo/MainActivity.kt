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

class MainActivity : AppCompatActivity() {
    // Data binding instance for the main activity layout
    private lateinit var binding: ActivityMainBinding

    // ViewModel instance for handling UI-related data
    private lateinit var subscriberViewModel: SubscriberViewModel

    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view using data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Initialize the DAO (Data Access Object) from the Room database
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO

        // Create a repository instance using the DAO
        val repository = SubscriberRepository(dao)

        // Pass the context to the SubscriberViewModelFactory
        val factory = SubscriberViewModelFactory(repository, applicationContext)

        // Use the factory to create the ViewModel
        subscriberViewModel = ViewModelProvider(this, factory)[SubscriberViewModel::class.java]

        // Set the ViewModel and lifecycle owner for data binding
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this

        // Initialize the RecyclerView
        initRecyclerView()

        subscriberViewModel.message.observe(this , Observer{
            it.getContentIfNotHandled()?.let{
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    // Initialize the RecyclerView with a LinearLayoutManager
    private fun initRecyclerView() {
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)

        // Display the list of subscribers in the RecyclerView
        displaySubscribersList()
    }

    // Observe the LiveData for the list of subscribers and update the RecyclerView when it changes
    private fun displaySubscribersList() {
        subscriberViewModel.subscribers.observe(this, Observer {
            // Log the list of subscribers for debugging purposes
            Log.i("MyTag", it.toString())

            // Set the adapter for the RecyclerView using a custom adapter (MyRecyclerViewAdapter)
            binding.subscriberRecyclerView.adapter = MyRecyclerViewAdapter(it) { selectedItem: Subscriber ->
                // Handle the click event for a list item
                listItemClicked(selectedItem)
            }
        })
    }

    // Display a Toast message when a list item is clicked
    private fun listItemClicked(subscriber: Subscriber) {
      //Toast.makeText(this, "selected name is ${subscriber.name}", Toast.LENGTH_LONG).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}
