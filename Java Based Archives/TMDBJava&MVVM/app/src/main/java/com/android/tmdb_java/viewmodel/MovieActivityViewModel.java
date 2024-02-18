package com.android.tmdb_java.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.tmdb_java.model.Movie;

public class MovieActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Movie> movieLiveData;

    // Constructor to initialize the ViewModel
    public MovieActivityViewModel(@NonNull Application application) {
        super(application);
        // Initialize the MutableLiveData for movie data
        movieLiveData = new MutableLiveData<>();
    }

    // Method to set movie details received from Intent
    public void setMovieDetails(Movie movie) {
        // Set the movie details in the MutableLiveData
        movieLiveData.setValue(movie);
    }

    // Method to get observed movie data
    public LiveData<Movie> getMovie() {
        // Return the LiveData containing the movie data
        return movieLiveData;
    }
}
