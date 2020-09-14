package com.media.base.view.displayable

interface DisplayableItem {

    val viewType: DisplayableItemViewType

    fun areItemsTheSame(other: DisplayableItem): Boolean
}
