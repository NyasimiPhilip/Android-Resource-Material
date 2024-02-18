package com.android.tmdb_java.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.tmdb_java.model.Movie;

public class MovieActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Movie> movieLiveData;

    public MovieActivityViewModel(@NonNull Application application) {
        super(application);
        movieLiveData = new MutableLiveData<>();
    }

    // Method to set movie details received from Intent
    public void setMovieDetails(Movie movie) {
        movieLiveData.setValue(movie);
    }

    // Method to get observed movie data
    public LiveData<Movie> getMovie() {
        return movieLiveData;
    }
}
