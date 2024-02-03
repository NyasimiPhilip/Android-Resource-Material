package com.android.stateflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(startingTotal: Int) : ViewModel() {
    // Using StateFlow for reactive, flow-based data updates
    private val _flowTotal = MutableStateFlow<Int>(0)

    private val _message = MutableSharedFlow<String>()
    val message : SharedFlow<String> = _message


    val flowTotal: StateFlow<Int>
        get() = _flowTotal

    // Using LiveData for reactive, observer-based data updates
    // private var total = MutableLiveData<Int>()
    // val totalData: LiveData<Int>
    //     get() = total

    init {
        _flowTotal.value = startingTotal
        // total.value = startingTotal
    }

    // Updating total using StateFlow
    fun setTotal(input: Int) {
        _flowTotal.value = (_flowTotal.value).plus(input)
        viewModelScope.launch{
            _message.emit("Total Updated Successfully")

        }
    }

    // Updating total using LiveData
    // fun setTotal(input: Int) {
    //     total.value = (total.value)?.plus(input)
    // }
}

/*
Comparison:

1. LiveData:
   - Part of the Android Architecture Components.
   - Designed for observer-based pattern, reacts to changes with observers.
   - Typically used for UI updates in Android.
   - Supports lifecycle-aware components, preventing memory leaks.

2. StateFlow:
   - Part of Kotlin Coroutines.
   - Designed for reactive, flow-based pattern.
   - Provides a simple and concise way to handle state updates.
   - Suited for non-UI logic and works well with Kotlin Coroutines.

In this example, StateFlow is chosen for its simplicity and suitability for non-UI logic.
LiveData might be preferred in Android-specific scenarios where lifecycle awareness is crucial.
*/
