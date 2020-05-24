package com.ovidiu.portfolio.di

import android.content.Context
import com.ovidiu.portfolio.architecture.view.MainActivity
import com.ovidiu.portfolio.architecture.view.fragments.ProfileFragment
import com.ovidiu.portfolio.di.modules.ApplicationModule
import com.ovidiu.portfolio.di.modules.NetworkModule
import com.ovidiu.portfolio.di.modules.ProfessionalModule
import com.ovidiu.portfolio.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelModule::class,
        ProfessionalModule::class,
        NetworkModule::class]
)
interface ApplicationComponent{

    fun inject(activity: MainActivity)
    fun inject(fragment: ProfileFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}