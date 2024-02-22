package com.android.workmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    public static final String KEY_COUNT_VALUE = "key count value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the layout for the activity.

        // Create a Data object to hold key-value pairs.
        Data data = new Data.Builder().putInt(KEY_COUNT_VALUE, 4500).build();

        // Create Constraints for the work request.
        Constraints constraint = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        // Create a OneTimeWorkRequest for the DemoWorker class with input data and constraints.
        final OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(DemoWorker.class)
                //.setConstraints(constraint)
                .setInputData(data)
                .build();

        // Find and initialize the Button and TextView from the layout.
        button = findViewById(R.id.button);
        textView = findViewById(R.id.tvStatus);

        // Set a click listener for the Button.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Enqueue the work request to the WorkManager when the Button is clicked.
                WorkManager.getInstance().enqueue(oneTimeWorkRequest);
            }
        });

        // Observe the WorkInfo associated with the oneTimeWorkRequest.
        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null) {
                            // Update the TextView with the current state of the work request.
                            textView.setText(workInfo.getState().name());
                        }
                        if (workInfo.getState().isFinished()) {
                            // If the work request is finished, retrieve output data and display a Toast message.
                            Data data1 = workInfo.getOutputData();
                            String message = data1.getString(DemoWorker.KEY_WORKER);
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
