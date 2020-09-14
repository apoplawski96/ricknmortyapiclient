package com.media.ricknmorty.di.presentation

import com.media.base.logger.Logger
import com.media.base.scheduler.SchedulerProvider
import com.media.ricknmorty.data.repository.Repository
import com.media.ricknmorty.presentation.episodes.detail.EpisodeDetailContract
import com.media.ricknmorty.presentation.episodes.detail.EpisodeDetailPresenter
import dagger.Module
import dagger.Provides

@Module
class EpisodeDetailModule {

    @Provides
    fun providesEpisodeDetailPresenter(
            repository: Repository,
            schedulerProvider: SchedulerProvider,
            logger: Logger
    ): EpisodeDetailContract.Presenter = EpisodeDetailPresenter(repository, schedulerProvider, logger)
}
