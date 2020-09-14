package com.media.ricknmorty.presentation.episodes.detail

import com.media.base.mvp.ViewCreationListener
import com.media.base.mvp.ViewLifecycleListener
import com.media.base.view.displayable.DisplayableItem
import com.media.ricknmorty.model.Episode

interface EpisodeDetailContract {

    interface View {

        fun showLoading()

        fun hideLoading()

        fun displayError()

        fun displayCharacters(characters: List<DisplayableItem>)

        fun displayEpisodeInfo(episode: Episode?)
    }

    interface Presenter : ViewLifecycleListener, ViewCreationListener<View> {

        fun setEpisode(episode: Episode?)

        fun fetchEpisodeCharacters()
    }
}