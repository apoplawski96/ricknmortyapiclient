package com.media.ricknmorty.episodes

import com.media.base.logger.Logger
import com.media.ricknmorty.data.repository.Repository
import com.media.ricknmorty.model.Character
import com.media.ricknmorty.model.Episode
import com.media.ricknmorty.presentation.episodes.list.EpisodesContract
import com.media.ricknmorty.presentation.episodes.list.EpisodesPresenter
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class EpisodesPresenterTest {

    companion object {
        private val episodesResponse = Episodes(
                FetchInfo(),
                listOf(
                        Episode()
                )
        )
    }

    private lateinit var systemUnderTest: EpisodesPresenter
    private val view: EpisodesContract.View = mock()
    private val repository: Repository = mock()
    private val logger: Logger = mock()

    @Before
    fun setup() {
        systemUnderTest = EpisodesPresenter(repository, logger)
        systemUnderTest.viewCreated(view)
    }
}