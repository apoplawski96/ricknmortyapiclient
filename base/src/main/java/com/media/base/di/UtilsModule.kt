package com.media.base.di

import com.media.base.logger.DebugLogger
import com.media.base.logger.Logger
import com.media.base.view.image.GlideImageLoader
import com.media.base.view.image.ImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun providesDebugLogger(): Logger = DebugLogger

    @Provides
    @Singleton
    fun providesImageLoader(): ImageLoader = GlideImageLoader()
}