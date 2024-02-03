package com.example.viewmodeldemo2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.stateflow.MainActivityViewModel

class MainActivityViewModelFactory(private val startingTotal : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainActivityViewModel(startingTotal) as T
}