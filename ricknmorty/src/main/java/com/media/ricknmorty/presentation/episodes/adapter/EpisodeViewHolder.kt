package com.media.ricknmorty.presentation.episodes.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.media.base.view.string.putInQuotationMarks
import com.media.ricknmorty.R
import com.media.ricknmorty.model.Episode
import kotlinx.android.extensions.LayoutContainer

class EpisodeViewHolder(
        override val containerView: View,
        private val onVideoClicked: (Episode) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(episode: Episode) {
        val episodeName: TextView = containerView.findViewById(R.id.episodeName)
        val episodeCode: TextView = containerView.findViewById(R.id.episodeCode)

        episodeName.text = episode.name?.putInQuotationMarks()
        episodeCode.text = episode.code

        itemView.setOnClickListener { onVideoClicked(episode) }
    }
}