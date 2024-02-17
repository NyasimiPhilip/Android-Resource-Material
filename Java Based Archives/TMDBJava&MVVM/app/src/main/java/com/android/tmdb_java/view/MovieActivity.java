package com.android.tmdb_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.tmdb_java.R;
import com.android.tmdb_java.model.Movie;
import com.bumptech.glide.Glide;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;
    private ImageView movieImage;
    private TextView movieTitle, movieSynopsis, movieRating, movieReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        movieImage = findViewById(R.id.ivMovieLarge);
        movieTitle = findViewById(R.id.tvMovieTitle);
        movieSynopsis = findViewById(R.id.tvPlotsynopsis);
        movieRating = findViewById(R.id.tvMovieRating);
        movieReleaseDate = findViewById(R.id.tvReleaseDate);

        // Retrieve movie details from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("movie")) {
            movie = intent.getParcelableExtra("movie");
            if (movie != null) {
                // Display movie title in a toast
                Toast.makeText(getApplicationContext(), movie.getOriginalTitle(), Toast.LENGTH_LONG).show();

                // Load movie image using Glide
                String image = movie.getPosterPath();
                String path = "https://image.tmdb.org/t/p/w500" + image;
                Glide.with(this)
                        .load(path)
                        .placeholder(R.drawable.loading)
                        .into(movieImage);

                // Set activity title
                getSupportActionBar().setTitle(movie.getTitle());

                // Populate movie details
                movieTitle.setText(movie.getTitle());
                movieSynopsis.setText(movie.getOverview());
                movieRating.setText(String.valueOf(movie.getVoteAverage()));
                movieReleaseDate.setText(movie.getReleaseDate());
            }
        }
    }
}
