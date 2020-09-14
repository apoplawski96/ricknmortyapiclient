package com.media.base.logger

interface Logger {

    fun setTag(tag: String)

    operator fun invoke(message: String)

    operator fun invoke(tag: String, message: String)
}