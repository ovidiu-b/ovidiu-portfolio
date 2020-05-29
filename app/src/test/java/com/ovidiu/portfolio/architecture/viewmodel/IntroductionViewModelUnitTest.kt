package com.ovidiu.portfolio.architecture.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.ovidiu.portfolio.architecture.LiveDataTestUtil
import com.ovidiu.portfolio.architecture.MainCoroutineRule
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.ContactType
import com.ovidiu.portfolio.support.test.FakeProfessionalRepositoryData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class IntroductionViewModelUnitTest {
    private lateinit var sut: IntroductionViewModel

    private lateinit var professionalRepository: FakeProfessionalRepositoryData

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val professionalName = "Ovidiu"
    private val professionalSurname = "Balaban"

    @Before
    fun setup() {
        professionalRepository = FakeProfessionalRepositoryData()
        sut = IntroductionViewModel(professionalRepository)
    }

    @Test
    fun loadProfessionalFromRepository_professionalNameAndSurnameMatches() {
        loadProfessionalFromRepository()

        assertThat(LiveDataTestUtil.getValue(sut.professional).name).isEqualTo(professionalName)
        assertThat(LiveDataTestUtil.getValue(sut.professional).surname).isEqualTo(professionalSurname)
    }

    @Test
    fun loadProfessionalFromRepository_professionalImageUrlNotNullOrEmpty() {
        loadProfessionalFromRepository()

        assertThat(LiveDataTestUtil.getValue(sut.profileImageUrl)).isNotNull()
        assertThat(LiveDataTestUtil.getValue(sut.profileImageUrl)).isNotEmpty()
    }

    // The professional must have at least 4 contact types such as Phone, Email, Github and LinkedIn
    @Test
    fun loadProfessionalFromRepository_professionalSocialMediaPhoneEmailGithubLinkedin() {
        loadProfessionalFromRepository()

        val contactList = LiveDataTestUtil.getValue(sut.contactList)

        val phone = contactList.first { it.contactType == ContactType.PHONE.type }
        val email = contactList.first { it.contactType== ContactType.EMAIL.type }
        val github = contactList.first { it.contactType == ContactType.GITHUB.type }
        val linkedin = contactList.first { it.contactType == ContactType.LINKEDIN.type }

        assertThat(phone).isNotNull()
        assertThat(email).isNotNull()
        assertThat(github).isNotNull()
        assertThat(linkedin).isNotNull()
    }

    private fun loadProfessionalFromRepository() {
        sut.loadProfessionalByNameAndSurname(professionalName, professionalSurname)
    }
}
