package com.inspiringteam.reactivecompass.data.source.orientation

import android.app.Application

import com.inspiringteam.reactivecompass.ReactiveSensors.ReactiveSensors
import com.inspiringteam.reactivecompass.di.scopes.AppScoped

import dagger.Module
import dagger.Provides

@Module
class CompassOrientationSourceModule {
    @AppScoped
    @Provides
    internal fun provideReactiveSensors(context: Application): ReactiveSensors {
        return ReactiveSensors(context)
    }
}
