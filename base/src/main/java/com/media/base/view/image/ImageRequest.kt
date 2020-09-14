package com.media.base.view.image

import androidx.annotation.DrawableRes
import com.media.base.R

data class ImageRequest (
        val url: String,
        val placeholder: Int = R.mipmap.smutna_zaba,
        @DrawableRes val errorPlaceholder: Int = R.mipmap.smutna_zaba
)