package com.android.studentregister.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.studentregister.entity.Student;

// Annotates the class to be a Room Database with a table (or multiple tables) for the specified entities.
@Database(entities = {Student.class}, version = 1)
public abstract class StudentAppDatabase extends RoomDatabase {

    // Declares an abstract method to get the DAO (Data Access Object) interface for performing database operations.
    public abstract StudentDao getStudentDao();

}
