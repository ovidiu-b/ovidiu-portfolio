package com.ovidiu.portfolio.architecture.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.ovidiu.portfolio.architecture.LiveDataTestUtil
import com.ovidiu.portfolio.architecture.MainCoroutineRule
import com.ovidiu.portfolio.architecture.model.repository.FakeProfessionalRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Before
import org.junit.Rule

class IntroductionViewModelUnitTest {
    private lateinit var sut: IntroductionViewModel

    private lateinit var professionalRepository: FakeProfessionalRepository

    @ExperimentalCoroutinesApi
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

        assertThat(LiveDataTestUtil.getValue(sut.professionalLiveData).name).isEqualTo(professionalName)

        assertThat(LiveDataTestUtil.getValue(sut.professionalLiveData).surname).isEqualTo(professionalSurname)
    }
}
