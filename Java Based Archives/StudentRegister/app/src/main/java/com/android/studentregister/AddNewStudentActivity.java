package com.android.studentregister;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.studentregister.databinding.ActivityAddNewStudentBinding;

// Activity class for adding a new student
public class AddNewStudentActivity extends AppCompatActivity {

    private ActivityAddNewStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set OnClickListener for the submit button using data binding
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input data using data binding
                String name = binding.etName.getText().toString();
                String email = binding.etEmail.getText().toString();
                String country = binding.etCountry.getText().toString();

                // Check if the name field is empty
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Name field cannot be empty", Toast.LENGTH_LONG).show();
                } else {
                    // Create an intent to pass the data back to the MainActivity
                    Intent intent = new Intent();
                    intent.putExtra("NAME", name);
                    intent.putExtra("EMAIL", email);
                    intent.putExtra("COUNTRY", country);
                    setResult(RESULT_OK, intent);
                    finish(); // Finish the activity
                }
            }
        });
    }
}
