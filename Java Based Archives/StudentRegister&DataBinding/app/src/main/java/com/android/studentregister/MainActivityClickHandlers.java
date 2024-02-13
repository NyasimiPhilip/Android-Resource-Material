package com.android.studentregister;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandlers {
    private Activity activity;
    private static final int NEW_STUDENT_ACTIVITY_REQUEST_CODE = 1;

    // Constructor to initialize the activity
    public MainActivityClickHandlers(Activity activity) {
        this.activity = activity;
    }

    // Method to handle click on FloatingActionButton
    public void onFabClick(View view) {
        Intent intent = new Intent(activity, AddNewStudentActivity.class);
        activity.startActivityForResult(intent, NEW_STUDENT_ACTIVITY_REQUEST_CODE);
    }
}
