package com.ovidiu.portfolio.architecture.model.data_source.common

import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional

interface ProfessionalDataAccess {
    suspend fun getProfessionalByCompleteName(completeName: String): Professional
}