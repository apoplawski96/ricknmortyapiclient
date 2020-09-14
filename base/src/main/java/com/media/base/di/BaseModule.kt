package com.media.base.di

import dagger.Module

@Module (includes = [AndroidModule::class, UtilsModule::class])
class BaseModule