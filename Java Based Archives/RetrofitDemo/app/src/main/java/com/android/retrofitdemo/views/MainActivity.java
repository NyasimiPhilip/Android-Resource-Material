package com.android.retrofitdemo.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.retrofitdemo.R;
import com.android.retrofitdemo.adapter.CountryAdapter;
import com.android.retrofitdemo.model.Data;
import com.android.retrofitdemo.service.RestCountriesService;
import com.android.retrofitdemo.service.RetroFitInstance;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Data> data;
    private CountryAdapter countryAdapter;
    private RecyclerView recyclerView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.NavigateButton);

        // Set OnClickListener for the button
        button.setOnClickListener(v -> {
            // Intent to start SecondActivity
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });


        // Call the method to fetch data
        getData();
    }

    private void getData() {
        // Create an instance of the RestCountriesService using RetrofitInstance
        RestCountriesService service = RetroFitInstance.getRestCountriesService();

        // Make the API call using the service
        Call<List<Data>> call = service.getAllCountries();

        // Execute the call asynchronously
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    List<Data> dataList = response.body();
                    if (dataList != null && !dataList.isEmpty()) {
                        // Data retrieval successful, handle the data here
                        processData(dataList);
                    } else {
                        Log.e("MainActivity", "No data received");
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("MainActivity", "Failed to fetch data: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                // Handle failure
                Log.e("MainActivity", "Error fetching data: " + t.getMessage());
            }
        });
    }
    private void processData(List<Data> dataList) {
        // Initialize RecyclerView and CountryAdapter
        recyclerView = findViewById(R.id.rvCountries);
        countryAdapter = new CountryAdapter((ArrayList<Data>) dataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countryAdapter);
    }


 /*   private void processData(List<Data> dataList) {
        // Process and display data as needed
        for (Data data : dataList) {
            Log.d("MainActivity", "Country Name: " + data.getName());

            // Check if the capital list is not null and not empty before accessing its elements
            List<String> capitalList = data.getCapital();
            if (capitalList != null && !capitalList.isEmpty()) {
                Log.d("MainActivity", "Capital: " + capitalList.get(0));
            } else {
                Log.e("MainActivity", "Capital list is null or empty for country: " + data.getName());
            }

            // Log additional data or perform other operations
        }
    }*/



}
