package com.example.viewmodeldemo2

import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal: Int) : ViewModel() {
    var total: Int = 0

    init {
        total = startingTotal
    }

    fun get1Total(): Int {
        return total
    }

    fun set2Total(input: Int) {
        total = input
    }
}
