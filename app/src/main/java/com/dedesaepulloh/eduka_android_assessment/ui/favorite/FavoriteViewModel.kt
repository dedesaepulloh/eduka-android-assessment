package com.dedesaepulloh.eduka_android_assessment.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dedesaepulloh.eduka_android_assessment.data.source.NewsRepository
import com.dedesaepulloh.eduka_android_assessment.data.source.local.entity.NewsEntity

class FavoriteViewModel(private val newsRepository: NewsRepository) : ViewModel()  {
    fun getFavoriteNews(): LiveData<PagedList<NewsEntity>> = newsRepository.getFavoriteNews()
}