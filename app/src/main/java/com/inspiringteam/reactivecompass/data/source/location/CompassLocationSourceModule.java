package com.inspiringteam.reactivecompass.data.source.location;

import android.app.Application;

import com.inspiringteam.reactivecompass.di.scopes.AppScoped;
import com.patloew.rxlocation.RxLocation;

import dagger.Module;
import dagger.Provides;

@Module
public class CompassLocationSourceModule {
    @AppScoped
    @Provides
    RxLocation provideRxLocation(Application context){
        return new RxLocation(context);
    }
}
