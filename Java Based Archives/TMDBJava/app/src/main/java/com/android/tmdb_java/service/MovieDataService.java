package com.android.tmdb_java.service;

import com.android.tmdb_java.model.MovieDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    // Define the method for retrieving popular movies
    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String apiKey);
}
