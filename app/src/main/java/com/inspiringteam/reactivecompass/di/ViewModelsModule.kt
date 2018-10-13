package com.inspiringteam.reactivecompass.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.inspiringteam.reactivecompass.di.compass.CompassViewModelFactory
import com.inspiringteam.reactivecompass.di.scopes.AppScoped
import com.inspiringteam.reactivecompass.ui.compass.CompassViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(CompassViewModel::class)
    abstract fun bindCompassViewModel(compassViewModel: CompassViewModel): ViewModel

    @Binds
    @AppScoped
    abstract fun bindViewModelFactory(factory: CompassViewModelFactory): ViewModelProvider.Factory
}