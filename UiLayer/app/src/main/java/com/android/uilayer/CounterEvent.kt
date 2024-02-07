package com.android.uilayer

// Define a sealed class named CounterEvent
sealed class CounterEvent {
    // Define a data class for when a value is entered
    data class ValueEntered(val value: String) : CounterEvent()

    // Define a data object for when the count button is clicked
    data object CountButtonClicked : CounterEvent()

    // Define a data object for when the reset button is clicked
    data object ResetButtonClicked : CounterEvent()
}
