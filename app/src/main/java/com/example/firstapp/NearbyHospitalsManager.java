package com.example.firstapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyHospitalsManager {
    private static final int RADIUS = 5000; // 5 kilometers
//    private static final String TYPE_HOSPITAL = "hospital";
    Map<Double, Double> pairMap = new HashMap<>();
    public Map<Double,Double> findNearbyHospitals(double latitude, double longitude, String apiKey, GoogleMap mMap, Context context,String TYPE_HOSPITAL) {

        String location = latitude + "," + longitude;
        PlacesApiService apiService = PlacesApiClient.getApiService();
        Call<PlacesApiResponse> call = apiService.getNearbyHospitals(location, RADIUS, TYPE_HOSPITAL, apiKey);

        call.enqueue(new Callback<PlacesApiResponse>() {
            @Override
            public void onResponse(Call<PlacesApiResponse> call, Response<PlacesApiResponse> response) {
                if (response.isSuccessful()) {
                    PlacesApiResponse placesApiResponse = response.body();
                    if (placesApiResponse != null && placesApiResponse.getResults() != null) {
                        for (PlaceResult place : placesApiResponse.getResults()) {
                            double placeLat = place.getGeometry().getLocation().getLat();
                            double placeLng = place.getGeometry().getLocation().getLng();
                            // Now you have the latitude and longitude of each nearby hospital

                            LatLng hospitalLocation = new LatLng(placeLat, placeLng);
                            Geocoder gcd = new Geocoder( context, Locale.getDefault());



                            List<Address> addresses = null;
                            try {
                                addresses = gcd.getFromLocation(placeLat, placeLng, 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (addresses != null && addresses.size() > 0) {

                                Log.d("Jiya",addresses.toString());

                                String locality = addresses.get(0).getAddressLine(0);

                                if(TYPE_HOSPITAL.equals("hospital")){
                                    mMap.addMarker(new MarkerOptions().position(hospitalLocation).title(locality).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                                }
                                else{
                                    mMap.addMarker(new MarkerOptions().position(hospitalLocation).title(locality).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                                }


                            }
                            pairMap.put(placeLat,placeLng);
                            Log.d("NearbyHospital", "Lat: " + placeLat + ", Lng: " + placeLng);
                        }
                    }
                } else {
                    Log.e("NearbyHospitals", "Error: " + response.message());
                }
            }



            @Override
            public void onFailure(Call<PlacesApiResponse> call, Throwable t) {
                Log.e("NearbyHospitals", "Failed to fetch nearby hospitals: " + t.getMessage());
            }
        });
        return pairMap;
    }

}


















