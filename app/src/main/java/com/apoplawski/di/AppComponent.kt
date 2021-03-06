package com.apoplawski.di

import com.apoplawski.MyApplication
import com.media.base.di.BaseModule
import com.media.ricknmorty.di.ActivitiesModule
import com.media.ricknmorty.di.data.ApiModule
import com.media.ricknmorty.di.FragmentsModule
import com.media.ricknmorty.di.data.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ActivitiesModule::class,
            FragmentsModule::class,
            ApiModule::class,
            RepositoryModule::class,
            BaseModule::class
        ]
)
interface AppComponent {

    fun inject (application: MyApplication)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun applicationBind (application: MyApplication): Builder
    }
}