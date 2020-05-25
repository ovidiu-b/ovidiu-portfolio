package com.ovidiu.portfolio.architecture.model.repository

import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional

interface ProfessionalRepository {
    suspend fun getProfessionalByNameAndSurname(name: String, surname: String): Professional
}