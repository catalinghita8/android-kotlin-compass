package com.inspiringteam.reactivecompass.data.source.orientation;

import com.inspiringteam.reactivecompass.data.models.CompassOrientation;
import com.inspiringteam.reactivecompass.data.models.GeoPosition;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface OrientationDataSource {
    Flowable<CompassOrientation> getOrientation();

    Single<GeoPosition> getDestinationLocation();

    void updateDestinationPosition(GeoPosition destinationPosition);

    void updateCurrentLocation(GeoPosition position);
}
