package com.media.ricknmorty.presentation.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.media.base.view.displayable.DisplayableItem
import com.media.base.view.displayable.DisplayableItemDiffCallback
import com.media.base.view.image.ImageLoader
import com.media.ricknmorty.R
import com.media.ricknmorty.model.Character
import javax.inject.Inject

class CharactersAdapter @Inject constructor(
        private val imageLoader: ImageLoader
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val characters = ArrayList<DisplayableItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder (
                view,
                imageLoader
        )
    }


    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CharacterViewHolder).bind(characters[position] as Character)
    }

    override fun getItemViewType(position: Int): Int = characters[position].viewType.typeId

    fun setData(newCharacters: List<DisplayableItem>) {
        val diffCallback = DisplayableItemDiffCallback(characters, newCharacters)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        characters.clear()
        characters.addAll(newCharacters)
        diffResult.dispatchUpdatesTo(this)
    }
}