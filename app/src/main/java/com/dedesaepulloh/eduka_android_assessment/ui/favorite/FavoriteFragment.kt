package com.dedesaepulloh.eduka_android_assessment.ui.favorite

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
import com.dedesaepulloh.eduka_android_assessment.R
import com.dedesaepulloh.eduka_android_assessment.databinding.FragmentFavoriteBinding
import com.dedesaepulloh.eduka_android_assessment.viewmodel.ViewModelFactory
import javax.inject.Inject


class FavoriteFragment : Fragment() {

    private var fragmentFavoriteBinding: FragmentFavoriteBinding? = null
    private val binding get() = fragmentFavoriteBinding as FragmentFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BaseApplication).applicationComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (activity != null) {
            favoriteAdapter = FavoriteAdapter()
            favoriteViewModel.getFavoriteNews().observe(viewLifecycleOwner, { favorite ->
                favoriteAdapter.submitList(favorite)
                Log.i("ISI :", favorite.toString())
                if (favorite.size > 0) {
                    isEmptyFavorites(false)
                } else {
                    isEmptyFavorites(true)
                }
            })
            binding.rvFavorite.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteAdapter
            }
        }
    }

    private fun isEmptyFavorites(state: Boolean) {
        if (state) {
            binding.newsEmpty.apply {
                imgEmptyList.visibility = View.VISIBLE
                tvEmpty.text = getString(R.string.empty_favorite)
                tvEmpty.visibility = View.VISIBLE
                parentEmpty.visibility = View.VISIBLE
            }
            binding.rvFavorite.visibility = View.GONE
        } else {
            binding.newsEmpty.apply {
                imgEmptyList.visibility = View.GONE
                tvEmpty.visibility = View.GONE
                parentEmpty.visibility = View.GONE
            }
            binding.rvFavorite.visibility = View.VISIBLE
        }
    }
}