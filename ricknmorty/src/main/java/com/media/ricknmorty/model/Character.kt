package com.media.ricknmorty.model

import com.media.base.view.displayable.DisplayableItem
import com.media.base.view.displayable.DisplayableItemViewType

data class Character(
        val id: Int = -1,
        val name: String = "",
        val status: String = "",
        val species: String = "",
        val type: String = "",
        val gender: String = "",
        val origin: Location = Location(),
        val location: Location = Location(),
        val image: String = "",
        val episode: ArrayList<String> = arrayListOf(),
        val url: String = "",
        val created: String = ""
) : DisplayableItem {

    override val viewType: DisplayableItemViewType
        get() = DisplayableItemViewType.EPISODE_CHARACTER

    override fun areItemsTheSame(other: DisplayableItem): Boolean = (other as? Character)?.id == id
}