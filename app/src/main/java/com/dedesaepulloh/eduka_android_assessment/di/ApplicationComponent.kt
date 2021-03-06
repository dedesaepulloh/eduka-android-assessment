package com.dedesaepulloh.eduka_android_assessment.di

import android.content.Context
import com.dedesaepulloh.eduka_android_assessment.data.source.NewsDataSource
import com.dedesaepulloh.eduka_android_assessment.ui.detail.DetailActivity
import com.dedesaepulloh.eduka_android_assessment.ui.favorite.FavoriteFragment
import com.dedesaepulloh.eduka_android_assessment.ui.home.MainActivity
import com.dedesaepulloh.eduka_android_assessment.ui.news.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@AppScope
@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun provideRepository(): NewsDataSource

    fun inject(newsFragment: NewsFragment)
    fun inject(detailActivity: DetailActivity)
    fun inject(favoriteFragment: FavoriteFragment)
    fun inject(mainActivity: MainActivity)
}