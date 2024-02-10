package com.example.circlecalc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CalcViewModelTest {

    // Rule to enforce that tasks are executed synchronously
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Late-initialized variables for CalcViewModel and Calculations
    private lateinit var calcViewModel: CalcViewModel
    private lateinit var calculations: Calculations

    // Setup method executed before each test
    @Before
    fun setup() {
        // Mocking the Calculations class using Mockito
        calculations = Mockito.mock(Calculations::class.java)

        // Stubbing the calculateArea method with a predefined value
        Mockito.`when`(calculations.calculateArea(2.1)).thenReturn(13.8474)

        Mockito.`when`(calculations.calculateCircumference(1.0)).thenReturn(6.28)

        // Creating an instance of CalcViewModel with the mocked Calculations
        calcViewModel = CalcViewModel(calculations)
    }

    // Test case for the calculateArea method in CalcViewModel
    @Test
    fun calculateArea_radiusSent_updateLiveData() {
        // Calling the calculateArea method with a specific radius
        calcViewModel.calculateArea(2.1)

        // Retrieving the result from the LiveData in calcViewModel
        val result: String? = calcViewModel.areaValue.value

        // Asserting that the result matches the expected value
        assertThat(result).isEqualTo(13.8474.toString())
    }
    @Test
    fun calculateCircumference_radiusSent_updateLiveData() {
        // Calling the calculateCircumference method with a specific radius
        calcViewModel.calculateCircumference(1.0)

        // Retrieving the result from the LiveData in calcViewModel
        val result: String? = calcViewModel.circumferenceValue.value

        // Asserting that the result matches the expected value
        assertThat(result).isEqualTo(6.28.toString())
    }

}
