package com.dedesaepulloh.eduka_android_assessment.di

import com.dedesaepulloh.eduka_android_assessment.data.source.NewsDataSource
import com.dedesaepulloh.eduka_android_assessment.data.source.NewsRepository
import dagger.Binds
import dagger.Module

@Module(includes = [DatabaseModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(newsRepository: NewsRepository): NewsDataSource
}