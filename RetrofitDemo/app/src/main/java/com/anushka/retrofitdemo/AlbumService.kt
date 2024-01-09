package com.anushka.retrofitdemo

import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit service interface for handling album-related API requests.
 */
interface AlbumService {

    /**
     * Represents a GET request to retrieve a list of albums from a specified endpoint ("/albums").
     * The function is annotated with @GET, indicating the HTTP method used.
     * The suspend modifier is used to perform this operation asynchronously.
     * @return A Retrofit Response containing a list of Album items.
     */
    @GET("/albums")
    suspend fun getAlbums(): Response<Album>
}
