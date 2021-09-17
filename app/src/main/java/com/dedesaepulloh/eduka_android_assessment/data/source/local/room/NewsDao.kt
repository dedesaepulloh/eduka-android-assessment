package com.dedesaepulloh.eduka_android_assessment.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dedesaepulloh.eduka_android_assessment.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM tbl_news")
    fun getNews(): DataSource.Factory<Int, NewsEntity>

    @Query("SELECT * FROM tbl_news WHERE newsId= :newsId")
    fun getNewsDetail(newsId: String): LiveData<NewsEntity>

    @Query("SELECT * FROM tbl_news WHERE favorite = 1")
    fun getFavoriteNews(): DataSource.Factory<Int, NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<NewsEntity>)

    @Update
    fun updateNews(news: NewsEntity)

    @Query("SELECT * FROM tbl_news WHERE title LIKE :searchQuery")
    fun searchNews(searchQuery: String): Flow<List<NewsEntity>>

}