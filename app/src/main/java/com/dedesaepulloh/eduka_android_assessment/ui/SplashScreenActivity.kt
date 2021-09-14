package com.dedesaepulloh.eduka_android_assessment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dedesaepulloh.eduka_android_assessment.ui.home.MainActivity
import com.dedesaepulloh.eduka_android_assessment.databinding.ActivitySplashScreenBinding
import com.dedesaepulloh.eduka_android_assessment.utils.Helper.TIME_MILLIS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var screenBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(screenBinding.root)
        CoroutineScope(Dispatchers.Main).launch {
            delay(TIME_MILLIS)
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }
    }
}