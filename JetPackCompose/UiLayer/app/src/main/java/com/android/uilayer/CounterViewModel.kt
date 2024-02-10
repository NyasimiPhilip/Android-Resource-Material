package com.android.uilayer

// Import necessary classes from Jetpack Compose and ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

// Define the CounterViewModel class, which extends ViewModel
class CounterViewModel : ViewModel() {

    // Define a mutable state for the screen state
    private val _screenState = mutableStateOf(MainScreenState(
        inputValue = "",
        displayingResult = "Total is 0.0"
    ))

    // Create a State object for observing changes in screenState
    val screenState: State<MainScreenState> = _screenState

    // Create a MutableSharedFlow for UI events
    private val _uiEventFlow = MutableSharedFlow<UIEvent>()

    // Expose the UI event flow as a SharedFlow to observers
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    // Initialize the total counter
    private var total = 0.0

    // Function to add input value to the total
    private fun addToTotal() {
        total += _screenState.value.inputValue.toDouble()
        // Update screenState with the new total and set the count button to be invisible
        _screenState.value = _screenState.value.copy(
            displayingResult = "Total is $total",
            isCountButtonVisible = true
        )
    }

    // Function to reset the total counter
    private fun resetTotal() {
        total = 0.0
        // Update screenState with the reset total, empty input value, and invisible count button
        _screenState.value = _screenState.value.copy(
            displayingResult = "Total is $total",
            inputValue = "",
            isCountButtonVisible = false
        )
    }

    // Function to handle events triggered by the UI
    fun onEvent(event: CounterEvent) {
        when (event) {
            is CounterEvent.ValueEntered -> {
                // Update screenState with the entered value and make the count button visible
                _screenState.value = _screenState.value.copy(
                    inputValue = event.value,
                    isCountButtonVisible = true
                )
            }
            is CounterEvent.CountButtonClicked -> {
                // Call addToTotal to add the value to the total, then emit a UI event
                addToTotal()
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        UIEvent.ShowMessage(
                            message = "Value added successfully"
                        )
                    )
                }
            }
            is CounterEvent.ResetButtonClicked -> {
                // Call resetTotal to reset the total, then emit a UI event
                resetTotal()
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        UIEvent.ShowMessage(
                            message = "Value reset successfully"
                        )
                    )
                }
            }
        }
    }
}
