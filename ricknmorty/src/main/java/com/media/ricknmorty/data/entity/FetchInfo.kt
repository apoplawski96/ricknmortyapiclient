package com.media.ricknmorty.data.entity

data class FetchInfo(
        val count: Int = -1,
        val pages: Int = -1,
        val next: String = "",
        val prev: String = ""
)