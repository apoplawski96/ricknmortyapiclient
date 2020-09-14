package com.media.ricknmorty.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.media.ricknmorty.R
import com.media.ricknmorty.presentation.characters.CharactersFragment
import com.media.ricknmorty.presentation.episodes.list.EpisodesFragment
import com.media.ricknmorty.presentation.locations.LocationsFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupSystemUi()
        initBottomNavigation()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        selectTab(item.itemId)
        true
    }

    private fun initBottomNavigation() {
        bottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        selectTab(R.id.navigation_episodes)
    }

    private fun selectTab(itemId: Int) {
        val tag = itemId.toString()
        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
            R.id.navigation_episodes -> EpisodesFragment.newInstance()
            R.id.navigation_characters -> CharactersFragment.newInstance()
            R.id.navigation_locations -> LocationsFragment.newInstance()
            else -> null
        }
        loadFragment(fragment, tag)
    }

    private fun loadFragment(fragment: Fragment?, tag: String) {
        if (fragment != null) {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

            if (viewModel.lastActiveFragmentTag != null) {
                val lastFragment = supportFragmentManager.findFragmentByTag(viewModel.lastActiveFragmentTag)
                if (lastFragment != null) transaction.hide(lastFragment)
            }

            if (!fragment.isAdded) {
                transaction.add(R.id.fragmentContainer, fragment, tag)
            } else {
                transaction.show(fragment)
            }

            transaction.commit()
            viewModel.lastActiveFragmentTag = tag
        }
    }

    private fun setupSystemUi() {
        this.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            navigationBarColor = Color.BLACK
        }
    }
}