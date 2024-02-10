package com.example.circlecalc

// Implementation of the Calculations interface for circle calculations
class MyCalc : Calculations {

    // Constant value for pi
    private val pi = 3.14

    // Function to calculate the circumference of a circle based on the provided radius
    // Returns the calculated circumference
    override fun calculateCircumference(radius: Double): Double {
        return 2 * pi * radius
    }

    // Function to calculate the area of a circle based on the provided radius
    // Returns the calculated area
    override fun calculateArea(radius: Double): Double {
        return pi * radius * radius
    }
}
