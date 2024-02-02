package com.example.composestatedemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    private var count by  mutableIntStateOf(0)
    fun increaseCount(){
       count++
    }
}