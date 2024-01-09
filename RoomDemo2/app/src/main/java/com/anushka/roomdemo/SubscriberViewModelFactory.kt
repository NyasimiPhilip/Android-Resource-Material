package com.anushka.roomdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushka.roomdemo.db.SubscriberRepository
import java.lang.IllegalArgumentException

/**
 * Factory class for creating instances of [SubscriberViewModel].
 * Used by [ViewModelProvider] to instantiate the view model with dependencies.
 * @param repository The data repository for managing Subscriber entities.
 */
class SubscriberViewModelFactory(private val repository: SubscriberRepository) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the specified [ViewModel] class with the provided repository.
     * @param modelClass The class of the [ViewModel] to be created.
     * @return An instance of [SubscriberViewModel] with the provided [SubscriberRepository].
     * @throws IllegalArgumentException if the [modelClass] is not assignable from [SubscriberViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the requested ViewModel class is assignable from SubscriberViewModel
        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
            // If true, create and return an instance of SubscriberViewModel with the provided repository
            return SubscriberViewModel(repository) as T
        }
        // If the requested ViewModel class is not assignable from SubscriberViewModel, throw an exception
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
