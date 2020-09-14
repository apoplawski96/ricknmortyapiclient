package com.media.ricknmorty.data.entity

import com.google.gson.annotations.SerializedName
import com.media.ricknmorty.model.Episode

data class Episodes(
        @SerializedName("info")
        val fetchInfo: FetchInfo? = null,
        @SerializedName("results")
        val items: List<Episode> = emptyList()
)

