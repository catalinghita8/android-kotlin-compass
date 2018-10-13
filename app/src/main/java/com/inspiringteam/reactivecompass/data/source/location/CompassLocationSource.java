package com.inspiringteam.reactivecompass.data.source.location;

import com.google.android.gms.location.LocationRequest;
import com.inspiringteam.reactivecompass.data.models.GeoPosition;
import com.inspiringteam.reactivecompass.di.scopes.AppScoped;
import com.patloew.rxlocation.RxLocation;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@AppScoped
public class CompassLocationSource implements LocationDataSource {
    private RxLocation mRxLocationProvider;

    @Inject
    public CompassLocationSource(RxLocation rxLocationProvider) {
        this.mRxLocationProvider = rxLocationProvider;
    }

    @Override
    public Flowable<GeoPosition> getLocationUpdates() {
        return mRxLocationProvider.location().updates(getCustomLocationRequest())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(position -> Observable.just(new GeoPosition(position.getLatitude(), position.getLongitude())))
                .toFlowable(BackpressureStrategy.LATEST);
    }

    private LocationRequest getCustomLocationRequest(){
        LocationRequest locationRequest = new LocationRequest();

        // 10m is more than enough to recalibrate the direction
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setSmallestDisplacement(10);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        return locationRequest;
    }
}
