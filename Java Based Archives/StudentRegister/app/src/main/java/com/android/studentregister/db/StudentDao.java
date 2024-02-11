package com.android.studentregister.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.studentregister.entity.Student;

import java.util.List;

// Annotates the interface to be a Room DAO (Data Access Object), which provides methods for accessing the database.
@Dao
public interface StudentDao {

    // Declares a method to insert a student entity into the database.
    @Insert
    void insert(Student student);

    // Declares a method to retrieve all students from the database.
    @Query("SELECT * FROM student_table")
    List<Student> getAllStudents();

    // Declares a method to delete a student entity from the database.
    @Delete
    void delete(Student student);

}
