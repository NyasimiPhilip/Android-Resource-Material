package com.android.studentregister;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.studentregister.databinding.ActivityAddNewStudentBinding;

// Activity class for adding a new student
public class AddNewStudentActivity extends AppCompatActivity {

    private ActivityAddNewStudentBinding binding;
    private AddNewStudentActivityClickHandlers clickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_student);

        // Set up click handlers
        clickHandlers = new AddNewStudentActivityClickHandlers(this);
        binding.setClickHandlers(clickHandlers);
    }
}
