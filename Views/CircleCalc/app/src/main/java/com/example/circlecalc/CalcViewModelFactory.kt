package com.example.circlecalc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Factory class responsible for creating instances of CalcViewModel with dependencies
class CalcViewModelFactory(
    private val calculations: Calculations // Dependency injected Calculations interface
) : ViewModelProvider.Factory {

    // Function to create a new instance of the specified ViewModel class
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Ensure that the specified class is of type CalcViewModel
        if (modelClass.isAssignableFrom(CalcViewModel::class.java)) {
            // If it is, create and return a new instance of CalcViewModel with the provided calculations dependency
            return CalcViewModel(calculations) as T
        }
        // If the specified class is not of type CalcViewModel, throw an IllegalArgumentException
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
