package com.ovidiu.portfolio.architecture.model.repository

import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Contact
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional

interface ProfessionalRepository {
    suspend fun getProfessionalByNameAndSurname(name: String, surname: String): Professional

    suspend fun getProfessionalProfileImageUrl(idProfessional: String): String

    suspend fun getProfessionalContactList(idProfessional: String): List<Contact>
}