package com.dedesaepulloh.eduka_android_assessment.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dedesaepulloh.eduka_android_assessment.data.source.NewsRepository
import com.dedesaepulloh.eduka_android_assessment.data.source.local.entity.NewsEntity
import com.dedesaepulloh.eduka_android_assessment.vo.Resource

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    fun getNews(): LiveData<Resource<PagedList<NewsEntity>>> =
        newsRepository.getNews()
}