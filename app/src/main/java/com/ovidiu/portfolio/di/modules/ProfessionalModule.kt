package com.ovidiu.portfolio.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfessionalModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfessionalViewModel::class)
    abstract fun bindViewModel(viewmodel: ProfessionalViewModel): ViewModel
}