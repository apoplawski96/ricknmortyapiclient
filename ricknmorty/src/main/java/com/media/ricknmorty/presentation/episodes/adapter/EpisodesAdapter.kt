package com.media.ricknmorty.presentation.episodes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.media.base.view.displayable.DisplayableItem
import com.media.base.view.displayable.DisplayableItemDiffCallback
import com.media.base.view.displayable.DisplayableItemViewType
import com.media.base.view.displayable.LoadingIndicatorViewHolder
import com.media.ricknmorty.R
import com.media.ricknmorty.model.Episode
import javax.inject.Inject

class EpisodesAdapter @Inject constructor(
        private val onVideoClicked: (Episode) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val episodes = ArrayList<DisplayableItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DisplayableItemViewType.EPISODE.typeId) {
            EpisodeViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false),
                    onVideoClicked
            )
        } else {
            LoadingIndicatorViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_loading_indicator, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EpisodeViewHolder -> holder.bind(episodes[position] as Episode)
            is LoadingIndicatorViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = episodes.size

    override fun getItemViewType(position: Int): Int = episodes[position].viewType.typeId

    fun setData(newEpisodes: List<DisplayableItem>) {
        val diffCallback = DisplayableItemDiffCallback(episodes, newEpisodes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        episodes.clear()
        episodes.addAll(newEpisodes)
        diffResult.dispatchUpdatesTo(this)
    }
}