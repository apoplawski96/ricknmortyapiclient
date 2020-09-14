package com.media.ricknmorty.presentation

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(): ViewModel() {
    internal var lastActiveFragmentTag: String? = null
}