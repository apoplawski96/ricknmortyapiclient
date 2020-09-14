package com.media.ricknmorty.episodes

import com.media.base.scheduler.SchedulerProvider
import com.media.base.scheduler.TrampolineSchedulerProvider
import com.media.base.view.displayable.DisplayableItem
import com.media.ricknmorty.data.entity.Characters
import com.media.ricknmorty.data.entity.FetchInfo
import com.media.ricknmorty.data.repository.Repository
import com.media.ricknmorty.model.Character
import com.media.ricknmorty.presentation.characters.CharactersContract
import com.media.ricknmorty.presentation.characters.CharactersPresenter
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class CharactersPresenterTest {

    companion object {
        private val charactersResponse: Characters =
                Characters(
                        feedInfo = FetchInfo(),
                        items = listOf(
                                Character(id = 1),
                                Character(id = 2),
                                Character(id = 3),
                                Character(id = 4)
                        )
                )
        private val expectedDisplayableItems: List<DisplayableItem> = listOf(
                Character(id = 1),
                Character(id = 2),
                Character(id = 3),
                Character(id = 4)
        )
    }

    private lateinit var systemUnderTest: CharactersPresenter
    private val schedulerProvider: SchedulerProvider = TrampolineSchedulerProvider()
    private val view: CharactersContract.View = mock()
    private val repository: Repository = mock()

    @Before
    fun setup() {
        systemUnderTest = CharactersPresenter(repository, schedulerProvider)
        systemUnderTest.viewCreated(view)
    }

    @Test
    fun `viewCreated attaches the view`() {
        Assertions.assertThat(systemUnderTest.view).isNotNull
    }

    @Test
    fun `viewDestroyed detaches the view`() {
        systemUnderTest.viewDestroyed()
        Assertions.assertThat(systemUnderTest.view).isEqualTo(null)
    }

    @Test
    fun `fetchCharacters shows a loading indicator`() {
        repositorySuccess()
        systemUnderTest.fetchCharacters()

        verify(view).showLoadingIndicator()
    }

    @Test
    fun `fetchCharacters successful request displays correct data in the list and hides loading indicator`() {
        repositorySuccess()
        systemUnderTest.fetchCharacters()

        verify(view).hideLoadingIndicator()
        verify(view).displayCharacters(expectedDisplayableItems)
    }

    @Test
    fun `error snackbar displays on unsuccessful data fetch, loading indicator hides`() {
        repositoryFailure()
        systemUnderTest.fetchCharacters()

        verify(view).hideLoadingIndicator()
        verify(view).displayError()
    }

    private fun repositorySuccess() {
        Mockito.`when`(repository.getAllCharacters())
                .thenReturn(Single.just(charactersResponse))
    }

    private fun repositoryFailure() {
        Mockito.`when`(repository.getAllCharacters())
                .thenReturn(Single.error(Exception()))
    }
}