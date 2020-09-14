package com.media.ricknmorty.presentation.episodes.list

import com.media.base.mvp.ViewCreationListener
import com.media.base.mvp.ViewLifecycleListener
import com.media.base.view.displayable.DisplayableItem
import com.media.ricknmorty.model.Episode

interface EpisodesContract {

    interface View {

        fun showMainLoadingIndicator()

        fun hideMainLoadingIndicator()

        fun displayError()

        fun displayEpisodes(episodes: List<DisplayableItem>)
    }

    interface Presenter : ViewLifecycleListener, ViewCreationListener<View> {

        fun fetchInitialEpisodes()

        fun fetchMoreEpisodes()

        fun refreshEpisodes()

        fun getCurrentlyFetchedEpisodes(): ArrayList<Episode>
    }
}