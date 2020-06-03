package com.ovidiu.portfolio.di.modules

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.local.DATABASE_NAME
import com.ovidiu.portfolio.architecture.model.data_source.local.LocalDataBase
import com.ovidiu.portfolio.architecture.model.data_source.local.data_access.ProfessionalLocalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.remote.data_access.ProfessionalRemoteDataAccess
import com.ovidiu.portfolio.architecture.model.repository.DefaultProfessionalRepository
import com.ovidiu.portfolio.architecture.model.repository.ProfessionalRepository
import com.ovidiu.portfolio.architecture.viewmodel.ProfessionalViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.BINARY

@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @Qualifier
    @Retention(BINARY)
    annotation class ProfessionalLocalDataAccess

    @Qualifier
    @Retention(BINARY)
    annotation class ProfessionalRemoteDataAccess

    @JvmStatic
    @Singleton
    @ProfessionalLocalDataAccess
    @Provides
    fun provideProfessionalLocalDataAccess(
        dataBase: LocalDataBase,
        ioDispatcher: CoroutineDispatcher
    ): ProfessionalDataAccess {
        return ProfessionalLocalDataAccess(
            dataBase.professionalDao(),
            dataBase.imageDao(),
            dataBase.studyDao(),
            dataBase.contactDao(),
            dataBase.experienceDao(),
            ioDispatcher
        )
    }

    @JvmStatic
    @Singleton
    @ProfessionalRemoteDataAccess
    @Provides
    fun provideProfessionalRemoteDataAccess(professionalApiRest: FirebaseFirestore): ProfessionalDataAccess {
        return ProfessionalRemoteDataAccess(professionalApiRest)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): LocalDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            LocalDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

@Module
abstract class ApplicationModuleBinds {

    @Binds
    @IntoMap
    @ViewModelKey(ProfessionalViewModel::class)
    abstract fun bindViewModel(viewmodel: ProfessionalViewModel): ViewModel

    @Binds
    abstract fun bindDefaultProfessionalRepository(implementation: DefaultProfessionalRepository): ProfessionalRepository
}