package com.dedesaepulloh.eduka_android_assessment.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dedesaepulloh.eduka_android_assessment.data.source.remote.network.ApiConfig
import com.dedesaepulloh.eduka_android_assessment.data.source.remote.response.ListResponse
import com.dedesaepulloh.eduka_android_assessment.data.source.remote.response.news.NewsResponse
import com.dedesaepulloh.eduka_android_assessment.data.source.remote.response.vo.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor() {
    fun getNews(): LiveData<ApiResponse<List<NewsResponse>>> {
        val results = MutableLiveData<ApiResponse<List<NewsResponse>>>()
        ApiConfig.getApiService().getNews()
            .enqueue(object : Callback<ListResponse<NewsResponse>> {
                override fun onResponse(
                    call: Call<ListResponse<NewsResponse>>,
                    response: Response<ListResponse<NewsResponse>>
                ) {
                    if (response.isSuccessful) {
                        results.postValue(ApiResponse.success(response.body()?.articles as List<NewsResponse>))
                        Log.i("isi :", "${response.body()?.articles}")
                    }
                }

                override fun onFailure(call: Call<ListResponse<NewsResponse>>, t: Throwable) {
                    Log.e("Failure", "${t.message}")
                    results.postValue(ApiResponse.error(t.message.toString(), mutableListOf()))
                }
            })
        return results
    }
}