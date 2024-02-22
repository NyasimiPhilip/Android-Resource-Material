package com.android.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class DemoWorker extends Worker {


    public static final String KEY_WORKER = "key_worker";

    // Constructor for DemoWorker class.
    public DemoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {
        // Retrieve input data from the Data object.
        Data data = getInputData();

        // Get the integer value associated with the key KEY_COUNT_VALUE from input data.
        int countList = data.getInt(MainActivity.KEY_COUNT_VALUE, 0);

        // Loop through countList and log each count.
        for(int i = 0; i < countList; i++)
            Log.i("MYTAG", "Count is " + i);

        // Create output data to be sent back.
        Data dataToSend = new Data.Builder()
                .putString(KEY_WORKER, "Task Done Successfully")
                .build();

        // Return a successful result with output data.
        return Result.success(dataToSend);
    }
}
