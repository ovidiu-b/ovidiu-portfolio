package com.ovidiu.portfolio

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.ovidiu.portfolio.di.DaggerApplicationComponent

class MainApplication : Application() {
    val applicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
