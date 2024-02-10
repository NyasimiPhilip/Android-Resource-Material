package com.example.circlecalc

// Define an interface named com.example.circlecalc.Calculations
interface Calculations {

    // Declare a function to calculate the circumference of a circle
    // Takes a 'radius' parameter (type: Double) and returns the calculated circumference (type: Double)
    fun calculateCircumference(radius: Double): Double

    // Declare a function to calculate the area of a circle
    // Takes a 'radius' parameter (type: Double) and returns the calculated area (type: Double)
    fun calculateArea(radius: Double): Double
}
