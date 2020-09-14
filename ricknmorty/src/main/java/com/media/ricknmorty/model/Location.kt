package com.media.ricknmorty.model

data class Location (
        val id: Int = -1,
        val name: String = "",
        val type: String = "",
        val dimension: String = "",
        val residents: ArrayList<String> = arrayListOf(),
        val url: String = "",
        val created: String = ""
)