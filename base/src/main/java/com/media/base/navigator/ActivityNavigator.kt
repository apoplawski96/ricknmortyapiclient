package com.media.base.navigator

import android.app.Activity
import android.os.Parcelable

interface ActivityNavigator {

    fun navigate(activity: Activity, bundle: Parcelable? = null)
}