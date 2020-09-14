package com.media.ricknmorty.data.repository

import com.media.ricknmorty.data.entity.Characters
import com.media.ricknmorty.data.entity.Episodes
import com.media.ricknmorty.model.Character
import io.reactivex.Single

interface Repository {

    fun getEpisodesPage(page: Int): Single<Episodes>

    fun getCharactersById(idList: ArrayList<String>): Single<ArrayList<Character>>

    fun getAllCharacters(): Single<Characters>
}