package com.inspiringteam.reactivecompass.ui.compass.models;

import com.inspiringteam.reactivecompass.data.models.GeoPosition;

public class LocationUiModel {
    private final GeoPosition mLocation;

    public GeoPosition getLocation() {
        return mLocation;
    }

    public LocationUiModel(GeoPosition mLocation) {
        this.mLocation = mLocation;
    }
}
