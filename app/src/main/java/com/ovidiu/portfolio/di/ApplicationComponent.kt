package com.ovidiu.portfolio.di

import android.content.Context
import com.ovidiu.portfolio.architecture.view.MainActivity
import com.ovidiu.portfolio.architecture.view.fragments.IntroductionFragment
import com.ovidiu.portfolio.architecture.view.fragments.ProfileFragment
import com.ovidiu.portfolio.di.modules.*
import com.ovidiu.portfolio.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelModule::class,
        IntroductionModule::class,
        ProfileModule::class,
        NetworkModule::class,
        TestModule::class]
)
interface ApplicationComponent{

    fun inject(activity: MainActivity)
    fun inject(fragment: IntroductionFragment)
    fun inject(fragment: ProfileFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}