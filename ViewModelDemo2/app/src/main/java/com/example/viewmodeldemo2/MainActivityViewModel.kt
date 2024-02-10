package com.example.viewmodeldemo2

import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal: Int) : ViewModel() {
    var total: Int = 0

    init {
        total = startingTotal
    }
/*
the get and set functions were not required  the property access
modifier is set to public and as a result total is accessible through the dot notation
 */
    fun get1Total(): Int {
        return total
    }

    fun set1Total(input: Int) {
        total = input
    }
}
