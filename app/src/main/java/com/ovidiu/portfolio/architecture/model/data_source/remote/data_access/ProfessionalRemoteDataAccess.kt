package com.ovidiu.portfolio.architecture.model.data_source.remote.data_access

import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import com.ovidiu.portfolio.architecture.model.data_source.remote.api_rest.ProfessionalApiRest
import javax.inject.Inject

class ProfessionalRemoteDataAccess @Inject constructor(private val apiRest: ProfessionalApiRest) :
    ProfessionalDataAccess {
    override suspend fun getProfessionalByCompleteName(completeName: String): Professional {
        TODO("Not yet implemented")
    }
}