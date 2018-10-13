package com.inspiringteam.reactivecompass.ui.updatedestination

import com.inspiringteam.reactivecompass.di.scopes.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UpdateDestinationModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun updateDestinationFragment(): UpdateDestinationFragment
}