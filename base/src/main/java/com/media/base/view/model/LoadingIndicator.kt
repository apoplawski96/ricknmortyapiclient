package com.media.base.view.model

import com.media.base.view.displayable.DisplayableItem
import com.media.base.view.displayable.DisplayableItemViewType

class LoadingIndicator : DisplayableItem {

    override val viewType: DisplayableItemViewType
        get() = DisplayableItemViewType.LOADING_INDICATOR

    override fun areItemsTheSame(other: DisplayableItem): Boolean = true
}