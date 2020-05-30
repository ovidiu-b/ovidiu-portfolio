package com.ovidiu.portfolio.architecture.model.repository

import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Contact
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Experience
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Study

interface ProfessionalRepository {
    suspend fun getProfessionalByNameAndSurname(name: String, surname: String): Professional

    suspend fun getProfessionalProfileImageUrl(idProfessional: String): String

    suspend fun getProfessionalContactList(idProfessional: String): List<Contact>

    suspend fun getProfessionalExperienceList(idProfessional: String): List<Experience>

    suspend fun getProfessionalStudyList(idProfessional: String): List<Study>
}