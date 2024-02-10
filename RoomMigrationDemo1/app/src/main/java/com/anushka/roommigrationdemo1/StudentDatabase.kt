package com.anushka.roommigrationdemo1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

// Declaring a Room Database with the specified entities and version
@Database(
    entities = [Student::class],
    version = 6
)
abstract class StudentDatabase : RoomDatabase() {

    // Declaring an abstract value for accessing the Data Access Object (DAO)
    abstract val subscriberDAO: StudentDAO

    // Companion object to hold static members and functions
    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        // Defining a migration from version 5 to version 6
        private val MIGRATION_5_6: Migration = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Check if the table exists
                val tableExistsQuery =
                    "SELECT name FROM sqlite_master WHERE type='table' AND name='student_info'"
                val cursor = database.query(tableExistsQuery)

                if (cursor != null && cursor.count > 0) {
                    cursor.close()
                    // The table already exists, no need to create it
                    return
                }

                // The table does not exist, create it
                val createTableQuery = """
                    CREATE TABLE IF NOT EXISTS student_info (
                        student_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        student_name TEXT DEFAULT 'No Name',
                        student_email TEXT DEFAULT 'No Email',
                        subject_name TEXT DEFAULT 'No Subject'
                    )
                """
                database.execSQL(createTableQuery)
            }
        }

        // Function to obtain an instance of the database
        fun getInstance(context: Context): StudentDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    // Creating a new instance of the database using Room's databaseBuilder
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_data_database"
                    )
                        // Adding the migration from version 5 to 6
                        .addMigrations(MIGRATION_5_6)
                        //.fallbackToDestructiveMigration() // Optional: Uncomment to allow destructive migration
                        .build()
                    INSTANCE = instance
                }
                // Returning the database instance
                return instance
            }
        }
    }
}
