package com.inspiringteam.reactivecompass.di

import android.app.Application
import com.inspiringteam.reactivecompass.App
import com.inspiringteam.reactivecompass.data.source.CompassRepositoryModule
import com.inspiringteam.reactivecompass.di.scopes.AppScoped
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScoped
@Component(modules = [AppModule::class,
    ActivityBindingModule::class,
    CompassRepositoryModule::class,
    ViewModelsModule::class,
    AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<App> {


    // we can now do DaggerAppComponent.builder().application(this).build().inject(this),
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}