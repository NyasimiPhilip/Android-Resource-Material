// Define the package where the interface belongs
package com.anushka.roomdemo.db

// Import necessary classes from the Room library
import androidx.lifecycle.LiveData
import androidx.room.*

// Declare the interface as a DAO (Data Access Object)
@Dao
interface SubscriberDAO {

    // Define a function to insert a subscriber into the database
    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber): Long

    // Define a function to update a subscriber in the database
    @Update
    suspend fun updateSubscriber(subscriber: Subscriber): Int

    // Define a function to delete a subscriber from the database
    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber): Int

    // Define a function to delete all subscribers from the database
    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll(): Int

    // Define a function to retrieve all subscribers from the database
    @Query("SELECT * FROM subscriber_data_table")
    // Use LiveData to observe changes in the data asynchronously
    fun getAllSubscribers(): LiveData<List<Subscriber>>
}
