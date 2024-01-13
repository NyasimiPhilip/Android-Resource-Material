package com.anushka.roommigrationdemo1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

@Database(
    entities = [Student::class],
    version = 6
)
abstract class StudentDatabase : RoomDatabase() {

    abstract val subscriberDAO: StudentDAO

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null

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

        fun getInstance(context: Context): StudentDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_data_database"
                    )
                        .addMigrations(MIGRATION_5_6)
                        //.fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
