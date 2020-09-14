package com.media.ricknmorty.di

import com.media.ricknmorty.di.presentation.CharactersListModule
import com.media.ricknmorty.di.presentation.EpisodesListModule
import com.media.ricknmorty.presentation.characters.CharactersFragment
import com.media.ricknmorty.presentation.episodes.list.EpisodesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector (modules = [EpisodesListModule::class])
    abstract fun contributeEpisodesFragment(): EpisodesFragment

    @ContributesAndroidInjector (modules = [CharactersListModule::class])
    abstract fun contributeCharactersFragment(): CharactersFragment
}