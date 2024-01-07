package com.anushka.roomdemo

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anushka.roomdemo.db.Subscriber
import com.anushka.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// ViewModel class for handling UI-related data and actions
class SubscriberViewModel(private val repository: SubscriberRepository, private val context: Context) : ViewModel() {

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val subscribers = repository.subscribers

    val saveOrUpdateButtonText = MutableLiveData<String>("Save")
    val clearAllOrDeleteButton = MutableLiveData<String>("Clear All")

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = statusMessage

    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Subscriber

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButton.value = "Clear All"
    }

    // Function to handle save or update operation
    fun saveOrUpdate() {
        if(isUpdateOrDelete){
            subscriberToUpdateOrDelete.name = inputName.value!!
            subscriberToUpdateOrDelete.email = inputEmail.value!!
            update(subscriberToUpdateOrDelete)

        }else{
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
            //showToast("Subscriber added/updated successfully")
        } catch (e: Exception) {
            // Handle the exception, log it, or show an error message to the user
            e.printStackTrace()

            // Show an error toast
            showToast("Error adding/updating subscriber")
        }
    }
    }

    // Function to handle clear all or delete operation
    fun clearAllOrDelete() {
        if(isUpdateOrDelete){
            delete(subscriberToUpdateOrDelete)
        }else{
        clearAll()
        }
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

            withContext(Dispatchers.Main){
               statusMessage.value = Event("Subscriber Inserted Successfully")
            }


        }

    // Coroutine function to update a subscriber in the background thread
    private fun update(subscriber: Subscriber) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(subscriber)
            withContext(Dispatchers.Main){
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButton.value = "Clear All"
                statusMessage.value = Event("Subscriber Update Successfully")
            }
        }

    // Coroutine function to delete a subscriber in the background thread
    fun delete(subscriber: Subscriber) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(subscriber)
            withContext(Dispatchers.Main){
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButton.value = "Clear All"
                statusMessage.value = Event("Subscriber Deleted Successfully!")
            }
        }

    // Coroutine function to clear all subscribers in the background thread
    private fun clearAll() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
            withContext(Dispatchers.Main){
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButton.value = "Clear All"
                statusMessage.value = Event("All subscribers Deleted Successfully!")
            }
        }
    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButton.value = "Delete"
    }


    // Function to display a toast message
    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
