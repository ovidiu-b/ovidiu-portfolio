package com.ovidiu.portfolio.architecture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Contact
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Experience
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Study
import com.ovidiu.portfolio.architecture.model.repository.ProfessionalRepository
import com.ovidiu.portfolio.support.asLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfessionalViewModel @Inject constructor(private val repository: ProfessionalRepository) : ViewModel() {

    private val _professional = MutableLiveData<Professional>()
    val professional = _professional.asLiveData()

    private val _profileImageUrl = MutableLiveData<String?>()
    val profileImageUrl = _profileImageUrl.asLiveData()

    private val _contactList = MutableLiveData<List<Contact>>()
    val contactList = _contactList.asLiveData()

    private val _experienceList = MutableLiveData<List<Experience>>()
    val experienceList = _experienceList.asLiveData()

    private val _studyList = MutableLiveData<List<Study>>()
    val studyList = _studyList.asLiveData()

    fun loadProfessionalByNameAndSurname(professionalName: String, professionalSurname: String) =
        viewModelScope.launch {
            val professional: Professional? =
                repository.getProfessionalByNameAndSurname(professionalName, professionalSurname)

            _professional.value = professional

            loadProfileImageUrl()
            loadContactList()
            loadExperienceList()
            loadStudyList()
        }

    private suspend fun loadProfileImageUrl() {
        _profileImageUrl.value = repository.getProfessionalImage(_professional.value!!.id)?.url
    }

    private suspend fun loadContactList() {
        _contactList.value = repository.getProfessionalContactList(_professional.value!!.id)
    }

    private suspend fun loadExperienceList() {
        _experienceList.value = repository.getProfessionalExperienceList(_professional.value!!.id)
    }

    private suspend fun loadStudyList() {
        _studyList.value = repository.getProfessionalStudyList(_professional.value!!.id)
    }
}