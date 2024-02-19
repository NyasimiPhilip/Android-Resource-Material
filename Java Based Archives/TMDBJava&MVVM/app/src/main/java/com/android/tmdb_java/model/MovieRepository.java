package com.android.tmdb_java.model;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.tmdb_java.R;
import com.android.tmdb_java.service.MovieDataService;
import com.android.tmdb_java.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// MovieRepository class acts as an intermediary between the ViewModel and the data source (API)
public class MovieRepository {
    // Instance variables
    private ArrayList<Movie> movies = new ArrayList<>(); // List to hold retrieved movies
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>(); // LiveData to observe changes in the list of movies
    private Context context; // Application context

    // Constructor
    public MovieRepository(Context context) {
        this.context = context; // Initialize application context
    }

    // Method to retrieve LiveData containing list of movies
    public MutableLiveData<List<Movie>> getMutableLiveData() {
        // Initialize MovieDataService using RetrofitInstance
        MovieDataService movieDataService = RetrofitInstance.getService();

        // Make API call to retrieve popular movies
        Call<MovieDBResponse> call = movieDataService.getPopularMovies(context.getString(R.string.api_key));
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                if (response.isSuccessful()) {
                    MovieDBResponse movieDBResponse = response.body();
                    if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                        movies = new ArrayList<>(movieDBResponse.getMovies());
                        showMovies(); // Update LiveData with retrieved movies
                        // Show success message
                        Toast.makeText(context, "Popular movies loaded successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Show empty state
                        Toast.makeText(context, "No movies found!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Show error message
                    Toast.makeText(context, "Failed to fetch popular movies. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
                // Show error message
                Toast.makeText(context, "Failed to fetch popular movies. Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });
        return mutableLiveData; // Return LiveData containing list of movies
    }

    // Method to update LiveData with retrieved movies
    private void showMovies() {
        mutableLiveData.setValue(movies); // Update LiveData with list of movies
    }
}
