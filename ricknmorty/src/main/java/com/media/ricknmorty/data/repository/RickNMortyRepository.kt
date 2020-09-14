package com.media.ricknmorty.data.repository

import com.media.ricknmorty.data.api.ApiHelper
import com.media.ricknmorty.data.entity.Characters
import com.media.ricknmorty.data.entity.Episodes
import com.media.ricknmorty.model.Character
import io.reactivex.Single
import javax.inject.Inject

class RickNMortyRepository @Inject constructor(
        private val apiHelper: ApiHelper
): Repository {

    override fun getEpisodesPage(page: Int): Single<Episodes> = apiHelper.getEpisodesPage(page)

    override fun getCharactersById(idList: ArrayList<String>): Single<ArrayList<Character>> = apiHelper.getCharactersById(idList)

    override fun getAllCharacters(): Single<Characters> = apiHelper.getAllCharacters()
}