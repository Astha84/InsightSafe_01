package com.example.firstapp;

public class ReportInfo {
    private double latitude;
    private double longitude;
    private long time;
    private boolean anonymous;
    private String crime;
    private String details;
    private String userId;
    public ReportInfo(){

    }
    public void setLatitude(double latitude){
        this.latitude=latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setTime(long time) {
        this.time = time;
    }
    public void setAnonymous(boolean anonymous){
        this.anonymous=anonymous;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getTime() {
        return time;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public String getCrime() {
        return crime;
    }

    public String getDetails() {
        return details;
    }

    public String getUserId() {
        return userId;
    }


}
