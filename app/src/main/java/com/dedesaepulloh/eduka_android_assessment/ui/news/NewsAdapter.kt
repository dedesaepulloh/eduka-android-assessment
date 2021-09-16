package com.dedesaepulloh.eduka_android_assessment.ui.news

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dedesaepulloh.eduka_android_assessment.R
import com.dedesaepulloh.eduka_android_assessment.data.source.local.entity.NewsEntity
import com.dedesaepulloh.eduka_android_assessment.databinding.ItemsNewsBinding
import java.text.SimpleDateFormat

class NewsAdapter : PagedListAdapter<NewsEntity, NewsAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsEntity>() {
            override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
                return oldItem.newsId == newItem.newsId
            }

            override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class NewsViewHolder(private val binding: ItemsNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news : NewsEntity){
            binding.apply {
                tvTitle.text = news.title
                Glide.with(itemView)
                    .load(news.urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_image_broken)
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val mView = ItemsNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(mView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        if (news != null) {
            holder.bind(news)
        }
    }
}