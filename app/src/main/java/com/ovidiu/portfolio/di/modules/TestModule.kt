package com.ovidiu.portfolio.di.modules

import com.ovidiu.portfolio.architecture.model.repository.ProfessionalRepository
import com.ovidiu.portfolio.support.test.FakeProfessionalRepositoryData
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object TestModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FakeProfessionalDataAccess

    @JvmStatic
    @Singleton
    @FakeProfessionalDataAccess
    @Provides
    fun providesFakeProfessionalRepository(): ProfessionalRepository {
        return FakeProfessionalRepositoryData()
    }
}