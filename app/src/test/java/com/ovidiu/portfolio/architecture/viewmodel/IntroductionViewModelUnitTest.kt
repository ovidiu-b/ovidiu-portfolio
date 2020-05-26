package com.ovidiu.portfolio.architecture.viewmodel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.ovidiu.portfolio.architecture.LiveDataTestUtil
import com.ovidiu.portfolio.architecture.MainCoroutineRule
import com.ovidiu.portfolio.architecture.model.repository.FakeProfessionalRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class IntroductionViewModelUnitTest {
    private lateinit var sut: IntroductionViewModel

    private lateinit var professionalRepository: FakeProfessionalRepository

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val professionalName = "Ovidiu"
    private val professionalSurname = "Balaban"

    @Before
    fun setup() {
        professionalRepository = FakeProfessionalRepository()
        sut = IntroductionViewModel(professionalRepository)
    }

    @Test
    fun loadProfessionalFromRepository_professionalNameAndSurnameMatches() {
        sut.loadProfessionalByNameAndSurname(professionalName, professionalSurname)

        assertThat(LiveDataTestUtil.getValue(sut.professional).name).isEqualTo(professionalName)
        assertThat(LiveDataTestUtil.getValue(sut.professional).surname).isEqualTo(professionalSurname)
    }

    @Test
    fun loadProfessionalFromRepository_professionalImageUrlNotNullOrEmpty() {
        sut.loadProfessionalByNameAndSurname(professionalName, professionalSurname)

        assertThat(LiveDataTestUtil.getValue(sut.profileImageUrl)).isNotNull()
        assertThat(LiveDataTestUtil.getValue(sut.profileImageUrl)).isNotEmpty()
    }

    // The professional must have at least 4 contact types such as Phone, Email, Github and LinkedIn
    @Test
    fun loadProfessionalFromRepository_professionalSocialMediaPhoneEmailGithubLinkedin() {
        sut.loadProfessionalByNameAndSurname(professionalName, professionalSurname)

        val contactList = LiveDataTestUtil.getValue(sut.contactList)

        val phone = contactList.first { it.contactType == "Phone" }
        val email = contactList.first { it.contactType== "Email" }
        val github = contactList.first { it.contactType == "Github" }
        val linkeding = contactList.first { it.contactType == "Linkedin" }

        assertThat(phone).isNotNull()
        assertThat(email).isNotNull()
        assertThat(github).isNotNull()
        assertThat(linkeding).isNotNull()
    }
}
