package com.media.base.mvp

interface ViewLifecycleListener {

    fun viewAvailable() {}

    fun viewUnavailable() {}

    fun viewBecomeVisible() {}

    fun viewBecomeHidden() {}
}