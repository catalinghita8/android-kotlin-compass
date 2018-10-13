package com.inspiringteam.reactivecompass.ui.compass

import android.arch.lifecycle.ViewModel
import com.inspiringteam.reactivecompass.data.models.CompassOrientation
import com.inspiringteam.reactivecompass.data.models.GeoPosition
import com.inspiringteam.reactivecompass.data.source.CompassRepository
import com.inspiringteam.reactivecompass.di.scopes.AppScoped
import com.inspiringteam.reactivecompass.ui.compass.models.DirectionsUiModel
import com.inspiringteam.reactivecompass.ui.compass.models.LocationUiModel
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

@AppScoped
class CompassViewModel @Inject constructor(val repository: CompassRepository) : ViewModel() {


    fun getCompassUiModel(): Flowable<DirectionsUiModel> {
        return repository.orientation.map<DirectionsUiModel>{ constructCompassUiModel(it) }
    }

    fun getCurrentLocationUiModel(): Flowable<LocationUiModel> {
        return repository.locationUpdates.map<LocationUiModel>{ constructLocationUiModel(it) }
    }

    fun getDestinationLocationUiModel(): Single<LocationUiModel>{
        return repository.destinationLocation.map<LocationUiModel>{ constructLocationUiModel(it) }
    }

    fun setDestinationLocation(location: GeoPosition){
        repository.updateDestinationPosition(location)
    }

    /**
     * Internal construction methods
     */
    private fun constructCompassUiModel(compassOrientation: CompassOrientation): DirectionsUiModel {
        return DirectionsUiModel(compassOrientation)
    }

    private fun constructLocationUiModel(geoPosition: GeoPosition): LocationUiModel {
        return LocationUiModel(geoPosition)
    }
}