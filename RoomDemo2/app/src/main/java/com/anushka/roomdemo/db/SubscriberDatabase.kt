// Define the package where the class belongs
package com.anushka.roomdemo.db

// Import necessary classes from the Room library
import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec

/**
 * Database class responsible for managing the SQLite database using Room.
 * This class is a RoomDatabase, which provides an abstraction layer over SQLite operations.
 * It contains a table for Subscribers and manages database migrations.
 *
 * @property subscriberDAO Abstract property to access the Data Access Object (DAO) for Subscribers.
 * @constructor Creates an instance of the SubscriberDatabase.
 */
@Database(
    // Specify the entities (tables) in the database
    entities = [Subscriber::class],
    // Specify the version of the database
    version = 3,
    // Define auto migrations for version updates
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = SubscriberDatabase.Migration1To2::class),
        AutoMigration(from = 2, to = 3, spec = SubscriberDatabase.Migration2To3::class)
    ],
    exportSchema = true // Set exportSchema to true
)
// Make the class abstract to create an instance using RoomDatabase
abstract class SubscriberDatabase : RoomDatabase() {

    // Declare an abstract property to access the DAO (Data Access Object)
    abstract val subscriberDAO: SubscriberDAO

    // Define a companion object to provide a singleton instance of the database
    companion object {
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null

        /**
         * Get or create an instance of the SubscriberDatabase.
         * This function ensures that only one instance of the database is created.
         *
         * @param context The application context.
         * @return The singleton instance of the SubscriberDatabase.
         */
        fun getInstance(context: Context): SubscriberDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    // Build the Room database instance
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    // Define the migration from version 1 to version 2
    @RenameColumn(
        tableName = "subscriber_data_table",
        fromColumnName = "subscriber_name",
        toColumnName = "subs_id"
    )
    class Migration1To2 : AutoMigrationSpec

    // Define the migration from version 2 to version 3
    @RenameColumn(
        tableName = "subscriber_data_table",
        fromColumnName = "subscriber_id",
        toColumnName = "subscriber_name"
    )
    class Migration2To3 : AutoMigrationSpec
}
