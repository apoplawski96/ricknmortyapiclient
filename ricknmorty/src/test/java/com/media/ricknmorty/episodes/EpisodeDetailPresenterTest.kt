package com.media.ricknmorty.episodes

import com.media.base.logger.Logger
import com.media.ricknmorty.data.repository.Repository
import com.media.ricknmorty.model.Character
import com.media.ricknmorty.model.Episode
import com.media.ricknmorty.presentation.episodes.detail.EpisodeDetailContract
import com.media.ricknmorty.presentation.episodes.detail.EpisodeDetailPresenter
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class EpisodeDetailPresenterTest {

    companion object {

        private val episode = Episode(
                id = 1,
                name = "odc 1",
                airDate = "9/11",
                code = "665"
        )

        private val charactersResponse: ArrayList<Character> = arrayListOf(
                Character(id = 2),
                Character(id = 1),
                Character(id = 3),
                Character(id = 7)
        )
    }

    private lateinit var systemUnderTest: EpisodeDetailPresenter
    private val view: EpisodeDetailContract.View = mock()
    private val repository: Repository = mock()
    private val logger: Logger = mock()

    @Before
    fun setup() {
        systemUnderTest = EpisodeDetailPresenter(repository, logger)
        systemUnderTest.viewCreated(view)
    }

    @Test
    fun `viewCreated attaches the view`() {
        assertThat(systemUnderTest.view).isNotNull
    }

    @Test
    fun `viewDestroyed detaches the view`() {
        systemUnderTest.viewDestroyed()

        assertThat(systemUnderTest.view).isEqualTo(null)
    }

    @Test
    fun `fetchEpisodeCharacters shows a loading indicator`() {
        repositorySuccess()
        systemUnderTest.fetchEpisodeCharacters()

        verify(view).showLoading()
    }

    @Test
    fun `correct episode data is displayed`() {
        systemUnderTest.setEpisode(episode)

        verify(view).displayEpisodeInfo(episode)
    }

    private fun repositorySuccess() {
        Mockito.`when`(repository.getCharactersById(any()))
                .thenReturn(Single.just(charactersResponse))
    }
}