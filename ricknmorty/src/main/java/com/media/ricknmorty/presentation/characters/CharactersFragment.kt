package com.media.ricknmorty.presentation.characters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.media.base.view.decoration.RecyclerViewTopMargin
import com.media.base.view.displayable.DisplayableItem
import com.media.ricknmorty.R
import com.media.ricknmorty.presentation.characters.adapter.CharactersAdapter
import com.media.ricknmorty.presentation.characters.adapter.CharactersAdapterFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject


class CharactersFragment : Fragment(), CharactersContract.View {

    companion object {
        @JvmStatic fun newInstance() = CharactersFragment()
    }

    @Inject
    lateinit var presenter: CharactersContract.Presenter

    @Inject
    lateinit var charactersAdapterFactory: CharactersAdapterFactory

    private lateinit var listLayoutManager: LinearLayoutManager
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var errorSnackbar: Snackbar

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupSnackbar()
        presenter.viewCreated(this)
        presenter.fetchCharacters()
    }

    override fun onDestroy() {
        presenter.viewDestroyed()
        super.onDestroy()
    }

    private fun setupSnackbar() {
        errorSnackbar = Snackbar.make(charactersList, getString(R.string.snackbar_error_message), Snackbar.LENGTH_SHORT)
        errorSnackbar.anchorView = activity?.bottomNav
        errorSnackbar.setAction(getString(R.string.snackbar_action_text)) {
            presenter.refreshCharacters()
        }
    }

    private fun setupAdapter() {
        listLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        charactersAdapter = charactersAdapterFactory.create()
        charactersList.apply {
            adapter = charactersAdapter
            layoutManager = listLayoutManager
            addItemDecoration(RecyclerViewTopMargin(R.dimen.spacing))
        }
    }

    override fun showLoadingIndicator() {
        loadingIndicator.visibility = View.VISIBLE
        charactersList.visibility = View.GONE
    }

    override fun hideLoadingIndicator() {
        loadingIndicator.visibility = View.GONE
        charactersList.visibility = View.VISIBLE
    }

    override fun displayError() {
        errorSnackbar.show()
    }

    override fun displayCharacters(characters: List<DisplayableItem>) {
        charactersAdapter.setData(characters)
    }
}