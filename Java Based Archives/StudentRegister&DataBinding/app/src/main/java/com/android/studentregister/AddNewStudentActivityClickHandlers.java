package com.android.studentregister;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.android.studentregister.databinding.ActivityAddNewStudentBinding;

public class AddNewStudentActivityClickHandlers {
    private Context context;

    public AddNewStudentActivityClickHandlers(Context context) {
        this.context = context;
    }

    public void onSubmitButtonClick(View view) {
        ActivityAddNewStudentBinding binding = DataBindingUtil.findBinding(view);
        if (binding != null) {
            // Get the input data using data binding
            String name = binding.etName.getText().toString();
            String email = binding.etEmail.getText().toString();
            String country = binding.etCountry.getText().toString();

            // Check if the name field is empty
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(context, "Name field cannot be empty", Toast.LENGTH_LONG).show();
            } else {
                // Create an intent to pass the data back to the MainActivity
                Intent intent = new Intent();
                intent.putExtra("NAME", name);
                intent.putExtra("EMAIL", email);
                intent.putExtra("COUNTRY", country);
                ((AddNewStudentActivity) context).setResult(RESULT_OK, intent);
                ((AddNewStudentActivity) context).finish(); // Finish the activity
            }
        }
    }
}
