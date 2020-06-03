package com.ovidiu.portfolio.architecture.model.repository

import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Contact
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Experience
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Study
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

    private var professionalCached: Professional? = null

    override suspend fun getProfessionalByNameAndSurname(name: String, surname: String): Professional? {

        if(professionalCached != null) {
            return professionalCached
        }

        return withContext(ioDispatcher) {
            val professionalFromDB = localRepository.getProfessionalByNameAndSurname(name, surname)

            professionalCached = if(professionalFromDB == null) {
                val professionalFromServer = remoteRepository.getProfessionalByNameAndSurname(name, surname)

                localRepository.insertProfessional(professionalFromServer!!)

                professionalFromServer
            } else {
                professionalFromDB
            }

            return@withContext professionalCached
        }
    }

    override suspend fun getProfessionalProfileImageUrl(idProfessional: String): String {
        return withContext(ioDispatcher) {
            return@withContext localRepository.getProfessionalProfileImageUrl(idProfessional)
        }
    }

    override suspend fun getProfessionalContactList(idProfessional: String): List<Contact> {
        return withContext(ioDispatcher) {
            return@withContext localRepository.getProfessionalContactList(idProfessional)
        }
    }

    override suspend fun getProfessionalExperienceList(idProfessional: String): List<Experience> {
        return withContext(ioDispatcher) {
            return@withContext localRepository.getProfessionalExperienceList(idProfessional)
        }
    }

    override suspend fun getProfessionalStudyList(idProfessional: String): List<Study> {
        return withContext(ioDispatcher) {
            return@withContext localRepository.getProfessionalStudyList(idProfessional)
        }
    }
}