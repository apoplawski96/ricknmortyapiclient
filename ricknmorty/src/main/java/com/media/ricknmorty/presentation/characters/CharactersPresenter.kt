package com.media.ricknmorty.presentation.characters

import com.media.base.scheduler.SchedulerProvider
import com.media.ricknmorty.data.entity.Characters
import com.media.ricknmorty.data.repository.Repository
import com.media.ricknmorty.model.Character
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CharactersPresenter @Inject constructor (
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider
) : CharactersContract.Presenter {

    companion object {
        private const val TAG = "CharactersPresenter"
    }

    var view: CharactersContract.View? = null

    private val characters: MutableList<Character> = mutableListOf()

    override fun viewCreated(view: CharactersContract.View) {
        this.view = view
    }

    override fun viewDestroyed() {
        this.view = null
    }

    override fun fetchCharacters() {
        view?.showLoadingIndicator()
        fetchCharactersFromRemote(
                onSuccess = ::onCharactersLoaded,
                onError = {
                    view?.hideLoadingIndicator()
                    view?.displayError()
                }
        )
    }

    override fun refreshCharacters() {
        fetchCharactersFromRemote(
                onSuccess = ::onCharactersLoaded,
                onError = {
                    view?.hideLoadingIndicator()
                    view?.displayError()
                }
        )
    }

    private fun onCharactersLoaded(charactersFetch: Characters) {
        characters.addAll(charactersFetch.items)

        view?.hideLoadingIndicator()
        view?.displayCharacters(characters)
    }

    private fun fetchCharactersFromRemote(
            onSuccess: (Characters) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        repository.getAllCharacters()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy (
                        onSuccess = onSuccess,
                        onError = onError
                )
    }
}