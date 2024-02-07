// Define the package where this class belongs
package com.example.bindingdemo2

// Import necessary classes from the Android framework
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

// Define a ViewModel class named MainViewModel that extends ViewModel
class MainViewModel : ViewModel() {

    // Create an ObservableField to track the visibility of a progress bar with an initial value of View.GONE
    private val progressBarVisibility = ObservableField(View.GONE)

    // Create an ObservableField to track the text displayed on a button with an initial value of "start"
    private val buttonText = ObservableField("start")

    // Define a function to get the ObservableField representing progress bar visibility
    fun getProgressBarVisibility(): ObservableField<Int> {
        return progressBarVisibility
    }

    // Define a function to get the ObservableField representing button text
    fun getButtonText(): ObservableField<String> {
        return buttonText
    }

    // Define a function dto handle button clicks
    fun onButtonClicked() {
        // Check if the progress bar is currently not visible
        if (progressBarVisibility.get() == View.GONE) {
            // If not visible, set visibility to visible and change button text to "Stop"
            progressBarVisibility.set(View.VISIBLE)
            buttonText.set("Stop")
        } else {
            // If visible, set visibility to gone and change button text to "Start"
            progressBarVisibility.set(View.GONE)
            buttonText.set("Start")
        }
    }
}
