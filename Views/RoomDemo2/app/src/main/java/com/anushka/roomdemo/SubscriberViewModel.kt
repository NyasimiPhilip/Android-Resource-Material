package com.anushka.roomdemo

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anushka.roomdemo.db.Subscriber
import com.anushka.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel class responsible for handling UI-related data and logic for Subscriber operations.
 * @param repository The data repository for managing Subscriber entities.
 */
class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel(){

    // LiveData to observe the list of subscribers from the repository
    val subscribers = repository.subscribers

    // Flags and data for tracking update or delete operations
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete : Subscriber

    // MutableLiveData for capturing user input (name and email)
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()

    // MutableLiveData for dynamically updating button texts
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    // MutableLiveData for displaying status messages using the Event wrapper
    private val statusMessage = MutableLiveData<Event<String>>()

    // LiveData for exposing the status messages to the UI
    val message : LiveData<Event<String>>
        get() = statusMessage

    // Initialization block to set default button texts
    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    /**
     * Function to handle the save or update operation based on user input.
     * Displays appropriate status messages using LiveData.
     */
    fun saveOrUpdate(){
        if (inputName.value == null) {
            statusMessage.value = Event("Please enter subscriber's name")
        } else if (inputEmail.value == null) {
            statusMessage.value = Event("Please enter subscriber's email")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            statusMessage.value = Event("Please enter a correct email address")
        } else {
            if(isUpdateOrDelete){
                // Update existing subscriber
                subscriberToUpdateOrDelete.name = inputName.value!!
                subscriberToUpdateOrDelete.email = inputEmail.value!!
                update(subscriberToUpdateOrDelete)
            } else {
                // Insert new subscriber
                val name = inputName.value!!
                val email = inputEmail.value!!
                insert(Subscriber(0, name, email))
                // Clear input fields after insertion
                inputName.value = ""
                inputEmail.value = ""
            }
        }
    }

    /**
     * Function to handle the clear all or delete operation based on user input.
     * Displays appropriate status messages using LiveData.
     */
    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            // Delete existing subscriber
            delete(subscriberToUpdateOrDelete)
        } else {
            // Clear all subscribers
            clearAll()
        }
    }

    /**
     * Function to insert a new subscriber into the database.
     * Uses coroutines for background processing and updates UI using LiveData.
     */
    private fun insert(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val newRowId = repository.insert(subscriber)
        withContext(Dispatchers.Main){
            if(newRowId > -1) {
                statusMessage.value = Event("Subscriber Inserted Successfully! $newRowId")
            } else {
                statusMessage.value = Event("Error Occurred!")
            }
        }
    }

    /**
     * Function to update an existing subscriber in the database.
     * Uses coroutines for background processing and updates UI using LiveData.
     */
    private fun update(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val numberOfRows = repository.update(subscriber)
        withContext(Dispatchers.Main){
            if(numberOfRows > 0) {
                // Clear input fields and update button texts after successful update
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("$numberOfRows Rows Updated Successfully!")
            } else {
                statusMessage.value = Event("Error Occurred!")
            }
        }
    }

    /**
     * Function to delete an existing subscriber from the database.
     * Uses coroutines for background processing and updates UI using LiveData.
     */
    private fun delete(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val numberOfRowsDeleted = repository.delete(subscriber)
        withContext(Dispatchers.Main){
            if(numberOfRowsDeleted > 0) {
                // Clear input fields and update button texts after successful delete
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("$numberOfRowsDeleted Rows Deleted Successfully!")
            } else {
                statusMessage.value = Event("Error Occurred!")
            }
        }
    }

    /**
     * Function to clear all subscribers from the database.
     * Uses coroutines for background processing and updates UI using LiveData.
     */
    private fun clearAll() = viewModelScope.launch(Dispatchers.IO) {
        val numberOfRowsDeleted = repository.deleteAll()
        withContext(Dispatchers.Main){
            if(numberOfRowsDeleted > 0) {
                statusMessage.value = Event("$numberOfRowsDeleted Rows Deleted Successfully!")
            } else {
                statusMessage.value = Event("Error Occurred!")
            }
        }
    }

    /**
     * Function to initialize update or delete operation with subscriber details.
     * Populates input fields, sets flags, and updates button texts for update or delete operation.
     */
    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }
}
