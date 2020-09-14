package com.media.ricknmorty.data.api

import com.media.ricknmorty.data.entity.Characters
import com.media.ricknmorty.data.entity.Episodes
import com.media.ricknmorty.model.Character
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("episode")
    fun getEpisodesPage(@Query("page") page: String): Single<Episodes>

    @GET("character/{id}")
    fun getCharactersById(@Path("id") idsArray: String): Single<ArrayList<Character>>

    @GET("character/")
    fun getAllCharacters(): Single<Characters>
}