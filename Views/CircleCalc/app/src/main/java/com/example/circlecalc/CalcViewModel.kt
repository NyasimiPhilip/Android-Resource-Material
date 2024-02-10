package com.example.circlecalc

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception

// ViewModel class responsible for handling calculations related to circles
class CalcViewModel(
    private val calculations: Calculations // Dependency injected Calculations interface
) : ViewModel() {

    // MutableLiveData for holding the radius input as a string
    var radius = MutableLiveData<String>()

    // MutableLiveData for holding the calculated area as a string
    var area = MutableLiveData<String?>()
    // LiveData for exposing the area value to observers
    val areaValue: LiveData<String?>
        get() = area

    // MutableLiveData for holding the calculated circumference as a string
    var circumference = MutableLiveData<String?>()
    // LiveData for exposing the circumference value to observers
    val circumferenceValue: LiveData<String?>
        get() = circumference

    // Function to perform calculations when the user clicks the "Calculate" button
    fun calculate() {
        try {
            // Attempt to convert the radius string to a Double
            val radiusDoubleValue = radius.value?.toDouble()

            // Check if the conversion was successful
            if (radiusDoubleValue != null) {
                // If successful, calculate and update both area and circumference
                calculateArea(radiusDoubleValue)
                calculateCircumference(radiusDoubleValue)
            } else {
                // If conversion fails, set both area and circumference to null
                area.value = null
                circumference.value = null
            }
        } catch (e: Exception) {
            // Handle any exceptions that might occur during calculations
            Log.i("MYTAG", e.message.toString())
            area.value = null
            circumference.value = null
        }
    }

    // Function to calculate and update the area based on the provided radius
    fun calculateArea(radius: Double) {
        val calculatedArea = calculations.calculateArea(radius)
        area.value = calculatedArea.toString()
    }

    // Function to calculate and update the circumference based on the provided radius
    fun calculateCircumference(radius: Double) {
        val calculatedCircumference = calculations.calculateCircumference(radius)
        circumference.value = calculatedCircumference.toString()
    }
}
