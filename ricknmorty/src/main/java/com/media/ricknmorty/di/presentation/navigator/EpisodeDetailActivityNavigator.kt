package com.media.ricknmorty.di.presentation.navigator

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import com.media.base.navigator.ActivityNavigator
import com.media.ricknmorty.presentation.episodes.detail.EpisodeDetailActivity

class EpisodeDetailActivityNavigator : ActivityNavigator {

    override fun navigate(activity: Activity, bundle: Parcelable?) {
        val intent = Intent(activity, EpisodeDetailActivity::class.java)
        intent.putExtra(EpisodeDetailActivity.BUNDLE_TAG, bundle)
        activity navigateUsing intent
    }

    private infix fun Activity.navigateUsing(intent: Intent) {
        startActivity(intent)
    }
}
