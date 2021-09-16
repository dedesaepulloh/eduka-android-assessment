package com.dedesaepulloh.eduka_android_assessment.data.source.remote.response.news

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("author")
    val author: String?,

    @SerializedName("urlToImage")
    val urlToImage: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("content")
    val content: String?,

    @SerializedName("source")
	val source: Source?
)

data class Source(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?
)
