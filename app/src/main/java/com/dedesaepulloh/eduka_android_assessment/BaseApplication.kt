package com.dedesaepulloh.eduka_android_assessment

import android.app.Application
import com.dedesaepulloh.eduka_android_assessment.di.ApplicationComponent
import com.dedesaepulloh.eduka_android_assessment.di.DaggerApplicationComponent

open class BaseApplication: Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}