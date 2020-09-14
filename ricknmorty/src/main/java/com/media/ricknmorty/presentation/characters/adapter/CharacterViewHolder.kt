package com.media.ricknmorty.presentation.characters.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.media.base.view.image.ImageLoader
import com.media.base.view.image.ImageRequest
import com.media.ricknmorty.R
import com.media.ricknmorty.model.Character
import kotlinx.android.extensions.LayoutContainer

class CharacterViewHolder(
        override val containerView: View,
        private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(character: Character) {
        val name: TextView = containerView.findViewById(R.id.characterName)
        val image: ImageView = containerView.findViewById(R.id.characterImage)
        name.text = character.name
        imageLoader.loadImage(image, ImageRequest(character.image))
    }
}