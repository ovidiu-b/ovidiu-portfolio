package com.ovidiu.portfolio.architecture.model.repository

import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Contact
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import com.ovidiu.portfolio.di.modules.ApplicationModule.ProfessionalRemoteDataAccess
import com.ovidiu.portfolio.di.modules.ApplicationModule.ProfessionalLocalDataAccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultProfessionalRepository @Inject constructor(
    @ProfessionalLocalDataAccess private val localRepository: ProfessionalDataAccess,
    @ProfessionalRemoteDataAccess private val remoteRepository: ProfessionalDataAccess,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProfessionalRepository {

    override suspend fun getProfessionalByNameAndSurname(
        name: String,
        surname: String
    ): Professional {

        return withContext(ioDispatcher) {
            return@withContext localRepository.getProfessionalByNameAndSurname(name, surname)
        }
    }

    override suspend fun getProfessionalProfileImageUrl(idProfessional: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun getProfessionalContactList(idProfessional: String): List<Contact> {
        TODO("Not yet implemented")
    }
}