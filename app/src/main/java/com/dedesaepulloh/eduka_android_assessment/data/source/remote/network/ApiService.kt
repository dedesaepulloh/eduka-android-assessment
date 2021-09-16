package com.dedesaepulloh.eduka_android_assessment.data.source.remote.network

import com.dedesaepulloh.eduka_android_assessment.BuildConfig
import com.dedesaepulloh.eduka_android_assessment.data.source.remote.response.ListResponse
import com.dedesaepulloh.eduka_android_assessment.data.source.remote.response.news.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything?q=keyword")
    fun getNews(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Call<ListResponse<NewsResponse>>
}