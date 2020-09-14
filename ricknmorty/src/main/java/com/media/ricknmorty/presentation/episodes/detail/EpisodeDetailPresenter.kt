package com.media.ricknmorty.presentation.episodes.detail

import com.media.base.logger.Logger
import com.media.base.scheduler.SchedulerProvider
import com.media.ricknmorty.data.repository.Repository
import com.media.ricknmorty.model.Character
import com.media.ricknmorty.model.Episode
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class EpisodeDetailPresenter @Inject constructor(
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider,
    private val logger: Logger
) : EpisodeDetailContract.Presenter {

    companion object {
        private const val TAG = "EpisodeDetailPresenter"
    }

    var view: EpisodeDetailContract.View? = null

    private var currentEpisode: Episode? = null
    private val episodeCharacters: MutableList<Character> = mutableListOf()

    override fun viewCreated(view: EpisodeDetailContract.View) {
        this.view = view
        setupLogger()
    }

    override fun viewDestroyed() {
        this.view = null
        this.currentEpisode = null
    }

    override fun setEpisode(episode: Episode?) {
        this.currentEpisode = episode
        view?.displayEpisodeInfo(currentEpisode)
    }

    override fun fetchEpisodeCharacters() {
        view?.showLoading()
        fetchEpisodeCharactersFromRemote(
                charactersIdList = getEpisodeCharactersIdList(),
                onSuccess = ::onEpisodeCharactersLoaded,
                onError = {
                    view?.hideLoading()
                    view?.displayError()
                }
        )
    }

    private fun getEpisodeCharactersIdList(): ArrayList<String> {
        val idList: ArrayList<String> = arrayListOf()
        currentEpisode?.characters?.forEach {
            val id = it.substringAfterLast("/")
            idList.add(id)
        }
        return idList
    }

    private fun fetchEpisodeCharactersFromRemote(
            charactersIdList: ArrayList<String>,
            onSuccess: (ArrayList<Character>) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        repository.getCharactersById(charactersIdList)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy (
                        onSuccess = onSuccess,
                        onError = onError
                )
    }

    private fun onEpisodeCharactersLoaded(characters: ArrayList<Character>) {
        episodeCharacters.addAll(characters)

        view?.hideLoading()
        view?.displayCharacters(episodeCharacters)
    }

    private fun setupLogger() {
        logger.setTag(TAG)
    }
}
