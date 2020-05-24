package com.ovidiu.portfolio.architecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import com.ovidiu.portfolio.architecture.model.repository.ProfessionalRepository
import com.ovidiu.portfolio.support.asLiveData
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class IntroductionViewModel @Inject constructor(private val repository: ProfessionalRepository) : ViewModel()
{
    private val professionalMutableLiveData = MutableLiveData<Professional>()
    val professionalLiveData: LiveData<Professional> = professionalMutableLiveData.asLiveData()

    fun loadProfessionalByNameAndSurname(professionalName: String, professionalSurname: String) = viewModelScope.launch {
        val professional: Professional = repository.getProfessionalByNameAndSurname(professionalName, professionalSurname)
    }

}