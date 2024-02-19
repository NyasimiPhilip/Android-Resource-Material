package com.android.studentregister;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.studentregister.databinding.ActivityAddNewStudentBinding;
import com.android.studentregister.entity.Student;

// Activity class for adding a new student
public class AddNewStudentActivity extends AppCompatActivity {

    private ActivityAddNewStudentBinding binding;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_student);

        // Initialize the student object
        student = new Student();
        binding.setStudent(student);

        // Set up click handlers with the student object
        binding.setClickHandlers(new AddNewStudentActivityClickHandlers(this, student));
    }
}
