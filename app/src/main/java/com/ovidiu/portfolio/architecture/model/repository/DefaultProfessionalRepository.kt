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
import java.lang.Exception
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

    override suspend fun getProfessionalByNameAndSurname(name: String, surname: String): Result<Professional?> {
        professionalCached?.let { return Result.Success(it) }

        return withContext(ioDispatcher) {
            // El profesional no está en la cache
            // Tenemos que cargarlo desde la base de datos o desde el servidor remoto

            // ¿Cuando cargamos el profesional desde el servidor remoto?
            // 1. El profesional no existe en la base de datos local
            // 2. Ha pasado más de 1 hora sin cargar el profesional desde el servidor remoto

            // Cada vez que cargamos el profesional desde el servidor remoto, lo actualizamos en
            // la base de datos local

            val localProfessionalResult = localRepository.getProfessionalByNameAndSurname(name, surname)

            if(localProfessionalResult.succeeded) {
                professionalCached = (localProfessionalResult as Result.Success).data

                if(professionalLastSyncWasOneHourAgo()) {
                    loadProfessionalFromRemote(name, surname) {
                        professionalCached = it
                    }
                }
            } else {
                // Cargamos el profesional desde el servidor, ya que no ha sido posible cargarlo desde
                // la base de datos local

                loadProfessionalFromRemote(name, surname) {
                    professionalCached = it
                }
            }

            if(professionalCached == null) {
                return@withContext  Result.Error(Exception("No se ha podido cargar el profesional"))
            }

            return@withContext  Result.Success(professionalCached)
        }
    }

    private suspend fun loadProfessionalFromRemote(name: String, surname: String, onSuccess: (professional: Professional?) -> Unit) {
        val remoteProfessionalResult = remoteRepository.getProfessionalByNameAndSurname(name, surname)

        if(remoteProfessionalResult.succeeded) {
            appSettings.setLastDateTimeSync(DateTimeUtils.getInstantNow().toEpochMilli())

            (remoteProfessionalResult as Result.Success).data?.let {
                localRepository.insertProfessional(it)
            }

            onSuccess(remoteProfessionalResult.data)
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