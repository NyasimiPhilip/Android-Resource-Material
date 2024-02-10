package com.example.livedatademo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.livedatademo.model.UserRepository
import kotlinx.coroutines.Dispatchers

class MainActivityViewModel : ViewModel()  {

    // Creating an instance of UserRepository
    private var usersRepository= UserRepository()

    // Using liveData builder to observe data changes and update UI automatically
    // Dispatching the coroutine to IO thread for background work
    var users = liveData(Dispatchers.IO) {
        // Fetching data using UserRepository
        val result = usersRepository.getUsers()

        // Emitting the result to update the LiveData
        emit(result)
    }

    // The commented-out block below is an alternative implementation using MutableLiveData
    // It shows how to fetch data in the background and update LiveData

//    private var usersRepository= UserRepository()
//    var users: MutableLiveData<List<User>?> = MutableLiveData()
//
//    // Function to fetch users in the background using coroutines
//    fun getUsers() {
//        viewModelScope.launch {
//            var result: List<User>? = null
//
//            // Switching to IO dispatcher for background work
//            withContext(Dispatchers.IO) {
//                // Fetching data from UserRepository
//                result = usersRepository.getUsers()
//            }
//
//            // Updating the LiveData with the fetched result
//            users.value = result
//        }
//    }
}
