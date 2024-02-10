package com.android.uilayer

// Define a sealed class named UIEvent
sealed class UIEvent {
    // Define a data class for showing a message
    data class ShowMessage(val message: String) : UIEvent()
}
