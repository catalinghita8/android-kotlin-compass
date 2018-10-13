package com.inspiringteam.reactivecompass.di

import com.inspiringteam.reactivecompass.di.scopes.ActivityScoped
import com.inspiringteam.reactivecompass.ui.compass.CompassActivity
import com.inspiringteam.reactivecompass.ui.compass.CompassModule
import com.inspiringteam.reactivecompass.ui.updatedestination.UpdateDestinationActivity
import com.inspiringteam.reactivecompass.ui.updatedestination.UpdateDestinationModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [CompassModule::class])
    internal abstract fun compassActivity(): CompassActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [UpdateDestinationModule::class])
    internal abstract fun updateDestinationActivity(): UpdateDestinationActivity
}
