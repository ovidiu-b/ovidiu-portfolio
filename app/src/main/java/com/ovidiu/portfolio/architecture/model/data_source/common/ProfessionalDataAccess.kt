package com.ovidiu.portfolio.architecture.model.data_source.common

import com.ovidiu.portfolio.architecture.model.data_source.local.entity.*
import com.ovidiu.portfolio.architecture.model.repository.Result

interface ProfessionalDataAccess {
    suspend fun getProfessionalByNameAndSurname(name: String, surname: String): Result<Professional?>

    suspend fun insertProfessional(professional: Professional)

    suspend fun getImage(idProfessional: String): Image?

    suspend fun insertImage(image: Image)

    suspend fun getContactList(idProfessional: String): List<Contact>?

    suspend fun insertContactList(contactList: List<Contact>)

    suspend fun getExperienceList(idProfessional: String): List<Experience>?

    suspend fun insertExperienceList(experienceList: List<Experience>)

    suspend fun getStudyList(idProfessional: String): List<Study>?

    suspend fun insertStudyList(studyList: List<Study>)
}