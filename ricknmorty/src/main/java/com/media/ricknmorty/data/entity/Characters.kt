package com.media.ricknmorty.data.entity

import com.google.gson.annotations.SerializedName
import com.media.ricknmorty.model.Character

data class Characters(
        @SerializedName("info")
        val feedInfo: FetchInfo? = null,
        @SerializedName("results")
        val items: List<Character> = emptyList()
)