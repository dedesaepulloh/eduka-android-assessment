package com.dedesaepulloh.eduka_android_assessment.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dedesaepulloh.eduka_android_assessment.data.source.local.LocalDataSource
import com.dedesaepulloh.eduka_android_assessment.data.source.local.entity.NewsEntity
import com.dedesaepulloh.eduka_android_assessment.data.source.remote.RemoteDataSource
import com.dedesaepulloh.eduka_android_assessment.data.source.remote.response.news.NewsResponse
import com.dedesaepulloh.eduka_android_assessment.data.source.remote.response.vo.ApiResponse
import com.dedesaepulloh.eduka_android_assessment.utils.AppExecutors
import com.dedesaepulloh.eduka_android_assessment.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : NewsDataSource {

    override fun getNews(): LiveData<Resource<PagedList<NewsEntity>>> {
        return object :
            NetworkBoundResource<PagedList<NewsEntity>, List<NewsResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<NewsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(20)
                    .setPageSize(20)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getAllNews(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<NewsEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<NewsResponse>>> =
                remoteDataSource.getNews()

            public override fun saveCallResult(data: List<NewsResponse>) {
                val newsList = ArrayList<NewsEntity>()
                for (response in data) {
                    val news = response.source?.id?.let {
                        NewsEntity(
                            it,
                            response.publishedAt.toString(),
                            response.author.toString(),
                            response.urlToImage.toString(),
                            response.description.toString(),
                            response.title.toString(),
                            response.url.toString(),
                            response.content.toString()
                        )
                    }
                    if (news != null) {
                        newsList.add(news)
                    }
                    localDataSource.insertNews(newsList)
                }
            }
        }.asLiveData()
    }

    override fun getNewsDetail(newsId: String): LiveData<NewsEntity> =
        localDataSource.getNewsById(newsId)

    override fun getFavoriteNews(): LiveData<PagedList<NewsEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .build()
        return LivePagedListBuilder(localDataSource.getNewsFavorites(), config).build()
    }

    override fun setFavoriteNews(news: NewsEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteNews(news, state) }
    }

    fun searchNews(searchQuery: String): Flow<List<NewsEntity>> =
        localDataSource.searchDatabase(searchQuery)

}