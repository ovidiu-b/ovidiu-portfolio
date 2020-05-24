package com.ovidiu.portfolio.di.modules

import androidx.lifecycle.ViewModel
import com.ovidiu.portfolio.architecture.viewmodel.IntroductionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class IntroductionModule {
    @Binds
    @IntoMap
    @ViewModelKey(IntroductionViewModel::class)
    abstract fun bindViewModel(viewmodel: IntroductionViewModel): ViewModel
}