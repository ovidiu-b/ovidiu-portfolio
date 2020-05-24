package com.ovidiu.portfolio.architecture.viewmodel

import androidx.lifecycle.ViewModel
import com.ovidiu.portfolio.architecture.model.repository.DefaultProfessionalRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val repository: DefaultProfessionalRepository) :
    ViewModel() {
}