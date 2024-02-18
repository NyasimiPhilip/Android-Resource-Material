package com.android.tmdb_java.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.tmdb_java.R;
import com.android.tmdb_java.adapter.MovieAdapter;
import com.android.tmdb_java.databinding.ActivityMainBinding;
import com.android.tmdb_java.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel mainActivityViewModel;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout using data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Set up the action bar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("TMDB Popular Movies Today");

        // Initialize ViewModel
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Initialize SwipeRefreshLayout
        SwipeRefreshLayout swipeRefreshLayout = binding.swipeRefreshLayout;
        // Get the color from resources
        int colorPrimary = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        // Set the color to the SwipeRefreshLayout
        swipeRefreshLayout.setColorSchemeColors(colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this::getPopularMovies);

        // Initialize RecyclerView
        RecyclerView recyclerView = binding.rvMovies;
        recyclerView.setLayoutManager(new GridLayoutManager(this, calculateSpanCount()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Initialize MovieAdapter with an empty list
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);

        // Fetch popular movies
        getPopularMovies();
    }

    // Method to fetch popular movies
    private void getPopularMovies() {
        mainActivityViewModel.getAllMovies().observe(this, movies -> {
            if (movies != null) {
                movieAdapter.updateMovies(movies); // Update RecyclerView with new movie list
            } else {
                Toast.makeText(MainActivity.this, "Failed to fetch movies", Toast.LENGTH_SHORT).show();
            }
            binding.swipeRefreshLayout.setRefreshing(false); // Stop the SwipeRefreshLayout loading indicator
        });
    }

    // Method to calculate the span count based on the screen orientation
    private int calculateSpanCount() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 2 : 4;
    }
}
