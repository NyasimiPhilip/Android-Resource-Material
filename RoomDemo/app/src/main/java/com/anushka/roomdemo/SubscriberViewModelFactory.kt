package com.anushka.roomdemo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushka.roomdemo.db.SubscriberRepository

// Factory class responsible for creating instances of SubscriberViewModel
class SubscriberViewModelFactory(
    private val repository: SubscriberRepository, // Repository providing access to data
    private val context: Context // Context required for ViewModel initialization
) : ViewModelProvider.Factory {

    // Called by the ViewModelProvider to create a new instance of ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the given modelClass is assignable to SubscriberViewModel
        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
            // If it is, create and return an instance of SubscriberViewModel with the provided repository and context
            @Suppress("UNCHECKED_CAST")
            return SubscriberViewModel(repository, context) as T
        }
        // If the modelClass is not SubscriberViewModel, throw an IllegalArgumentException
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
