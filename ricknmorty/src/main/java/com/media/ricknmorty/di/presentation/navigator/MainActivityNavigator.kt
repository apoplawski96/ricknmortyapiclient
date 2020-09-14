package com.media.ricknmorty.di.presentation.navigator

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import com.media.base.navigator.ActivityNavigator
import com.media.ricknmorty.presentation.MainActivity
import javax.inject.Inject

class MainActivityNavigator @Inject constructor(): ActivityNavigator {

    override fun navigate(activity: Activity, bundle: Parcelable?) {
        val intent = Intent(activity, MainActivity::class.java)
        activity navigateUsing intent
    }

    private infix fun Activity.navigateUsing(intent: Intent) {
        startActivity(intent)
        finish()
    }
}