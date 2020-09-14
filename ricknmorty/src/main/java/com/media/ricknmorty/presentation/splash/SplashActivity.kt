package com.media.ricknmorty.presentation.splash

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.media.base.navigator.ActivityNavigator
import com.media.ricknmorty.R
import dagger.android.AndroidInjection
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity : Activity(){

    @Inject
    lateinit var activityNavigator : ActivityNavigator

    companion object {
        private const val SPLASH_SCREEN_DURATION_SECONDS: Long = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        setUpSystemUi()
        triggerSplashScreen()
    }

    private fun setUpSystemUi() {
        this.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            navigationBarColor = Color.BLACK
        }
    }

    private fun triggerSplashScreen() {
        Single.timer(SPLASH_SCREEN_DURATION_SECONDS, TimeUnit.SECONDS).subscribeBy(onSuccess = { launchMainScreen() })
    }

    private fun launchMainScreen() {
        activityNavigator.navigate(this)
    }
}