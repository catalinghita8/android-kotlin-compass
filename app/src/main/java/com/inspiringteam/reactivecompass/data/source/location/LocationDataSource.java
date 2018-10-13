package com.inspiringteam.reactivecompass.data.source.location;

import com.inspiringteam.reactivecompass.data.models.GeoPosition;

import io.reactivex.Flowable;

public interface LocationDataSource {
    Flowable<GeoPosition> getLocationUpdates();
}
