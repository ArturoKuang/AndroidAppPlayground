package com.example.interviewpractice.manual_di.ui

import com.example.interviewpractice.manual_di.network.HealthData

sealed class HealthUiState {
    object Loading: HealthUiState()

    data class HealthPage(val healthData: List<HealthData>): HealthUiState()

    data class Error(val errorMessage: String): HealthUiState()
}