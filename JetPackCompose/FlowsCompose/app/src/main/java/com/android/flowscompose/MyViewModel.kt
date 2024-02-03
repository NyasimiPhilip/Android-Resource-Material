package com.android.flowscompose

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * ViewModel class that demonstrates the usage of Kotlin Flows with coroutines.
 */
class MyViewModel : ViewModel() {
    /**
     * Simple flow emitting integers from 1 to 100 with a delay of 1 second between emissions.
     */
    val myFlow = flow<Int> {
        for (i in 1..100) {
            emit(i)
            delay(1000)
        }
    }

    init {
        // Initialize the ViewModel by demonstrating back pressure handling
        backPressureDemo()
    }

    /**
     * Demonstrates back pressure handling using a flow with delayed emissions and collection.
     * It produces values with a delay of 1 second and consumes them with a delay of 10 seconds.
     */
    private fun backPressureDemo() {
        val myFlow1 = flow<Int> {
            for (i in 1..10) {
                Log.i("MYTAG", "Produced$i")
                emit(i)
                delay(1000)
            }
        }

        viewModelScope.launch {
            myFlow1.collect {
                delay(10000)
                Log.i("MYTAG", "Consumed $it")
            }
        }
    }

    /**
     * Demonstrates back pressure handling with buffering using the 'buffer' operator.
     * It produces values with a delay of 1 second and consumes them with a delay of 10 seconds.
     * The 'buffer' operator helps in handling back pressure by allowing buffering of emitted values.
     */
    private fun backPressureDemo1() {
        val myFlow1 = flow<Int> {
            for (i in 1..10) {
                Log.i("MYTAG", "Produced$i")
                emit(i)
                delay(1000)
            }
        }.buffer() // Introduce buffering here

        viewModelScope.launch {
            myFlow1.collect {
                delay(10000)
                Log.i("MYTAG", "Consumed $it")
            }
        }
    }

    /**
     * Demonstrates the usage of `collectLatest` to only process the latest emitted value.
     * It emits values with a delay of 500 milliseconds and processes the latest emitted value
     * with a simulated async delay of 1 second.
     */
    fun collectLatest() = runBlocking {
        val myFlow = flow<Int> {
            for (i in 1..5) {
                emit(i)
                delay(500) // Simulate some work
            }
        }

        val job = launch {
            myFlow.collectLatest { value ->
                // Simulate some async processing
                println("Start processing: $value")
                delay(1000)
                println("End processing: $value")
            }
        }

        // Simulate some delay before emitting a new value
        delay(1500)
        myFlow.collectLatest { value ->
            println("Collecting latest: $value")
        }

        job.join()
    }
}
