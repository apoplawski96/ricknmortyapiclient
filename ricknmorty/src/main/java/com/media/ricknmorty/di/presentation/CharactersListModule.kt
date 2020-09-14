package com.media.ricknmorty.di.presentation

import com.media.base.scheduler.SchedulerProvider
import com.media.ricknmorty.data.repository.Repository
import com.media.ricknmorty.presentation.characters.CharactersContract
import com.media.ricknmorty.presentation.characters.CharactersPresenter
import dagger.Module
import dagger.Provides

@Module
class CharactersListModule {

    @Provides
    fun providesCharactersPresenter(
            repository: Repository,
            schedulerProvider: SchedulerProvider
    ): CharactersContract.Presenter = CharactersPresenter(repository, schedulerProvider)
}
