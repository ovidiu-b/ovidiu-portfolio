package com.ovidiu.portfolio.architecture.model.data_source.remote.data_access

import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional

class ProfessionalRemoteDataAccess(private val retrofit: String): ProfessionalDataAccess {
    override suspend fun getProfessionalByCompleteName(completeName: String): Professional {
        TODO("Not yet implemented")
    }
}