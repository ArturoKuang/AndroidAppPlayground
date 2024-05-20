package com.example.interviewpractice.manual_di.ui

import app.cash.turbine.test
import com.example.interviewpractice.fakes.FakeHealthRepository
import com.example.interviewpractice.manual_di.network.HealthData
import com.google.common.truth.Truth

class HealthViewModelRobot {

    private val fakeHealthRepository = FakeHealthRepository()
    private lateinit var healthViewModel: HealthViewModel
    fun buildViewModel() = apply {
        healthViewModel = HealthViewModel(
            healthRepository =  fakeHealthRepository
        )
    }

    fun mockGetHealthData(healthData: List<HealthData>) = apply {
        fakeHealthRepository.healthDataResults = healthData
    }

    fun mockGetHealthDataThrowHttpException(healthDataException: Exception) = apply {
        fakeHealthRepository.healthDataException = healthDataException
    }

    fun loadHealthData() = apply {
        healthViewModel.loadHealthData()
    }

    fun assertViewState(expected: HealthUiState) = apply {
        Truth.assertThat(expected).isEqualTo(healthViewModel.uiState.value)
    }

    suspend fun assertViewStates(
        action: HealthViewModelRobot.() -> Unit,
        viewStates: List<HealthUiState>,
    ) = apply {
        healthViewModel.uiState.test {
            action()

            for (state in viewStates) {
                val actual = awaitItem()
                Truth.assertThat(actual).isEqualTo(state)
            }

            this.cancel()
        }
    }
}