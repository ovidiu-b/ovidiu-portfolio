package com.ovidiu.portfolio.architecture.model.data_source.local.data_access

import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.local.dao.*
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.*
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

    override suspend fun insertProfessional(professional: Professional) = withContext(ioDispatcher) {
        return@withContext professionalDao.insert(professional)
    }

    override suspend fun getImage(idProfessional: String): Image = withContext(ioDispatcher) {
        return@withContext imageDao.getImageByProfessionalId(idProfessional)
    }

    override suspend fun insertImage(image: Image) = withContext(ioDispatcher) {
        return@withContext imageDao.insert(image)
    }

    override suspend fun getContactList(idProfessional: String): List<Contact> = withContext(ioDispatcher) {
        return@withContext contacDao.getAllByProfessionalId(idProfessional)
    }

    override suspend fun insertContactList(contactList: List<Contact>) = withContext(ioDispatcher) {
        return@withContext contacDao.insert(contactList)
    }

    override suspend fun getExperienceList(idProfessional: String): List<Experience> = withContext(ioDispatcher) {
        return@withContext experienceDao.getAllByProfessionalId(idProfessional)
    }

    override suspend fun insertExperienceList(experienceList: List<Experience>) = withContext(ioDispatcher) {
        return@withContext experienceDao.insert(experienceList)
    }

    override suspend fun getStudyList(idProfessional: String): List<Study> = withContext(ioDispatcher) {
        return@withContext studyDao.getAllByProfessionalId(idProfessional)
    }

    override suspend fun insertStudyList(studyList: List<Study>) = withContext(ioDispatcher) {
        return@withContext studyDao.insert(studyList)
    }
}