package com.ovidiu.portfolio.architecture.model.data_source.local.data_access

import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.local.dao.ProfessionalDao
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ProfessionalLocalDataAccess @Inject constructor(
    private val professionalDao: ProfessionalDao,
    private val ioDispatcher: CoroutineDispatcher
) : ProfessionalDataAccess {
    override suspend fun getProfessionalByCompleteName(completeName: String): Professional {
        TODO("Not yet implemented")
    }
}