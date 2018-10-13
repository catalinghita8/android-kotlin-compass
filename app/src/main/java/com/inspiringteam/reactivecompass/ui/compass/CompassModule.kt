package com.inspiringteam.reactivecompass.ui.compass

import com.inspiringteam.reactivecompass.di.scopes.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CompassModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun compassFragment(): CompassFragment
}