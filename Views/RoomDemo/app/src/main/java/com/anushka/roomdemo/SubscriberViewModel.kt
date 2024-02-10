package com.anushka.roomdemo

import android.content.Context
import android.util.Patterns
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
        if (isUpdateOrDelete) {
            subscriberToUpdateOrDelete.name = inputName.value!!
            subscriberToUpdateOrDelete.email = inputEmail.value!!
            update(subscriberToUpdateOrDelete)

        } else {
            val name = inputName.value
            val email = inputEmail.value

            if (name.isNullOrBlank() || email.isNullOrBlank()) {
                showToast("Please input name and email")
                return
            }

            if (!isValidEmail(email)) {
                showToast("Please enter a valid email address")
                return
            }

            try {
                insertOrUpdateSubscriber(Subscriber(0, name, email))

                inputName.value = ""
                inputEmail.value = ""

                // Optionally, show a success toast
                // showToast("Subscriber added/updated successfully")
            } catch (e: Exception) {
                e.printStackTrace()

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
             val newRowId = repository.insert(subscriber)
            withContext(Dispatchers.Main){
                if (newRowId > -1) {
                    statusMessage.value = Event("Subscriber Inserted Successfully${newRowId}")
                }else{

                    statusMessage.value = Event("Error Occurred")
                }
            }



        }

    // Coroutine function to update a subscriber in the background thread
    private fun update(subscriber: Subscriber) =
        viewModelScope.launch(Dispatchers.IO) {
            val numberOfRows = repository.update(subscriber)
            withContext(Dispatchers.Main){
                if (numberOfRows > 0){
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButton.value = "Clear All"
                statusMessage.value = Event("${numberOfRows}Subscriber Update Successfully")
            }else{
                statusMessage.value = Event("Error Occurred")
                }
            }
        }

    // Coroutine function to delete a subscriber in the background thread
    private fun delete(subscriber: Subscriber) =
        viewModelScope.launch(Dispatchers.IO) {
            val numberOfRowsDeleted = repository.delete(subscriber)
            withContext(Dispatchers.Main) {
                if (numberOfRowsDeleted > 0) {
                    inputName.value = ""
                    inputEmail.value = ""
                    isUpdateOrDelete = false
                    saveOrUpdateButtonText.value = "Save"
                    clearAllOrDeleteButton.value = "Clear All"
                    statusMessage.value = Event("$numberOfRowsDeleted Subscriber Deleted Successfully!")
                } else {
                    statusMessage.value = Event("Error Occurred")
                }
            }
        }

    // Coroutine function to clear all subscribers in the background thread
    private fun clearAll() =
        viewModelScope.launch(Dispatchers.IO) {
            val numberOfRowsDeleted = repository.deleteAll()
            withContext(Dispatchers.Main){
                if (numberOfRowsDeleted > 0){
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButton.value = "Clear All"
                statusMessage.value = Event(" $numberOfRowsDeleted All subscribers Deleted Successfully!")
            }else{
                statusMessage.value = Event("Error Occurred")
                }
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
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        /** val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())*/
    }
}
