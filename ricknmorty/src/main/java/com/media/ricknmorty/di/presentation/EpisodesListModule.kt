package com.media.ricknmorty.di.presentation

import com.media.base.logger.Logger
import com.media.base.navigator.ActivityNavigator
import com.media.base.scheduler.SchedulerProvider
import com.media.ricknmorty.data.repository.Repository
import com.media.ricknmorty.di.presentation.navigator.EpisodeDetailActivityNavigator
import com.media.ricknmorty.presentation.episodes.list.EpisodesContract
import com.media.ricknmorty.presentation.episodes.list.EpisodesPresenter
import dagger.Module
import dagger.Provides

@Module
class EpisodesListModule {

    @Provides
    fun providesEpisodesPresenter(
            repository: Repository,
            schedulerProvider: SchedulerProvider,
            logger: Logger
    ): EpisodesContract.Presenter = EpisodesPresenter(repository, schedulerProvider, logger)

    @Provides
    fun providesEpisodeDetailActivityNavigator(): ActivityNavigator = EpisodeDetailActivityNavigator()
}