package com.media.base.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.media.base.scheduler.AndroidSchedulerProvider
import com.media.base.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    internal fun provideResources(application: Application): Resources = application.resources

    @Provides
    @Singleton
    internal fun providesAndroidScheduler(): SchedulerProvider = AndroidSchedulerProvider()
}