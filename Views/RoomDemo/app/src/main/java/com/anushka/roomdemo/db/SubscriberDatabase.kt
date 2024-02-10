package com.anushka.roomdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotating the class with @Database indicates that this class represents a Room database.
// The 'entities' attribute specifies the entities (tables) in the database, and 'version' is the version number.
@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase : RoomDatabase() {

    // Abstract property representing the Data Access Object (DAO) for the Subscriber entity.
    abstract val subscriberDAO: SubscriberDAO

    companion object {
        // Using @Volatile to ensure that the INSTANCE variable is always up-to-date across threads.
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null

        // A static method to get an instance of the database.
        fun getInstance(context: Context): SubscriberDatabase {
            synchronized(this) {
                var instance: SubscriberDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_database"
                    ).build()

                    INSTANCE = instance
                }

                // Returning the database instance.
                return instance!!
            }
        }
    }
}
