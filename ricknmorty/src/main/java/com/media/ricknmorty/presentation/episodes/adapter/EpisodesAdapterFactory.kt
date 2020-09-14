package com.media.ricknmorty.presentation.episodes.adapter

import com.media.ricknmorty.model.Episode
import javax.inject.Inject

class EpisodesAdapterFactory @Inject constructor() {

    fun create(onEpisodeClicked: (Episode) -> Unit) = EpisodesAdapter(onEpisodeClicked)
}