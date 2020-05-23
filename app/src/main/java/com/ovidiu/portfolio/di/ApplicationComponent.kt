package com.ovidiu.portfolio.di

import android.content.Context
import com.ovidiu.portfolio.di.modules.ApplicationModule
import com.ovidiu.portfolio.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}