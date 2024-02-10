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

    fun set2Total() {
        inputText.value?.toIntOrNull()?.let {
            total.value = (total.value ?: 0) + it
        }
    }
}
