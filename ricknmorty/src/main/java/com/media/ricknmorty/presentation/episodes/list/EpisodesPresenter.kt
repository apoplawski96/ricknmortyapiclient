package com.media.ricknmorty.presentation.episodes.list

import com.media.base.logger.Logger
import com.media.base.scheduler.SchedulerProvider
import com.media.base.view.displayable.DisplayableItem
import com.media.ricknmorty.data.entity.Episodes
import com.media.ricknmorty.data.entity.FetchInfo
import com.media.ricknmorty.data.repository.Repository
import com.media.ricknmorty.model.Episode
import com.media.base.view.model.LoadingIndicator
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class EpisodesPresenter @Inject constructor(
        private val repository: Repository,
        private val schedulerProvider: SchedulerProvider,
        private val logger: Logger
) : EpisodesContract.Presenter {

    companion object {
        private const val TAG = "EpisodesPresenter"
    }

    var view: EpisodesContract.View? = null

    private var latestFetchInfo: FetchInfo? = null
    private val episodes: MutableList<Episode> = mutableListOf()
    private var pageCounter: Int = 1

    override fun viewCreated(view: EpisodesContract.View) {
        this.view = view
        setupLogger()
    }

    override fun viewDestroyed() {
        this.view = null
    }

    override fun fetchInitialEpisodes() {
        view?.showMainLoadingIndicator()
        fetchEpisodesFromRemote(
                onSuccess = ::onEpisodesPageLoaded,
                onError = {
                    view?.hideMainLoadingIndicator()
                    view?.displayError()
                }
        )
    }

    override fun fetchMoreEpisodes() {
        if (morePagesAvailable()) {
            fetchEpisodesFromRemote(
                    onSuccess = ::onEpisodesPageLoaded,
                    onError = {
                        view?.displayError()
                    }
            )
        }
    }

    override fun refreshEpisodes() {
        view?.showMainLoadingIndicator()
        fetchEpisodesFromRemote(
                onSuccess = ::onEpisodesPageLoaded,
                onError = {
                    view?.hideMainLoadingIndicator()
                    view?.displayError()
                }
        )
    }

    override fun getCurrentlyFetchedEpisodes(): ArrayList<Episode> = ArrayList(episodes)

    private fun onEpisodesPageLoaded(episodesFetch: Episodes)  {
        latestFetchInfo = episodesFetch.fetchInfo
        pageCounter ++
        episodes.addAll(episodesFetch.items)

        view?.hideMainLoadingIndicator()
        view?.displayEpisodes(assembleDisplayableList())
    }

    private fun fetchEpisodesFromRemote(
            onSuccess: (Episodes) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        repository.getEpisodesPage(pageCounter)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy (
                        onSuccess = onSuccess,
                        onError = onError
                )
    }

    private fun assembleDisplayableList(): List<DisplayableItem> {
        return if (morePagesAvailable()){
            episodes + LoadingIndicator()
        }
        else {
            episodes
        }
    }

    private fun morePagesAvailable(): Boolean = (latestFetchInfo?.next.toString() != "null")

    private fun setupLogger() {
        logger.setTag(TAG)
    }
}