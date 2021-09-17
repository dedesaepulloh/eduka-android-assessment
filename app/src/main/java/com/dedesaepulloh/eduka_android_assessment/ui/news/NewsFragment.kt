package com.dedesaepulloh.eduka_android_assessment.ui.news

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedesaepulloh.eduka_android_assessment.BaseApplication
import com.dedesaepulloh.eduka_android_assessment.databinding.FragmentNewsBinding
import com.dedesaepulloh.eduka_android_assessment.viewmodel.ViewModelFactory
import com.dedesaepulloh.eduka_android_assessment.vo.Status
import javax.inject.Inject


class NewsFragment : Fragment() {

    private var fragmentNewsBinding: FragmentNewsBinding? = null
    private val binding get() = fragmentNewsBinding as FragmentNewsBinding
    private lateinit var adapter: NewsAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    private val newsViewModel: NewsViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentNewsBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BaseApplication).applicationComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NewsAdapter()
        showLoading(true)
        binding.apply {
            rvNews.layoutManager = LinearLayoutManager(context)
            rvNews.setHasFixedSize(true)
            rvNews.adapter = adapter
        }

        newsViewModel.getNews().observe(viewLifecycleOwner, { news ->
            if (news != null) {
                when (news.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        news.data?.let {
                            adapter.submitList(it)
                            adapter.notifyDataSetChanged()
                            Log.i("DATA : ", news.toString())
                            showLoading(false)
                        }
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showTryAgain(true)
                    }
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.apply {
                rvNews.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                rvNews.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun showTryAgain(state: Boolean) {
        if (state) {
            binding.tryagainNews.apply {
                imgTryAgain.visibility = View.VISIBLE
                tryagainLoad.visibility = View.VISIBLE
            }
            binding.rvNews.visibility = View.GONE
        } else {
            binding.tryagainNews.apply {
                imgTryAgain.visibility = View.GONE
                tryagainLoad.visibility = View.GONE
            }
            binding.rvNews.visibility = View.VISIBLE
        }
    }
}