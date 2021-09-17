package com.dedesaepulloh.eduka_android_assessment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dedesaepulloh.eduka_android_assessment.data.source.NewsRepository
import com.dedesaepulloh.eduka_android_assessment.data.source.local.entity.NewsEntity

class MainViewModel(private val newsRepository: NewsRepository) : ViewModel()  {
    fun searchNews(searchQuery: String): LiveData<List<NewsEntity>> {
        return newsRepository.searchNews(searchQuery).asLiveData()
    }
}