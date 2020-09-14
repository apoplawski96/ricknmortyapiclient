package com.media.base.view.image

import android.widget.ImageView

interface ImageLoader {

    fun loadImage(imageView: ImageView, imageRequest: ImageRequest)
}