package com.example.firstapp;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlacesApiService {
    @GET("maps/api/place/nearbysearch/json")
    Call<PlacesApiResponse> getNearbyHospitals(
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("type") String type,
            @Query("key") String apiKey
    );
}




