package com.ovidiu.portfolio.architecture.viewmodel

import androidx.lifecycle.ViewModel
import com.ovidiu.portfolio.architecture.model.repository.ProfessionalRepository
import javax.inject.Inject

class ProfessionalViewModel @Inject constructor(private val repository: ProfessionalRepository) :
    ViewModel() {
    private var counter: Int = 0

    fun getMessage(): Int {
        return ++counter
    }
}