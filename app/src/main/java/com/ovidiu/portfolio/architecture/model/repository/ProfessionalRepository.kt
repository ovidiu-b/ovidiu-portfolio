package com.ovidiu.portfolio.architecture.model.repository

import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional

interface ProfessionalRepository {
    fun getProfessionalByNameAndSurname(professionalName: String, professionalSurname: String): Professional
}