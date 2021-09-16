package com.dedesaepulloh.eduka_android_assessment.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dedesaepulloh.eduka_android_assessment.BaseApplication
import com.dedesaepulloh.eduka_android_assessment.R
import com.dedesaepulloh.eduka_android_assessment.data.source.local.entity.NewsEntity
import com.dedesaepulloh.eduka_android_assessment.databinding.ActivityDetailNewsBinding
import com.dedesaepulloh.eduka_android_assessment.utils.Helper
import com.dedesaepulloh.eduka_android_assessment.utils.Helper.EXTRA_ID
import com.dedesaepulloh.eduka_android_assessment.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityDetailNewsBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val detailViewModel: DetailViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showLoading(true)

        val newsSourceId = intent.getStringExtra(EXTRA_ID)

        if (newsSourceId != null) {
            detailViewModel.getNewsDetail(newsSourceId.toString()).observe(this, {
                supportActionBar?.title = getString(R.string.detail_news)
                newsLoadData(it)
                showLoading(false)
            })
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun newsLoadData(data: NewsEntity?) {
        data?.apply {
            activityDetailBinding.itemDetail.apply {

                tvTitle.text = title
                tvAuthor.text = author

                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                val formatter = SimpleDateFormat("dd MMMM yyyy")
                val output: String = if (publishedAt.isEmpty()) {
                    "getString(R.string.strip)"
                } else {
                    formatter.format(parser.parse(publishedAt))
                }

                tvPublis.text = output
                tvContent.text = content
                Glide.with(this@DetailActivity)
                    .load(urlToImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_image_broken)
                    .into(roundedImg)
            }
//            setFavoriteState(favorite)
        }
    }

//    private fun setFavoriteState(favorite: Boolean) {
//
//    }

    private fun showLoading(state: Boolean) {
        if (state) {
            activityDetailBinding.itemDetail.apply {
                progressBar.visibility = View.VISIBLE
                labelAuthor.visibility = View.GONE
                labelPublis.visibility = View.GONE
                tvTitle.visibility = View.GONE
                tvAuthor.visibility = View.GONE
                tvPublis.visibility = View.GONE
                tvContent.visibility = View.GONE
            }

        } else {
            activityDetailBinding.itemDetail.apply {
                progressBar.visibility = View.GONE
                labelAuthor.visibility = View.VISIBLE
                labelPublis.visibility = View.VISIBLE
                tvTitle.visibility = View.VISIBLE
                tvAuthor.visibility = View.VISIBLE
                tvPublis.visibility = View.VISIBLE
                tvContent.visibility = View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}