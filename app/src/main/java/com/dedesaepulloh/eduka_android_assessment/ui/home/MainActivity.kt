package com.dedesaepulloh.eduka_android_assessment.ui.home

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dedesaepulloh.eduka_android_assessment.BaseApplication
import com.dedesaepulloh.eduka_android_assessment.R
import com.dedesaepulloh.eduka_android_assessment.databinding.ActivityMainBinding
import com.dedesaepulloh.eduka_android_assessment.ui.news.NewsAdapter
import com.dedesaepulloh.eduka_android_assessment.viewmodel.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener  {
    private lateinit var activityMainBinding: ActivityMainBinding
    private val adapter: NewsAdapter by lazy { NewsAdapter() }


    @Inject
    lateinit var factory: ViewModelFactory

    private val mainViewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val bottomNavigationView: BottomNavigationView = activityMainBinding.bottomNav
        val navController = findNavController(R.id.nav_fragment)
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.news_nav, R.id.favorite_nav, R.id.web_nav))

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
        mainViewModel.searchNews(searchQuery).observe(this, { list ->
            list.let {
                adapter.setData(it)
            }
        })
    }
}