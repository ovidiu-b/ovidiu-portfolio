package com.ovidiu.portfolio

import android.app.Application
import com.ovidiu.portfolio.di.ApplicationComponent
import com.ovidiu.portfolio.di.DaggerApplicationComponent

class App: Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}
