package com.example.firstapp;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlacesApiResponse {
    @SerializedName("results")
    private List<PlaceResult> results;

    public List<PlaceResult> getResults() {
        return results;
    }


}