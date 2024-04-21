package com.example.firstapp;

import com.google.gson.annotations.SerializedName;

public class PlaceResult {
    @SerializedName("geometry")
    private Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }
}
