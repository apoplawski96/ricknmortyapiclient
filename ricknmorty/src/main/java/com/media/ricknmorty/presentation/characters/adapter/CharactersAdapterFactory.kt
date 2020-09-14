package com.media.ricknmorty.presentation.characters.adapter

import com.media.base.view.image.ImageLoader
import javax.inject.Inject

class CharactersAdapterFactory @Inject constructor(
    private val imageLoader: ImageLoader
) {

    fun create() = CharactersAdapter(imageLoader)
}