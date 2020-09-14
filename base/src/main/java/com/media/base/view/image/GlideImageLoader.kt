package com.media.base.view.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.media.base.R
import javax.inject.Singleton

@Singleton
class GlideImageLoader : ImageLoader {

    override fun loadImage(imageView: ImageView, imageRequest: ImageRequest) {
        if (imageRequest.url.isEmpty()) {
            errorImageRequest(imageView.context)
        } else {
            imageRequest(imageView.context, imageRequest.url)
        }?.applyToImageView(imageView)
    }

    private fun RequestBuilder<Drawable>.applyToImageView(imageView: ImageView) =
            centerCrop()
                    .dontAnimate()
                    .into(imageView)

    private fun imageRequest(context: Context, imageUrl: String): RequestBuilder<Drawable>? =
            Glide.with(context)
                    .load(imageUrl)
                    .error(R.mipmap.smutna_zaba)

    private fun errorImageRequest(context: Context): RequestBuilder<Drawable>? =
            Glide.with(context)
                    .load(R.mipmap.smutna_zaba)
}