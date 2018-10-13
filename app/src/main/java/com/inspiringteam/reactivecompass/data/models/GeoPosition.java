package com.inspiringteam.reactivecompass.data.models;

public final class GeoPosition {
    private double latitude;

    private double longtitude;


    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public GeoPosition() {
    }

    public GeoPosition(double latitude, double longtitude) {

        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
