package com.dedesaepulloh.eduka_android_assessment.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dedesaepulloh.eduka_android_assessment.data.source.local.entity.NewsEntity
import com.dedesaepulloh.eduka_android_assessment.vo.Resource

interface NewsDataSource {
    fun getNews(): LiveData<Resource<PagedList<NewsEntity>>>
    fun getNewsDetail(newsId: String): LiveData<NewsEntity>
    fun getFavoriteNews(): LiveData<PagedList<NewsEntity>>
    fun setFavoriteNews(news: NewsEntity, state: Boolean)
}