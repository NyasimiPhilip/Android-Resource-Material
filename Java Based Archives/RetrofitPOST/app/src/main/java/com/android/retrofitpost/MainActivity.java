package com.android.retrofitpost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.retrofitpost.model.User;
import com.android.retrofitpost.model.UserCredentials;
import com.android.retrofitpost.service.RetrofitInstance;
import com.android.retrofitpost.service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText passWord;
    private Button submitButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEmail = (EditText) findViewById(R.id.et_email);
        passWord = (EditText) findViewById(R.id.et_password);
        submitButton = (Button) findViewById(R.id.btn_submit);
        resultTextView = (TextView) findViewById(R.id.tv_result);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postData();
            }
        });
    }
    private void postData() {
        // Create a new User object
        User user = new User();

        UserCredentials credentials = new UserCredentials();
        credentials.setUserEmail(userEmail.getText().toString());
        credentials.setPassWord(passWord.getText().toString());

        // Show a toast before making the request
        Toast.makeText(getApplicationContext(), "Sending request...", Toast.LENGTH_SHORT).show();

        // Get Retrofit service instance
        RetrofitService postAppService = RetrofitInstance.getService();

        // Make the POST request using Retrofit
        Call<User> call = postAppService.getResult(user);

        // Clear email and password fields after making the request
        userEmail.setText("");
        passWord.setText("");

        // Asynchronously enqueue the request
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    User returnedUser = response.body();

                    // Show a toast with the received user ID
                    Toast.makeText(getApplicationContext(), "Received User ID: " + returnedUser.getId(), Toast.LENGTH_SHORT).show();

                    // Update UI with the received user ID
                    resultTextView.setText("Id is : " + returnedUser.getId());
                } else {
                    // Handle unsuccessful response
                    Log.e("MYTAG", "Failed to get user data. Error: " + response.message());
                    Toast.makeText(getApplicationContext(), "Failed to get user data. Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Handle request failure
                Toast.makeText(getApplicationContext(), "Failed to make POST request: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}