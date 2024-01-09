// Define the package where the class belongs
package com.anushka.roomdemo.db

// Import necessary classes from the Room library
import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec

// Define the SubscriberDatabase class as a RoomDatabase
@Database(
    // Specify the entities (tables) in the database
    entities = [Subscriber::class],
    // Specify the version of the database
    version = 3,
    // Define auto migrations for version updates
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = SubscriberDatabase.Migration1To2::class),
        AutoMigration(from = 2, to = 3, spec = SubscriberDatabase.Migration2To3::class)
    ]
)
// Make the class abstract to create an instance using RoomDatabase
abstract class SubscriberDatabase : RoomDatabase() {

    // Declare an abstract property to access the DAO (Data Access Object)
    abstract val subscriberDAO: SubscriberDAO

    // Define a companion object to provide a singleton instance of the database
    companion object {
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null

        // Define a function to get or create an instance of the database
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
