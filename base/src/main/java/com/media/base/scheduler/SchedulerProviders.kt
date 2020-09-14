package com.media.base.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}

class AndroidSchedulerProvider : SchedulerProvider {
    override fun ui() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
}

class TrampolineSchedulerProvider : SchedulerProvider {
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}