package com.android.tmdb_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.android.tmdb_java.R;
import com.android.tmdb_java.databinding.ActivityMovieBinding;
import com.android.tmdb_java.model.Movie;
import com.android.tmdb_java.viewmodel.MovieActivityViewModel;
import com.bumptech.glide.Glide;

public class MovieActivity extends AppCompatActivity {

    private ActivityMovieBinding binding;
    private MovieActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using data binding
        binding = ActivityMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up the toolbar
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Instantiate the ViewModel
        viewModel = new ViewModelProvider(this).get(MovieActivityViewModel.class);

        // Retrieve movie details from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("movie")) {
            // Get movie object from intent
            Movie movie = intent.getParcelableExtra("movie");
            if (movie != null) {
                // Set movie details in ViewModel
                viewModel.setMovieDetails(movie);
                // Bind movie object to layout
                binding.setMovie(movie);
            }
        }

        // Observe changes in the movie data
        viewModel.getMovie().observe(this, movie -> {
            if (movie != null) {
                // Load movie image using Glide
                String image = movie.getPosterPath();
                String path = "https://image.tmdb.org/t/p/w500" + image;
                Glide.with(this)
                        .load(path)
                        .placeholder(R.drawable.loading)
                        .into(binding.ivMovieLarge);

                // Set activity title
                getSupportActionBar().setTitle(movie.getTitle());
            }
        });
    }
}
