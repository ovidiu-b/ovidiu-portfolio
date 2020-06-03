package com.ovidiu.portfolio.architecture.model.data_source.remote.data_access

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Contact
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Experience
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Study
import com.ovidiu.portfolio.architecture.model.data_source.remote.entity.ProfessionalRemote
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class ProfessionalRemoteDataAccess @Inject constructor(private val firestore: FirebaseFirestore) :
    ProfessionalDataAccess
{
    override suspend fun getProfessionalByNameAndSurname(
        name: String,
        surname: String
    ): Professional? {
        return try {
            val response = firestore.collection("Professional")
                .whereEqualTo("name", name)
                .whereEqualTo("surname", surname)
                .get().await()

            val objectList = response.toObjects<ProfessionalRemote>()

            Professional(objectList[0])
        } catch(e: Exception) {
            null
        }
    }

    override suspend fun getProfessionalProfileImageUrl(idProfessional: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun getProfessionalContactList(idProfessional: String): List<Contact> {
        TODO("Not yet implemented")
    }

    override suspend fun getProfessionalExperienceList(idProfessional: String): List<Experience> {
        TODO("Not yet implemented")
    }

    override suspend fun getProfessionalStudyList(idProfessional: String): List<Study> {
        TODO("Not yet implemented")
    }

    override suspend fun insertProfessional(professional: Professional) {
        TODO("Not yet implemented")
    }
}