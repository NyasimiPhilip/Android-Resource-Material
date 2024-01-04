// MainActivityViewModel.kt
package com.example.viewmodelscopedemo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodelscopedemo.model.User
import com.example.viewmodelscopedemo.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {
    // Initialize UserRepository
    private var userRepository = UserRepository()

    // LiveData to hold the list of users
    var users: MutableLiveData<List<User>?> = MutableLiveData()

    // Function to fetch user data asynchronously
    fun getUserData() {
        // Use viewModelScope to handle coroutine lifecycle
        viewModelScope.launch {
            try {
                // Log: Indicate the start of data fetching
                Log.i("MyTag", "Fetching user data...")

                // Variable to hold the result of the asynchronous operation
                var result: List<User>? = null

                // Use withContext to switch to the IO dispatcher for the asynchronous operation
                withContext(Dispatchers.IO) {
                    // Perform the actual data fetching
                    result = userRepository.getUsers()
                }

                // Switch back to the main thread for UI updates
                withContext(Dispatchers.Main) {
                    // Log: Indicate successful data fetching
                    Log.i("MyTag", "User data fetched successfully.")

                    // Update the LiveData with the fetched data
                    users.value = result
                }
            } catch (e: Exception) {
                // Log: Indicate an error if fetching data fails
                Log.e("MyTag", "Error fetching user data: ${e.message}")
            }
        }
    }
}
