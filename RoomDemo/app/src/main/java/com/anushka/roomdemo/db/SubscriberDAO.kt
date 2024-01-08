// This code defines a Data Access Object (DAO) interface named SubscriberDAO.
// A DAO is responsible for defining methods to interact with the Room database.

package com.anushka.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// Annotating the interface with @Dao indicates that this is a Room DAO (Data Access Object).
@Dao
interface SubscriberDAO {

    // Annotating the method with @Insert specifies that it will be used for inserting data into the database.
    // The 'suspend' keyword indicates that this method should be called from a coroutine.
    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber): Long

    // Annotating the method with @Update specifies that it will be used for updating data in the database.
    @Update
    suspend fun updateSubscriber(subscriber: Subscriber) : Int

    // Annotating the method with @Delete specifies that it will be used for deleting data from the database.
    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber) : Int

    // Annotating the method with @Query specifies a custom SQL query for deleting all data from the database table.
    // This query is used to clear all records in the SUBSCRIBER_DATA_TABLE.
    @Query("DELETE FROM SUBSCRIBER_DATA_TABLE")
    suspend fun deleteAll() : Int

    // Annotating the method with @Query specifies a custom SQL query for retrieving all subscribers from the database.
    // The returned data is wrapped in a LiveData object, making it observable for UI updates.
    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers(): LiveData<List<Subscriber>>
}
