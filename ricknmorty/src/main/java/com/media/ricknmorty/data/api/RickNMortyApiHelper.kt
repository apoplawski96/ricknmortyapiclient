package com.media.ricknmorty.data.api

import com.media.ricknmorty.data.entity.Characters
import com.media.ricknmorty.data.entity.Episodes
import com.media.ricknmorty.model.Character
import io.reactivex.Single
import javax.inject.Inject

class RickNMortyApiHelper @Inject constructor(
        private val apiService: ApiService
): ApiHelper{

    override fun getEpisodesPage(page: Int): Single<Episodes> = apiService.getEpisodesPage(page.toString())

    override fun getCharactersById(idList: ArrayList<String>) = apiService.getCharactersById(idList.toString())

    override fun getAllCharacters(): Single<Characters> = apiService.getAllCharacters()
}