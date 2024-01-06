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

class SubscriberViewModel(private val repository: SubscriberRepository, private val context: Context): ViewModel() {

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val subscribers = repository.subscribers

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButton = MutableLiveData<String>()

    init{
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButton.value= "Clear All"
    }

    fun saveOrUpdate() {
        val name = inputName.value
        val email = inputEmail.value

        if (name.isNullOrBlank() || email.isNullOrBlank()) {
            // Show a toast message if either name or email is blank
            showToast("Please input name and email")
            return
        }

        try {
            // Assuming insert function may throw exceptions, handle them appropriately
            insert(Subscriber(0, name, email))

            // Reset the input values after a successful operation
            inputName.value = ""
            inputEmail.value = ""

            // Optionally, show a success toast
            showToast("Subscriber added successfully")
        } catch (e: Exception) {
            // Handle the exception, log it, or show an error message to the user
            e.printStackTrace()

            // Show an error toast
            showToast("Error adding subscriber")
        }
    }

    fun clearAllOrDelete(){
        clearAll()
    }
    private fun insert(subscriber: Subscriber)=
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(subscriber)
    }
    fun update(subscriber: Subscriber)=
        viewModelScope.launch(Dispatchers.IO){
            repository.update(subscriber)
        }
    fun delete(subscriber: Subscriber)=
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(subscriber)
        }
    private fun clearAll()=
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }
}