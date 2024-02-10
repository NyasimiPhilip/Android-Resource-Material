// Define the package where the class belongs
package com.anushka.roomdemo.db

// Import necessary classes from the Room library
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Annotate the class as an Entity, indicating it's a table in the database
@Entity(tableName = "subscriber_data_table")
// Create a data class named Subscriber to represent the structure of the table
data class Subscriber (
    // Specify the primary key with auto-generate enabled
    @PrimaryKey(autoGenerate = true)
    // Define the column name for the primary key
    @ColumnInfo(name = "subs_id")
    val id : Int,

    // Define a column for the subscriber's name
    @ColumnInfo(name = "subscriber_name")
    var name : String,

    // Define a column for the subscriber's email
    @ColumnInfo(name = "subscriber_email")
    var email : String
)
