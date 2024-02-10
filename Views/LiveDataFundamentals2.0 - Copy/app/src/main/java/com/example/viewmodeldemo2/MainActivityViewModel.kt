package com.example.viewmodeldemo2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal: Int) : ViewModel() {
    private var total = MutableLiveData<Int>()
    val totalData: LiveData<Int>
        get() = total

    // Observable property for input text
    val inputText = MutableLiveData<String>()

    init {
        total.value = startingTotal
    }

    // Define a function named set2Total
    fun set2Total() {
        // Access the value property of the inputText variable with safe call operator (?.)
        // Convert the value to an integer using toIntOrNull() method, returns null if conversion fails
        inputText.value?.toIntOrNull()?.let {
            // Inside the let block, the conversion was successful, and the result is not null
            // If total.value is null, set it to 0, then add the converted integer to it
            total.value = (total.value ?: 0) + it
        }
    }

    // Define a function named setTotal
    fun setTotal() {
        // Declare a variable intInput and initialize it with the non-null result of inputText.value!!.toInt()
        // This assumes that inputText.value is not null; otherwise, it will throw a NullPointerException
        val intInput: Int = inputText.value!!.toInt()

        // Use the safe call operator (?.) to check if total.value is not null
        // If not null, add intInput to the existing total.value
        total.value = (total.value)?.plus(intInput)
    }

}
