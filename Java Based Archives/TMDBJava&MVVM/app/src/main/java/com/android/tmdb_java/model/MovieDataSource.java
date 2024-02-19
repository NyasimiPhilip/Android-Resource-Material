package com.android.tmdb_java.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.android.tmdb_java.R;
import com.android.tmdb_java.service.MovieDataService;
import com.android.tmdb_java.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long,Movie> {
    private MovieDataService movieDataService;
    private Application application;

    // Constructor initializing MovieDataService and Application
    public MovieDataSource(MovieDataService movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;
    }


    @Override
    // Method to load the initial data
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Movie> callback) {

        // Getting an instance of MovieDataService
        movieDataService = RetrofitInstance.getService();

        // Creating a call to get popular movies with paging
        Call<MovieDBResponse> call = movieDataService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key),1);

        // Enqueueing the call to execute asynchronously
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            // Handling the response
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {

                // Getting the response body
                MovieDBResponse movieDBResponse=response.body();
                ArrayList<Movie> movies = new ArrayList<>();

                // Checking if the response and movies are not null
                if(movieDBResponse != null && movieDBResponse.getMovies() != null) {
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();

                    // Calling onResult to pass the data to the UI
                    callback.onResult(movies, null, (long) 2);
                }
            }

            @Override
            // Handling the failure
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

    }

    @Override
    // Method to load data before the current position
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    // Method to load data after the current position
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {

        // Getting an instance of MovieDataService
        movieDataService = RetrofitInstance.getService();

        // Creating a call to get popular movies with paging
        Call<MovieDBResponse> call = movieDataService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key),params.key);

        // Enqueueing the call to execute asynchronously
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            // Handling the response
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {

                // Getting the response body
                MovieDBResponse movieDBResponse=response.body();
                ArrayList<Movie> movies = new ArrayList<>();

                // Checking if the response and movies are not null
                if(movieDBResponse != null && movieDBResponse.getMovies() != null) {
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();

                    // Calling onResult to pass the data to the UI
                    callback.onResult(movies, params.key + 1);

                }
            }

            @Override
            // Handling the failure
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

    }
}
