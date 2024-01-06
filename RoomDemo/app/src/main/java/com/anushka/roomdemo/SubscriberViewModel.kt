package com.anushka.roomdemo

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anushka.roomdemo.db.Subscriber
import com.anushka.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ViewModel class for handling UI-related data and actions
class SubscriberViewModel(private val repository: SubscriberRepository, private val context: Context) : ViewModel() {

    // LiveData for input fields in the UI
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()

    // LiveData for observing the list of subscribers from the repository
    val subscribers = repository.subscribers

    // LiveData for dynamic button text in the UI
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButton = MutableLiveData<String>()

    // Initialize button text LiveData in the constructor
    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButton.value = "Clear All"
    }

    // Function to handle save or update operation
    fun saveOrUpdate() {
        val name = inputName.value
        val email = inputEmail.value

        if (name.isNullOrBlank() || email.isNullOrBlank()) {
            // Show a toast message if either name or email is blank
            showToast("Please input name and email")
            return
        }

        try {
            // Insert or update the subscriber in the repository
            insertOrUpdateSubscriber(Subscriber(0, name, email))

            // Reset the input values after a successful operation
            inputName.value = ""
            inputEmail.value = ""

            // Optionally, show a success toast
            showToast("Subscriber added/updated successfully")
        } catch (e: Exception) {
            // Handle the exception, log it, or show an error message to the user
            e.printStackTrace()

            // Show an error toast
            showToast("Error adding/updating subscriber")
        }
    }

    // Function to handle clear all or delete operation
    fun clearAllOrDelete() {
        clearAll()
    }

    // Coroutine function to insert a subscriber in the background thread
    private fun insertOrUpdateSubscriber(subscriber: Subscriber) =
        viewModelScope.launch(Dispatchers.IO) {
            // If subscriber has an ID (greater than 0), update it; otherwise, insert a new one
            if (subscriber.id > 0) {
                update(subscriber)
            } else {
                insert(subscriber)
            }
        }

    // Coroutine function to insert a subscriber in the background thread
    private fun insert(subscriber: Subscriber) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(subscriber)
        }

    // Coroutine function to update a subscriber in the background thread
    fun update(subscriber: Subscriber) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(subscriber)
        }

    // Coroutine function to delete a subscriber in the background thread
    fun delete(subscriber: Subscriber) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(subscriber)
        }

    // Coroutine function to clear all subscribers in the background thread
    private fun clearAll() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }

    // Function to display a toast message
    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
