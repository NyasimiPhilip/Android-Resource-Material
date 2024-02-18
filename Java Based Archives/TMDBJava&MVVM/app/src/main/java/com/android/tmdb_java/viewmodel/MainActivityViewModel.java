package com.android.tmdb_java.viewmodel;

import android.app.Application;

import com.android.tmdb_java.model.Movie;
import com.android.tmdb_java.model.MovieRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        // Initialize the movie repository
        movieRepository = new MovieRepository(application);
    }

    // Method to get all movies from the repository
    public LiveData<List<Movie>> getAllMovies() {
        // Return the LiveData containing the list of movies
        return movieRepository.getMutableLiveData();
    }
}
