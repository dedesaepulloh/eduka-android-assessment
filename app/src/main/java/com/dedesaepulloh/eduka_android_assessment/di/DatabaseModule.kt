package com.dedesaepulloh.eduka_android_assessment.di

import android.content.Context
import androidx.room.Room
import com.dedesaepulloh.eduka_android_assessment.data.source.local.room.NewsDao
import com.dedesaepulloh.eduka_android_assessment.data.source.local.room.NewsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): NewsDatabase = Room.databaseBuilder(
        context,
        NewsDatabase::class.java, "db_news"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideNewsDao(database: NewsDatabase): NewsDao = database.newsDao()
}