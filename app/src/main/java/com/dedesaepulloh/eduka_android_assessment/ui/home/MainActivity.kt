package com.dedesaepulloh.eduka_android_assessment.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dedesaepulloh.eduka_android_assessment.R
import com.dedesaepulloh.eduka_android_assessment.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val bottomNavigationView: BottomNavigationView = activityMainBinding.bottomNav
        val navController = findNavController(R.id.nav_fragment)
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.news_nav, R.id.favorite_nav))

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
    }
}