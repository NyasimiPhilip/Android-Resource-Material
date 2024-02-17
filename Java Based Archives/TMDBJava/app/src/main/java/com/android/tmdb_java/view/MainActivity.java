package com.android.tmdb_java.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.tmdb_java.R;
import com.android.tmdb_java.adapter.MovieAdapter;
import com.android.tmdb_java.model.Movie;
import com.android.tmdb_java.model.MovieDBResponse;
import com.android.tmdb_java.service.MovieDataService;
import com.android.tmdb_java.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout; // Added SwipeRefreshLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("TMDB Popular Movies Today");

        // Initialize SwipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        /*swipeRefreshLayout.getResources().getColor(R.color.colorPrimary);*/
        // Get the color from resources
        int colorPrimary = ContextCompat.getColor(this, R.color.colorPrimaryDark);

        // Set the color to the SwipeRefreshLayout
        swipeRefreshLayout.setColorSchemeColors(colorPrimary);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Handle refresh action
                getPopularMovies();
            }
        });

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.rvMovies);
        recyclerView.setLayoutManager(new GridLayoutManager(this, calculateSpanCount()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Fetch popular movies
        getPopularMovies();
    }

    private int calculateSpanCount() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 2 : 4;
    }

    private void getPopularMovies() {
        MovieDataService movieDataService = RetrofitInstance.getService();
        Call<MovieDBResponse> call = movieDataService.getPopularMovies(getString(R.string.api_key));

        // Show loading indicator
        Toast.makeText(MainActivity.this, "Fetching popular movies...", Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                if (response.isSuccessful()) {
                    MovieDBResponse movieDBResponse = response.body();
                    if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                        movies = new ArrayList<>(movieDBResponse.getMovies());
                        showMovies();
                        // Show success message
                        Toast.makeText(MainActivity.this, "Popular movies loaded successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Show empty state
                        Toast.makeText(MainActivity.this, "No movies found!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Show error message
                    Toast.makeText(MainActivity.this, "Failed to fetch popular movies. Please try again later.", Toast.LENGTH_SHORT).show();
                }

                // Log the movie data
                Log.d("wapi", "Movie Data: " + movies);

                // Stop the SwipeRefreshLayout's refreshing animation
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
                // Show error message
                Toast.makeText(MainActivity.this, "Failed to fetch popular movies. Please check your internet connection.", Toast.LENGTH_SHORT).show();

                // Stop the SwipeRefreshLayout's refreshing animation
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void showMovies() {
        MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}
