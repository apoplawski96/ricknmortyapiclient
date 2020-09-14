package com.media.base.mvp

interface ViewCreationListener<View> {

    fun viewCreated(view: View) {}

    fun viewDestroyed() {}
}