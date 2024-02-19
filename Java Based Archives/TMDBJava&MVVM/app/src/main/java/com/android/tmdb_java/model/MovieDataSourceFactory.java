package com.android.tmdb_java.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.android.tmdb_java.service.MovieDataService;

// DataSource.Factory is a generic class, but it's used without specifying the type.
// This means it can produce any type of data source, but in this case, it's intended for MovieDataSource.
public class MovieDataSourceFactory extends DataSource.Factory {

    // Instance variables
    private MovieDataSource movieDataSource; // Instance of the data source responsible for loading movie data
    private MovieDataService movieDataService; // Service for fetching movie data
    private Application application; // Application context
    private MutableLiveData<MovieDataSource> mutableLiveData; // LiveData to observe changes in the data source

    // Constructor
    public MovieDataSourceFactory(MovieDataService movieDataService, Application application) {
        this.movieDataService = movieDataService; // Initialize movie data service
        this.application = application; // Initialize application context
        mutableLiveData = new MutableLiveData<>(); // Initialize LiveData
    }

    // This method is called when a new data source needs to be created.
    @Override
    public DataSource create() {
        // Create a new instance of MovieDataSource
        movieDataSource = new MovieDataSource(movieDataService, application);

        // Post the new data source to the LiveData for observation
        mutableLiveData.postValue(movieDataSource);

        // Return the newly created data source
        return movieDataSource;
    }

    // Getter method to retrieve the LiveData containing the MovieDataSource
    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
