package com.ovidiu.portfolio.architecture.model.repository

import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.*
import com.ovidiu.portfolio.di.modules.ApplicationModule.ProfessionalRemoteDataAccess
import com.ovidiu.portfolio.di.modules.ApplicationModule.ProfessionalLocalDataAccess
import com.ovidiu.portfolio.support.AppSettings
import com.ovidiu.portfolio.support.DateTimeUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultProfessionalRepository @Inject constructor(
    @ProfessionalLocalDataAccess private val localRepository: ProfessionalDataAccess,
    @ProfessionalRemoteDataAccess private val remoteRepository: ProfessionalDataAccess,
    private val appSettings: AppSettings,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProfessionalRepository {

    private var professionalCached: Professional? = null
    private var imageUrlCached: Image? = null
    private var contactListCached: List<Contact>? = null
    private var experienceListCached: List<Experience>? = null
    private var studyListCached: List<Study>? = null

    override suspend fun getProfessionalByNameAndSurname(name: String, surname: String): Professional? {
        if(professionalCached != null) return professionalCached

        return withContext(ioDispatcher) {
            val professionalFromDB = localRepository.getProfessionalByNameAndSurname(name, surname)

            professionalCached = if(professionalFromDB == null || professionalLastSyncWasOneHourAgo()) {
                val professionalFromServer = remoteRepository.getProfessionalByNameAndSurname(name, surname)

                appSettings.setLastDateTimeSync(DateTimeUtils.getInstantNow().toEpochMilli())

                professionalFromServer?.let { localRepository.insertProfessional(it) }

                professionalFromServer
            } else {
                professionalFromDB
            }

            return@withContext professionalCached
        }
    }

    override suspend fun getProfessionalImage(idProfessional: String): Image? {
        if(imageUrlCached != null) return imageUrlCached

        return withContext(ioDispatcher) {
            val imageUrlFromDB = localRepository.getImage(idProfessional)

            imageUrlCached = if(imageUrlFromDB == null || professionalLastSyncWasOneHourAgo()) {
                val imageUrlFromServer = remoteRepository.getImage(idProfessional)

                imageUrlFromServer?.let { localRepository.insertImage(it) }

                imageUrlFromServer
            } else {
                imageUrlFromDB
            }

            return@withContext imageUrlCached
        }
    }

    override suspend fun getProfessionalContactList(idProfessional: String): List<Contact>? {
        if(contactListCached != null) return contactListCached

        return withContext(ioDispatcher) {
            val contactListFromDB = localRepository.getContactList(idProfessional)

            contactListCached = if(contactListFromDB.isNullOrEmpty() || professionalLastSyncWasOneHourAgo()) {
                val contactListFromServer = remoteRepository.getContactList(idProfessional)

                contactListFromServer?.let { localRepository.insertContactList(it) }

                contactListFromServer
            } else {
                contactListFromDB
            }

            return@withContext contactListCached
        }
    }

    override suspend fun getProfessionalExperienceList(idProfessional: String): List<Experience>? {
        if(experienceListCached != null) return experienceListCached

        return withContext(ioDispatcher) {
            val experienceListFromDB = localRepository.getExperienceList(idProfessional)

            experienceListCached = if(experienceListCached.isNullOrEmpty() || professionalLastSyncWasOneHourAgo()) {
                val experienceListFromServer = remoteRepository.getExperienceList(idProfessional)

                experienceListFromServer?.let { localRepository.insertExperienceList(it) }

                experienceListFromServer
            } else {
                experienceListFromDB
            }

            return@withContext experienceListCached
        }
    }

    override suspend fun getProfessionalStudyList(idProfessional: String): List<Study>? {
        if(studyListCached != null) return studyListCached

        return withContext(ioDispatcher) {
            val studyListFromDB = localRepository.getStudyList(idProfessional)

            studyListCached = if(studyListFromDB.isNullOrEmpty() || professionalLastSyncWasOneHourAgo()) {
                val studyListFromServer = remoteRepository.getStudyList(idProfessional)

                studyListFromServer?.let { localRepository.insertStudyList(it) }

                studyListFromServer
            } else {
                studyListFromDB
            }

            return@withContext studyListCached
        }
    }

    private fun professionalLastSyncWasOneHourAgo(): Boolean {
        val instantNow = DateTimeUtils.getInstantNow()

        val diff = instantNow.minusMillis(appSettings.getLastDateTimeSync())

        val hourInMilli = 3600000L

        return diff.toEpochMilli() >= hourInMilli
    }
}