package com.media.ricknmorty.presentation.episodes.detail

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.media.base.view.displayable.DisplayableItem
import com.media.base.view.string.putInBrackets
import com.media.base.view.string.putInQuotationMarks
import com.media.ricknmorty.R
import com.media.ricknmorty.model.Episode
import com.media.ricknmorty.presentation.characters.adapter.CharactersAdapter
import com.media.ricknmorty.presentation.characters.adapter.CharactersAdapterFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_episode_detail.*
import javax.inject.Inject

class EpisodeDetailActivity : AppCompatActivity(), EpisodeDetailContract.View {

    companion object {
        const val BUNDLE_TAG = "EPISODE_DETAIL_BUNDLE"
    }

    @Inject
    lateinit var presenter: EpisodeDetailContract.Presenter
    @Inject
    lateinit var charactersAdapterFactory: CharactersAdapterFactory

    private lateinit var listLayoutManager: LinearLayoutManager
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)

        val episode: Episode? = intent.getParcelableExtra(BUNDLE_TAG)

        setUpSystemUi()
        setupCollapsingToolbar()
        setupAdapter()

        presenter.viewCreated(this)
        presenter.setEpisode(episode)
        presenter.fetchEpisodeCharacters()
    }

    override fun onDestroy() {
        presenter.viewDestroyed()
        super.onDestroy()
    }

    override fun showLoading() {
        charactersList.visibility = View.GONE
        loadingIndicator.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        charactersList.visibility = View.VISIBLE
        loadingIndicator.visibility = View.GONE
    }

    override fun displayError() {}

    override fun displayCharacters(characters: List<DisplayableItem>) {
        charactersAdapter.setData(characters)
    }

    override fun displayEpisodeInfo(episode: Episode?) {
        collapsingToolbarLayout.title = episode?.code
        episodeTitle.text = episode?.name?.putInQuotationMarks()
        episodeAirTime.text = episode?.airDate?.putInBrackets()
    }

    private fun setUpSystemUi() {
        this.window.apply {
            navigationBarColor = Color.BLACK
        }
    }

    private fun setupAdapter() {
        listLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        charactersAdapter = charactersAdapterFactory.create()
        charactersList.apply {
            adapter = charactersAdapter
            layoutManager = listLayoutManager
        }
    }

    private fun setupCollapsingToolbar() {
        val typeface : Typeface? = ResourcesCompat.getFont(this, R.font.nexa_bold)
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar)
        collapsingToolbarLayout.apply {
            setExpandedTitleMargin(40,40,40,44)
            setCollapsedTitleTypeface(typeface)
            setExpandedTitleTypeface(typeface)
        }
        toolbarBackButton.setOnClickListener { returnToPreviousScreen() }
    }

    private fun returnToPreviousScreen() {
        this.finish()
    }
}