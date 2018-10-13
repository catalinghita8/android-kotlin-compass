package com.inspiringteam.reactivecompass.data.source

import com.inspiringteam.reactivecompass.ReactiveSensors.ReactiveSensors
import com.inspiringteam.reactivecompass.data.source.location.CompassLocationSource
import com.inspiringteam.reactivecompass.data.source.location.CompassLocationSourceModule
import com.inspiringteam.reactivecompass.data.source.location.LocationDataSource
import com.inspiringteam.reactivecompass.data.source.orientation.CompassOrientationSource
import com.inspiringteam.reactivecompass.data.source.orientation.CompassOrientationSourceModule
import com.inspiringteam.reactivecompass.data.source.orientation.OrientationDataSource
import com.inspiringteam.reactivecompass.di.scopes.AppScoped
import com.patloew.rxlocation.RxLocation

import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(CompassOrientationSourceModule::class, CompassLocationSourceModule::class))
class CompassRepositoryModule {
    @Provides
    @AppScoped
    internal fun provideCompassOrientationSource(reactiveSensors: ReactiveSensors): OrientationDataSource {
        return CompassOrientationSource(reactiveSensors)
    }

    @Provides
    @AppScoped
    internal fun provideCompassLocationSource(rxLocation: RxLocation): LocationDataSource {
        return CompassLocationSource(rxLocation)
    }
}