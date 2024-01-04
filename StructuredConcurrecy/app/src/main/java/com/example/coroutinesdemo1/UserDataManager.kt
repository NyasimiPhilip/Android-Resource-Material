package com.example.coroutinesdemo1

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserDataManager {

    suspend fun getTotalUserCount(): Int {
        var count = 0


        CoroutineScope(Dispatchers.IO).launch {
                delay(1000)
                count = 50
        }.join() // Wait for the launch coroutine to complete


        // Use async to perform the second asynchronous operation
        val deferred = CoroutineScope(Dispatchers.IO).async {
            delay(3000)
            return@async 70
        }

        // Return the sum of count and the result of the deferred operation
        return count + deferred.await()
    }
}
