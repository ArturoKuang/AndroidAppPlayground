package com.example.interviewpractice.manual_di.ui

import com.example.interviewpractice.CoroutinesTestRule
import com.example.interviewpractice.manual_di.network.HealthData
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class HealthViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private val healthViewModelRobot = HealthViewModelRobot()

    @Test
    fun initialUiState() {
        val expectedViewState = HealthUiState.Loading
        healthViewModelRobot
            .buildViewModel()
            .assertViewState(expectedViewState)
    }

    @Test
    fun loadHealthDataSuccess() {
        val mockHealthData = listOf(
            HealthData(
                age = 11,
                name = "John",
                weight = 100,
                image = "https://picsum.photos/id/0/5000/3333"
            )
        )

        val expectedViewStates = listOf(
            HealthUiState.Loading,
            HealthUiState.HealthPage(healthData = mockHealthData)
        )

        runTest {
            healthViewModelRobot
                .buildViewModel()
                .mockGetHealthData(mockHealthData)
                .assertViewStates(
                    action = {
                        loadHealthData()
                    },
                    viewStates = expectedViewStates
                )
        }
    }

    @Test
    fun loadHealthDataError() {
        val errorMessage = ""
        val mockException = HttpException(Response.error<Any>(400, errorMessage.toResponseBody()))
        val expectedViewStates = listOf(
            HealthUiState.Loading,
            HealthUiState.Error("HTTP 400 Response.error()")
        )

        runTest {
            healthViewModelRobot
                .buildViewModel()
                .mockGetHealthDataThrowHttpException(mockException)
                .assertViewStates(
                    action = {
                        loadHealthData()
                    },
                    viewStates = expectedViewStates
                )
        }
    }
}