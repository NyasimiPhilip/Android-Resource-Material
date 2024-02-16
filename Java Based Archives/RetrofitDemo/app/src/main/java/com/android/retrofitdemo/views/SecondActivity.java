package com.android.retrofitdemo.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.retrofitdemo.R;
import com.android.retrofitdemo.model.Data;
import com.android.retrofitdemo.service.RestCountriesService;
import com.android.retrofitdemo.service.RetroFitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    EditText countryCodeEditText;
    TextView countryNameTextView;
    Button submitButton;
    Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        countryCodeEditText = findViewById(R.id.etCountryName);
        countryNameTextView = findViewById(R.id.tvCountryData);
        submitButton = findViewById(R.id.btnSubmit);
        clearButton = findViewById(R.id.btnClear);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countryName = countryCodeEditText.getText().toString();

                RestCountriesService restCountriesService = RetroFitInstance.getRestCountriesService();

                Call<List<Data>> call = restCountriesService.getCountryByName(countryName);

                call.enqueue(new Callback<List<Data>>() {
                    @Override
                    public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                        if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                            // Extract the first Data object from the list
                            Data data = response.body().get(0);
                            // Format the data
                            StringBuilder countryInfo = new StringBuilder();
                            countryInfo.append("Name: ").append(String.valueOf(data.getName())).append("\n");
                            countryInfo.append("Alpha-2 Code: ").append(data.getCca2()).append("\n");
                            countryInfo.append("Alpha-3 Code: ").append(data.getCca3()).append("\n");
                            countryInfo.append("Capital: ").append(data.getCapital().get(0)).append("\n");
                            countryInfo.append("Region: ").append(data.getRegion()).append("\n");
                            countryInfo.append("Subregion: ").append(data.getSubregion()).append("\n");
                            countryInfo.append("Population: ").append(data.getPopulation()).append("\n");
                            countryInfo.append("Area: ").append(data.getArea()).append(" square kilometers").append("\n");
                            countryInfo.append("Borders: ").append(data.getBorders()).append("\n");
                            countryInfo.append("Timezones: ").append(data.getTimezones()).append("\n");
                            countryInfo.append("Continents: ").append(data.getContinents()).append("\n");
                            countryInfo.append("Start of Week: ").append(data.getStartOfWeek()).append("\n");

                            countryNameTextView.setText(countryInfo.toString());
                        } else {
                            countryNameTextView.setText("Country not found");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Data>> call, Throwable t) {
                        countryNameTextView.setText("Error occurred: " + t.getMessage());
                    }
                });
            }
        });

        clearButton.setOnClickListener(view -> {
            countryNameTextView.setText("");
            countryCodeEditText.setText("");
        });
    }
}