package com.inspiringteam.reactivecompass.ui.compass.models;

import com.inspiringteam.reactivecompass.data.models.CompassOrientation;

public class DirectionsUiModel {
    private final CompassOrientation mCompassOrientation;

    public CompassOrientation getCompassOrientation() {
        return mCompassOrientation;
    }


    public DirectionsUiModel(CompassOrientation compassOrientation) {
        this.mCompassOrientation = compassOrientation;
    }
}
