package com.dedesaepulloh.eduka_android_assessment.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dedesaepulloh.eduka_android_assessment.data.source.NewsRepository
import com.dedesaepulloh.eduka_android_assessment.data.source.local.entity.NewsEntity

class DetailViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    fun getNewsDetail(newsId: String): LiveData<NewsEntity> = newsRepository.getNewsDetail(newsId)

    fun setFavoriteNews(news: NewsEntity) {
        val newState = !news.favorite
        newsRepository.setFavoriteNews(news, newState)
    }
}