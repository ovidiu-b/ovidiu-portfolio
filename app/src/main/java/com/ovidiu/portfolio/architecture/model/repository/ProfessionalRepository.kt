package com.ovidiu.portfolio.architecture.model.repository

import com.ovidiu.portfolio.architecture.model.data_source.local.entity.*

interface ProfessionalRepository {
    suspend fun getProfessionalByNameAndSurname(name: String, surname: String): Result<Professional?>

    suspend fun getProfessionalImage(idProfessional: String): Image?

    suspend fun getProfessionalContactList(idProfessional: String): List<Contact>?

    suspend fun getProfessionalExperienceList(idProfessional: String): List<Experience>?

    suspend fun getProfessionalStudyList(idProfessional: String): List<Study>?
}