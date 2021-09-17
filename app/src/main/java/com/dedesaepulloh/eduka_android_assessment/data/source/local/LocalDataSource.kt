package com.dedesaepulloh.eduka_android_assessment.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dedesaepulloh.eduka_android_assessment.data.source.local.entity.NewsEntity
import com.dedesaepulloh.eduka_android_assessment.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val mNewsDao: NewsDao) {
    fun getAllNews(): DataSource.Factory<Int, NewsEntity> = mNewsDao.getNews()
    fun getNewsById(newsId: String): LiveData<NewsEntity> = mNewsDao.getNewsDetail(newsId)
    fun getNewsFavorites(): DataSource.Factory<Int, NewsEntity> = mNewsDao.getFavoriteNews()

    fun insertNews(news: List<NewsEntity>) = mNewsDao.insertNews(news)
    fun setFavoriteNews(news: NewsEntity, newState: Boolean) {
        news.favorite = newState
        mNewsDao.updateNews(news)
    }

    fun searchDatabase(searchQuery: String): Flow<List<NewsEntity>> = mNewsDao.searchNews(searchQuery)

}