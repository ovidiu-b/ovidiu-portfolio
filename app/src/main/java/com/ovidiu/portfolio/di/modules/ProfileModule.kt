package com.ovidiu.portfolio.di.modules

import androidx.lifecycle.ViewModel
import com.ovidiu.portfolio.architecture.viewmodel.IntroductionViewModel
import com.ovidiu.portfolio.architecture.viewmodel.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfileModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindViewModel(viewmodel: ProfileViewModel): ViewModel
}