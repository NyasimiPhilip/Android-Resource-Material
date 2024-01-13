package com.anushka.roommigrationdemo1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_info")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    val id: Long,

    @ColumnInfo(name = "student_name", defaultValue = "No Name")
    val name: String,

    @ColumnInfo(name = "student_email", defaultValue = "No Email")
    val email: String?,

    @ColumnInfo(name = "subject_name", defaultValue = "No subject")
    val course: String
)

