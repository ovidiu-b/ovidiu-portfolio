package com.ovidiu.portfolio.architecture.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.ovidiu.portfolio.architecture.LiveDataTestUtil
import com.ovidiu.portfolio.architecture.MainCoroutineRule
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.ContactType
import com.ovidiu.portfolio.support.test.FakeProfessionalRepositoryData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProfessionalViewModelUnitTest {
    private lateinit var sut: ProfessionalViewModel

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
        sut = ProfessionalViewModel(professionalRepository)
    }

    @Test
    fun loadProfessionalFromRepository_nameAndSurnameMatches() {
        loadProfessionalFromRepository()

        assertThat(LiveDataTestUtil.getValue(sut.professional)?.name).isEqualTo(professionalName)
        assertThat(LiveDataTestUtil.getValue(sut.professional)?.surname).isEqualTo(professionalSurname)
    }

    @Test
    fun loadProfessionalFromRepository_professionalLoaded() {
        assertThat(LiveDataTestUtil.getValue(sut.professionalLoaded)).isNull()

        loadProfessionalFromRepository()

        assertThat(LiveDataTestUtil.getValue(sut.professionalLoaded)).isTrue()
    }

    @Test
    fun loadProfessionalFromRepository_imageUrlNotNullOrEmpty() {
        loadProfessionalFromRepository()

        assertThat(LiveDataTestUtil.getValue(sut.profileImageUrl)).isNotNull()
        assertThat(LiveDataTestUtil.getValue(sut.profileImageUrl)).isNotEmpty()
    }

    // The professional must have at least 4 contact types such as Phone, Email, Github and LinkedIn
    @Test
    fun loadProfessionalFromRepository_socialMediaPhoneEmailGithubLinkedin() {
        loadProfessionalFromRepository()

        val contactList = LiveDataTestUtil.getValue(sut.contactList)

        val phone = contactList?.first { it.contactType == ContactType.PHONE.type }
        val email = contactList?.first { it.contactType== ContactType.EMAIL.type }
        val github = contactList?.first { it.contactType == ContactType.GITHUB.type }
        val linkedin = contactList?.first { it.contactType == ContactType.LINKEDIN.type }

        assertThat(phone).isNotNull()
        assertThat(email).isNotNull()
        assertThat(github).isNotNull()
        assertThat(linkedin).isNotNull()
    }

    @Test
    fun loadProfessionalFromRepository_experienceListNotNullOrEmpty() {
        loadProfessionalFromRepository()

        assertThat(LiveDataTestUtil.getValue(sut.experienceList)).isNotNull()
        assertThat(LiveDataTestUtil.getValue(sut.experienceList)).isNotEmpty()
    }

    @Test
    fun loadProfessionalFromRepository_studyListNotNullOrEmpty() {
        loadProfessionalFromRepository()

        assertThat(LiveDataTestUtil.getValue(sut.studyList)).isNotNull()
        assertThat(LiveDataTestUtil.getValue(sut.studyList)).isNotEmpty()
    }

    private fun loadProfessionalFromRepository() {
        sut.loadProfessionalByNameAndSurname(professionalName, professionalSurname)
    }
}