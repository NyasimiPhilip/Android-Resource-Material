package com.android.studentregister;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.android.studentregister.entity.Student;

public class AddNewStudentActivityClickHandlers {
    private Context context;
    private Student student;

    public AddNewStudentActivityClickHandlers(Context context, Student student) {
        this.context = context;
        this.student = student;
    }

    public void onSubmitButtonClick(View view) {

        if (student != null) {
            // Check if the name field is empty
            if (TextUtils.isEmpty(student.getName())) {
                Toast.makeText(context, "Name field cannot be empty", Toast.LENGTH_LONG).show();
            } else {
                // Create an intent to pass the data back to the MainActivity
                Intent intent = new Intent();
                intent.putExtra("NAME", student.getName());
                intent.putExtra("EMAIL", student.getEmail());
                intent.putExtra("COUNTRY", student.getCountry());
                ((AddNewStudentActivity) context).setResult(RESULT_OK, intent);
                ((AddNewStudentActivity) context).finish(); // Finish the activity
            }
        }
    }
}
