// This code defines a data class named "Subscriber" that represents an entity in a Room database.
// Room is a persistence library provided by Android for handling SQLite databases.

package com.anushka.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Annotating the class with @Entity indicates that this class is a Room database entity.
@Entity(tableName = "subscriber_data_table")
data class Subscriber(

    // Annotating the field with @PrimaryKey designates it as the primary key for the entity.
    // The 'autoGenerate' attribute is set to true, which means Room will automatically generate unique IDs.
    @PrimaryKey(autoGenerate = true )
    @ColumnInfo(name = "subscriber_id")
    val id: Int,

    // Annotating this field with @ColumnInfo specifies the name of the column in the database.
    // In this case, the column is named "subscriber_name".
    @ColumnInfo(name = "subscriber_name")
    var name: String,

    // Another column in the database named "subscriber_email".
    @ColumnInfo(name = "subscriber_email")
    var email: String
)
