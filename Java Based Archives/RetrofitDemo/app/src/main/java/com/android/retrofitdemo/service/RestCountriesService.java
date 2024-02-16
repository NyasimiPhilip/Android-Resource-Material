package com.android.retrofitdemo.service;

import com.android.retrofitdemo.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestCountriesService {
    @GET("v3.1/all")
    Call<List<Data>> getAllCountries();

    @GET("v3.1/name/{name}")
    Call<List<Data>> getCountryByName(@Path("name") String name);

    @GET("v3.1/name/{name}")
    Call<List<Data>> getCountryByNameWithFullText(@Path("name") String name, @Query("fullText") boolean fullText);

    @GET("v3.1/alpha/{code}")
    Call<Data> getCountryByCode(@Path("code") String code);

    @GET("v3.1/alpha")
    Call<List<Data>> getCountriesByCodes(@Query("codes") String codes);

    @GET("v3.1/currency/{currency}")
    Call<List<Data>> getCountriesByCurrency(@Path("currency") String currency);

    @GET("v3.1/demonym/{demonym}")
    Call<List<Data>> getCountriesByDemonym(@Path("demonym") String demonym);

    @GET("v3.1/lang/{language}")
    Call<List<Data>> getCountriesByLanguage(@Path("language") String language);

    @GET("v3.1/capital/{capital}")
    Call<List<Data>> getCountriesByCapital(@Path("capital") String capital);

    @GET("v3.1/region/{region}")
    Call<List<Data>> getCountriesByRegion(@Path("region") String region);

    @GET("v3.1/subregion/{subregion}")
    Call<List<Data>> getCountriesBySubregion(@Path("subregion") String subregion);
}
