package com.media.ricknmorty.di.presentation

import com.media.base.navigator.ActivityNavigator
import com.media.ricknmorty.di.presentation.navigator.MainActivityNavigator
import dagger.Module
import dagger.Provides

@Module
class SplashScreenModule {

    @Provides
    fun providesNavigator(): ActivityNavigator = MainActivityNavigator()
}
