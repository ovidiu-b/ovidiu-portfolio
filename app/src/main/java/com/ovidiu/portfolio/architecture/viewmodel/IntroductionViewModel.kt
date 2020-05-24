package com.ovidiu.portfolio.architecture.viewmodel

import androidx.lifecycle.ViewModel
import com.ovidiu.portfolio.architecture.model.repository.ProfessionalRepository
import javax.inject.Inject

class IntroductionViewModel @Inject constructor(private val repository: ProfessionalRepository) :
    ViewModel()
{

}