package com.example.viewmodeldemo1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    // Use MutableLiveData to allow changes to be observed
    private val _count = MutableLiveData<Int>()

    // Expose LiveData to observe changes
    val count: LiveData<Int>
        get() = _count

    init {
        // Initialize LiveData with a default value
        _count.value = 0
    }

    // Function to get the current count
    fun getCurrentCount(): Int {
        return _count.value ?: 0
    }

    // Function to update the count
    fun getUpdatedCount() {
        // Update the count and notify observers
        _count.value = (_count.value ?: 0) + 1
    }
}
