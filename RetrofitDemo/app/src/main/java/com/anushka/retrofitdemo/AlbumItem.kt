package com.anushka.retrofitdemo

import com.google.gson.annotations.SerializedName

/**
 * Data class representing an item in the Album.
 * Each instance corresponds to an album retrieved from an API response.
 * @param id The unique identifier of the album item.
 * @param title The title of the album item.
 * @param userId The user ID associated with the album item.
 */
data class AlbumItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)
