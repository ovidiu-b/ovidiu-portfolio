package com.ovidiu.portfolio.architecture.model.data_source.local.data_access

import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.local.dao.*
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Contact
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Experience
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Study
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfessionalLocalDataAccess @Inject constructor(
    private val professionalDao: ProfessionalDao,
    private val imageDao: ImageDao,
    private val studyDao: StudyDao,
    private val contacDao: ContactDao,
    private val experienceDao: ExperienceDao,
    private val ioDispatcher: CoroutineDispatcher
) : ProfessionalDataAccess {

    override suspend fun getProfessionalByNameAndSurname(name: String, surname: String):
            Professional? = withContext(ioDispatcher)
    {
        return@withContext professionalDao.getByNameAndSurname(name, surname)
    }

    override suspend fun getProfessionalProfileImageUrl(idProfessional: String): String = withContext(ioDispatcher) {
        return@withContext imageDao.getUrlByProfessionalId(idProfessional)
    }

    override suspend fun getProfessionalContactList(idProfessional: String): List<Contact> = withContext(ioDispatcher) {
        return@withContext contacDao.getAllByProfessionalId(idProfessional)
    }

    override suspend fun getProfessionalExperienceList(idProfessional: String): List<Experience> = withContext(ioDispatcher) {
        return@withContext experienceDao.getAllByProfessionalId(idProfessional)
    }

    override suspend fun getProfessionalStudyList(idProfessional: String): List<Study> = withContext(ioDispatcher) {
        return@withContext studyDao.getAllByProfessionalId(idProfessional)
    }

    override suspend fun insertProfessional(professional: Professional) = withContext(ioDispatcher) {
        return@withContext professionalDao.insert(professional)
    }
}