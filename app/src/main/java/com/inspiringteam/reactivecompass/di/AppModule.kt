package com.inspiringteam.reactivecompass.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    //expose Application as an injectable context
    @Binds
    internal abstract fun bindContext(application: Application): Context
}
