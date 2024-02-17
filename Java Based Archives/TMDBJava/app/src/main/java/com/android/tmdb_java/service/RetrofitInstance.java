package com.android.tmdb_java.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    // Method to get Retrofit service instance
    public static MovieDataService getService() {
        // If Retrofit instance is null, create new instance
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        // Return MovieDataService interface implementation
        return retrofit.create(MovieDataService.class);
    }
}
