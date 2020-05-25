package com.ovidiu.portfolio.architecture.model.data_source.local.data_access

import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.architecture.model.data_source.local.dao.ProfessionalDao
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfessionalLocalDataAccess @Inject constructor(
    private val professionalDao: ProfessionalDao,
    private val ioDispatcher: CoroutineDispatcher
) : ProfessionalDataAccess {

    override suspend fun getProfessionalByNameAndSurname(
        name: String,
        surname: String
    ): Professional = withContext(ioDispatcher) {
        return@withContext professionalDao.getByNameAndSurname(name, surname)
    }
}