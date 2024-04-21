package com.example.firstapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlacesApiClient {
    private static final String BASE_URL = "https://maps.googleapis.com/";
    private static PlacesApiService apiService;

    public static PlacesApiService getApiService() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(PlacesApiService.class);
        }
        return apiService;
    }
}