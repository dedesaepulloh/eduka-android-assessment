package com.dedesaepulloh.eduka_android_assessment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dedesaepulloh.eduka_android_assessment.data.source.NewsRepository
import com.dedesaepulloh.eduka_android_assessment.di.AppScope
import com.dedesaepulloh.eduka_android_assessment.ui.detail.DetailViewModel
import com.dedesaepulloh.eduka_android_assessment.ui.news.NewsViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(private val mNewsRepository: NewsRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> {
                NewsViewModel(mNewsRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mNewsRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}