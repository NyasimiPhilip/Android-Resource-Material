package com.anushka.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        // Obtain the Retrofit service instance
        val retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

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
        responseLiveData.observe(this, Observer {
            // Check if the API response is successful
            if (it.isSuccessful) {
                // Extract the list of albums from the response body
                val albumsList = it.body()?.listIterator()
                if (albumsList != null) {
                    // Iterate over the list and log each album's title
                    while (albumsList.hasNext()) {
                        val albumItem = albumsList.next()
                        val result : String = " "+"Album Title : ${albumItem.title}" + "\n"+
                                               " "+"Album id : ${albumItem.id}" + "\n"+
                                                " "+"User id : ${albumItem.userId}" + "\n"+"\n\n\n"
                        textView.append(result)
                        Log.i("MyTag", albumItem.title)
                    }
                }
            } else {
                // Log an error message if the API response is not successful
                Log.e("MyTag", "API call failed with code: ${it.code()}")
            }
        })
    }
}
