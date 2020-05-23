package com.ovidiu.portfolio.architecture.model.repository

import com.ovidiu.portfolio.architecture.model.data_source.common.ProfessionalDataAccess
import com.ovidiu.portfolio.di.modules.ApplicationModule.ProfessionalRemoteDataAccess
import com.ovidiu.portfolio.di.modules.ApplicationModule.ProfessionalLocalDataAccess
import javax.inject.Inject

class ProfessionalRepository @Inject constructor(
    @ProfessionalLocalDataAccess private val localRepository: ProfessionalDataAccess,
    @ProfessionalRemoteDataAccess private val remoteRepository: ProfessionalDataAccess
)