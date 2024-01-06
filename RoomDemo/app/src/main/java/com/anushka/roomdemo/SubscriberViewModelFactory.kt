package com.anushka.roomdemo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushka.roomdemo.db.SubscriberRepository

class SubscriberViewModelFactory(
    private val repository: SubscriberRepository,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SubscriberViewModel(repository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
