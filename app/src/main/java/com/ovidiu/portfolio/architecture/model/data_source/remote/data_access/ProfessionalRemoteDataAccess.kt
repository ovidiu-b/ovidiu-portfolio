package com.ovidiu.portfolio.architecture.model.data_source.remote.data_access

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.*
import com.ovidiu.portfolio.architecture.model.data_source.remote.entity.*
import com.ovidiu.portfolio.architecture.model.repository.Result
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class ProfessionalRemoteDataAccess @Inject constructor(private val firestore: FirebaseFirestore) :
    ProfessionalDataAccess
{
    override suspend fun getProfessionalByNameAndSurname(
        name: String,
        surname: String
    ): Result<Professional?> {
        return try {
            val response = firestore.collection("Professional")
                .whereEqualTo("name", name)
                .whereEqualTo("surname", surname)
                .get().await()

            val objectList = response.toObjects<ProfessionalRemote>()

            Result.Success(Professional(objectList[0]))
        } catch(e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun insertProfessional(professional: Professional) {
        // We are not gonna write any data to the server from the app
        // I want to keep it as simple as possible
    }

    override suspend fun getImage(idProfessional: String): Image? {
        return try {
            val response = firestore.collection("Image")
                .whereEqualTo("professionalId", idProfessional)
                .get().await()

            val objectList = response.toObjects<ImageRemote>()

            Image(objectList[0])
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun insertImage(image: Image) {
        // We are not gonna write any data to the server from the app
        // I want to keep it as simple as possible
    }

    override suspend fun getContactList(idProfessional: String): List<Contact>? {
        return try {
            val response = firestore.collection("Contact")
                .whereEqualTo("professionalId", idProfessional)
                .get().await()

            val objectList = response.toObjects<ContactRemote>()

            val contactList = mutableListOf<Contact>()

            objectList.map {
                contactList.add(Contact(it))
            }

            contactList
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun insertContactList(contactList: List<Contact>) {
        // We are not gonna write any data to the server from the app
        // I want to keep it as simple as possible
    }

    override suspend fun getExperienceList(idProfessional: String): List<Experience>? {
        return try {
            val response = firestore.collection("Experience")
                .whereEqualTo("professionalId", idProfessional)
                .get().await()

            val objectList = response.toObjects<ExperienceRemote>()

            val experienceList = mutableListOf<Experience>()

            objectList.map {
                experienceList.add(Experience(it))
            }

            experienceList
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun insertExperienceList(experienceList: List<Experience>) {
        // We are not gonna write any data to the server from the app
        // I want to keep it as simple as possible
    }

    override suspend fun getStudyList(idProfessional: String): List<Study>? {
        return try {
            val response = firestore.collection("Study")
                .whereEqualTo("professionalId", idProfessional)
                .get().await()

            val objectList = response.toObjects<StudyRemote>()

            val studyList = mutableListOf<Study>()

            objectList.map {
                studyList.add(Study(it))
            }

            studyList
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun insertStudyList(studyList: List<Study>) {
        // We are not gonna write any data to the server from the app
        // I want to keep it as simple as possible
    }
}