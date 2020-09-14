package com.media.base.logger

import android.util.Log
import javax.inject.Singleton

@Singleton
object DebugLogger : Logger {

    private var DEFAULT_TAG = "2137"

    override fun invoke(message: String) {
        Log.d(DEFAULT_TAG, message)
    }

    override fun invoke(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun setTag(tag: String) {
        DEFAULT_TAG = tag
    }
}