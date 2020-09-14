package com.media.base.view.displayable

import androidx.recyclerview.widget.DiffUtil

class DisplayableItemDiffCallback(private val oldList: List<DisplayableItem>,
                                  private val newList: List<DisplayableItem>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldIndex: Int, newIndex: Int): Boolean =
            (oldList[oldIndex] as? DisplayableItem)?.areItemsTheSame(newList[newIndex])
                    ?: areContentsTheSame(oldIndex, newIndex)

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldIndex: Int, newIndex: Int): Boolean = oldList[oldIndex] == newList[newIndex]
}