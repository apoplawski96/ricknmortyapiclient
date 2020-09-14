package com.media.ricknmorty.presentation.episodes.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.media.base.logger.Logger
import com.media.base.navigator.ActivityNavigator
import com.media.base.view.displayable.DisplayableItem
import com.media.base.view.decoration.RecyclerViewTopMargin
import com.media.ricknmorty.R
import com.media.ricknmorty.model.Episode
import com.media.ricknmorty.presentation.episodes.adapter.EpisodesAdapter
import com.media.ricknmorty.presentation.episodes.adapter.EpisodesAdapterFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_episodes.*
import javax.inject.Inject

class EpisodesFragment : Fragment(), EpisodesContract.View {

    companion object {
        @JvmStatic fun newInstance() = EpisodesFragment()

        private const val SCROLL_DIRECTION_DOWN = 1
        private const val FETCHED_EPISODES_BUNDLE = "CURRENTLY_FETCHED_EPISODES"
    }

    @Inject
    lateinit var presenter: EpisodesContract.Presenter

    @Inject
    lateinit var episodesAdapterFactory: EpisodesAdapterFactory

    @Inject
    lateinit var navigator: ActivityNavigator

    @Inject
    lateinit var logger: Logger

    private lateinit var onListScrollListener: RecyclerView.OnScrollListener
    private lateinit var listLayoutManager: LinearLayoutManager
    private lateinit var episodesAdapter: EpisodesAdapter
    private lateinit var errorSnackbar: Snackbar

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupSnackbar()
        presenter.viewCreated(this)
        presenter.fetchInitialEpisodes()
    }

    override fun showMainLoadingIndicator() {
        mainLoadingIndicator.visibility = View.VISIBLE
        episodesList.visibility = View.GONE
    }

    override fun hideMainLoadingIndicator() {
        mainLoadingIndicator.visibility = View.GONE
        episodesList.visibility = View.VISIBLE
    }

    override fun displayError() {
        errorSnackbar.show()
    }

    override fun displayEpisodes(episodes: List<DisplayableItem>) {
        episodesAdapter.setData(episodes)
        episodesList.addOnScrollListener(onListScrollListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (presenter.getCurrentlyFetchedEpisodes().isNullOrEmpty().not()) {
            outState.putParcelableArrayList(FETCHED_EPISODES_BUNDLE, presenter.getCurrentlyFetchedEpisodes())
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        restoreSavedEpisodes(savedInstanceState)
    }

    private fun setupSnackbar() {
        errorSnackbar = Snackbar.make(headerText, getString(R.string.snackbar_error_message), Snackbar.LENGTH_SHORT)
        errorSnackbar.anchorView = activity?.bottomNav
        errorSnackbar.setAction(getString(R.string.snackbar_action_text)) {
            presenter.refreshEpisodes()
        }
    }

    private fun setupAdapter() {
        setUpUserOnScrollListener()
        listLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        episodesAdapter = episodesAdapterFactory.create(::episodeItemClicked)
        episodesList.apply {
            adapter = episodesAdapter
            layoutManager = listLayoutManager
            addItemDecoration(RecyclerViewTopMargin(R.dimen.spacing))
        }
    }

    private fun setUpUserOnScrollListener() {
        onListScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (episodesList.canScrollVertically(SCROLL_DIRECTION_DOWN)) return

                presenter.fetchMoreEpisodes()
                episodesList.removeOnScrollListener(this)
            }
        }
    }

    private fun episodeItemClicked(episode: Episode?) {
        navigator.navigate(
                activity = activity as FragmentActivity,
                bundle = episode
        )
    }

    private fun restoreSavedEpisodes(savedInstanceState: Bundle?) {
        val episodes: ArrayList<Episode>? = savedInstanceState?.getParcelableArrayList("CURRENTLY_FETCHED_EPISODES")
        episodes?.toList()?.let { displayEpisodes(it) }
    }
}