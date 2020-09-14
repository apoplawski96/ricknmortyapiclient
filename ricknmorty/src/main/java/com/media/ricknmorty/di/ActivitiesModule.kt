package com.media.ricknmorty.di

import com.media.ricknmorty.di.presentation.EpisodeDetailModule
import com.media.ricknmorty.di.presentation.SplashScreenModule
import com.media.ricknmorty.presentation.MainActivity
import com.media.ricknmorty.presentation.episodes.detail.EpisodeDetailActivity
import com.media.ricknmorty.presentation.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun bindsMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(SplashScreenModule::class)])
    abstract fun bindsSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [(EpisodeDetailModule::class)])
    abstract fun bindsEpisodeDetailActivity(): EpisodeDetailActivity
}