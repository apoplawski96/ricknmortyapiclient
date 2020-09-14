package com.media.ricknmorty.presentation.characters

import com.media.base.mvp.ViewCreationListener
import com.media.base.mvp.ViewLifecycleListener
import com.media.base.view.displayable.DisplayableItem

interface CharactersContract {

    interface View {
        
        fun showLoadingIndicator()

        fun hideLoadingIndicator()
        
        fun displayError()
        
        fun displayCharacters(characters: List<DisplayableItem>)
    }

    interface Presenter : ViewLifecycleListener, ViewCreationListener<View> {

        fun fetchCharacters()
        
        fun refreshCharacters()
    }
}