package com.ovidiu.portfolio.architecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Contact
import com.ovidiu.portfolio.architecture.model.repository.ProfessionalRepository
import com.ovidiu.portfolio.support.asLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

class IntroductionViewModel @Inject constructor(private val repository: ProfessionalRepository) : ViewModel()
{
    private val _professional = MutableLiveData<Professional>()
    val professional: LiveData<Professional> = _professional.asLiveData()

    private val _profileImageUrl = MutableLiveData<String>()
    val profileImageUrl: LiveData<String> = _profileImageUrl.asLiveData()

    private val _socialMediaList = MutableLiveData<List<Contact>>()
    val contactList: LiveData<List<Contact>> = _socialMediaList.asLiveData()

    fun loadProfessionalByNameAndSurname(professionalName: String, professionalSurname: String) = viewModelScope.launch {
        val professional: Professional = repository.getProfessionalByNameAndSurname(professionalName, professionalSurname)

        _professional.value = professional

        loadProfileImageUrl()
        loadContactList()
    }

    private suspend fun loadProfileImageUrl() {
        _profileImageUrl.value = repository.getProfessionalProfileImageUrl(_professional.value!!.id)
    }

    private suspend fun loadContactList() {
        _socialMediaList.value = repository.getProfessionalContactList(_professional.value!!.id)
    }
}