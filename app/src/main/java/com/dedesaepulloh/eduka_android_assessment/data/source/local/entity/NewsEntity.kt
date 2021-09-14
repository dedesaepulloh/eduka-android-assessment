package com.dedesaepulloh.eduka_android_assessment.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tbl_news")
data class NewsEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "newsId")
    val newsId: String,

    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false

) : Parcelable
