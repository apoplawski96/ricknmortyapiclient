package com.media.base.view.decoration

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewTopMargin(
        @DimenRes val marginTop: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val resources: Resources = view.resources
        val spacingSize = resources.getDimensionPixelSize(marginTop)

        if (parent.getChildLayoutPosition(view) == 0) outRect.top = spacingSize
    }
}