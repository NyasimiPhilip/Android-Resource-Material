package com.anushka.roomdemo

// Define a generic class `Event` with a covariant type parameter `T`
open class Event<out T>(private val content: T) {

    // Boolean flag to track whether the event has been handled
    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content if the event has not been handled; otherwise, returns null.
     * Marks the event as handled after retrieving the content.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            // Mark the event as handled and return its content
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content of the event without marking it as handled.
     * Useful for peeking at the content without consuming the event.
     */
    fun peekContent(): T = content
}

