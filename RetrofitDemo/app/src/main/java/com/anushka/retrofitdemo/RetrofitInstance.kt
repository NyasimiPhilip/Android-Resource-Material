package com.anushka.retrofitdemo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

/**
 * Singleton class responsible for providing a Retrofit instance.
 */
class RetrofitInstance {
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        /**
         * Returns a Retrofit instance configured with the base URL and Gson converter factory.
         * @return Configured Retrofit instance.
         */
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}
