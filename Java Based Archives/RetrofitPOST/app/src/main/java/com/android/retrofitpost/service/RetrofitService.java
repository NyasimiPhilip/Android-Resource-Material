package com.android.retrofitpost.service;

import com.android.retrofitpost.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("posts")
    Call<User> getResult(@Body User user);
}
