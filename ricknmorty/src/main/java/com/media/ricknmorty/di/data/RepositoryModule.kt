package com.media.ricknmorty.di.data

import com.media.ricknmorty.data.api.ApiHelper
import com.media.ricknmorty.data.api.ApiService
import com.media.ricknmorty.data.api.RickNMortyApiHelper
import com.media.ricknmorty.data.repository.Repository
import com.media.ricknmorty.data.repository.RickNMortyRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesApiHelper(apiService: ApiService): ApiHelper = RickNMortyApiHelper(apiService)

    @Provides
    fun providesRepository(apiHelper: ApiHelper): Repository = RickNMortyRepository(apiHelper)
}