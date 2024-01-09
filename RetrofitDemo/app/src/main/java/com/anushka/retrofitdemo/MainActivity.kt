package com.anushka.retrofitdemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // Retrofit service instance for making API calls
    private val retService: AlbumService = RetrofitInstance
        .getRetrofitInstance()
        .create(AlbumService::class.java)

    // TextView for displaying API response in the UI
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the TextView
        textView = findViewById(R.id.textView)

        // Make API calls and observe responses
        getRequestWithQueryParameter()
        getRequestWithPathParameter()
    }

    // Function to make an API call with query parameters
    private fun getRequestWithQueryParameter() {
        // Create a LiveData to handle the response from the API call
        val responseLiveData: LiveData<Response<Album>> = liveData {
            try {
                // Make the API call and emit the response
                val response: Response<Album> = retService.getAlbums()
                emit(response)
            } catch (e: Exception) {
                // Handle any exceptions that might occur during the API call
                Log.e("MyTag", "Error: ${e.message}")
            }
        }

        // Observe the LiveData for changes in the API response
        observeAlbumResponse(responseLiveData)
    }

    // Function to make an API call with a path parameter
    private fun getRequestWithPathParameter() {
        // Create a LiveData to handle the response from the API call
        val pathResponse: LiveData<Response<AlbumItem>> = liveData {
            // Make the API call with a path parameter and emit the response
            val response: Response<AlbumItem> = retService.getAlbum(3)
            emit(response)
        }

        // Observe the LiveData for changes in the API response
        observePathResponse(pathResponse)
    }

    // Function to observe and handle the response from the API with query parameters
    private fun observeAlbumResponse(responseLiveData: LiveData<Response<Album>>) {
        responseLiveData.observe(this, Observer {
            // Check if the API response is successful
            if (it.isSuccessful) {
                // Extract the list of albums from the response body
                val albumsList = it.body()?.listIterator()
                albumsList?.forEach { albumItem ->
                    // Display album information in the TextView
                    val result: String = " " + "Album Title : ${albumItem.title}" + "\n" +
                            " " + "Album id : ${albumItem.id}" + "\n" +
                            " " + "User id : ${albumItem.userId}" + "\n" + "\n\n\n"
                    textView.append(result)
                    // Log album title to the console
                    Log.i("MyTag", albumItem.title)
                }
            } else {
                // Log an error message if the API response is not successful
                Log.e("MyTag", "API call failed with code: ${it.code()}")
            }
        })
    }

    // Function to observe and handle the response from the API with path parameters
    private fun observePathResponse(pathResponse: LiveData<Response<AlbumItem>>) {
        pathResponse.observe(this, Observer {
            // Extract the title from the response body
            val title: String? = it.body()?.title
            // Display a Toast message with the title
            Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
        })
    }
}
