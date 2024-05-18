package com.example.interviewpractice.ui

import com.example.interviewpractice.network.HealthData

sealed class HealthUiState {
    object Loading: HealthUiState()

    data class HealthPage(val healthData: List<HealthData>): HealthUiState()

    data class Error(val errorMessage: String): HealthUiState()
}