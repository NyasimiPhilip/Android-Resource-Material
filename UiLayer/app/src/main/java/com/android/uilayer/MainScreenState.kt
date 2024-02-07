package com.android.uilayer

// Define a data class named MainScreenState to represent the state of the main screen
data class MainScreenState(
    // Boolean property indicating whether the count button is visible or not, default is false
    var isCountButtonVisible: Boolean = false,

    // String property representing the result to be displayed on the screen, default is an empty string
    var displayingResult: String = "",

    // String property representing the input value entered by the user, default is an empty string
    var inputValue: String = ""
)
