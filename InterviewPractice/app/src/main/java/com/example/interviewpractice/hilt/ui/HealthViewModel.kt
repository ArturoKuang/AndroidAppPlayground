package com.example.interviewpractice.hilt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewpractice.hilt.data.HealthDataRepository
import com.example.interviewpractice.hilt.data.UserHealthData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HealthViewModel @Inject constructor(
    private val healthDataRepository: HealthDataRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<HealthDataUiState> = MutableStateFlow(HealthDataUiState.Loading)
    val uiState: StateFlow<HealthDataUiState> = _uiState

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = HealthDataUiState.Loading
            try {
                _uiState.value = HealthDataUiState.HealthPage(
                    usersHealthData = healthDataRepository.getUserHealthData()
                )
            } catch (e: Exception) {
                _uiState.value = HealthDataUiState.Error(e.message ?: "")
            }
        }
    }
}

sealed class HealthDataUiState {
    object Loading: HealthDataUiState()

    data class HealthPage(val usersHealthData: List<UserHealthData>): HealthDataUiState()

    data class Error(val errorMessage: String): HealthDataUiState()
}
