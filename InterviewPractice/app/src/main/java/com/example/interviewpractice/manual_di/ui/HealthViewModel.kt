package com.example.interviewpractice.manual_di.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.interviewpractice.MainApplication
import com.example.interviewpractice.manual_di.network.HealthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HealthViewModel(private val healthRepository: HealthRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<HealthUiState> = MutableStateFlow(HealthUiState.Loading)
    val uiState: StateFlow<HealthUiState> = _uiState


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val healthRepository =
                    (this[APPLICATION_KEY] as MainApplication).appContainer.healthRepository

                HealthViewModel(healthRepository = healthRepository)
            }
        }
    }

    fun loadHealthData() {
        viewModelScope.launch {
            _uiState.value = HealthUiState.Loading
            try {
                val healthData = healthRepository.getHealthData()
                _uiState.value = HealthUiState.HealthPage(healthData = healthData)
            } catch (e: Exception) {
                _uiState.value = HealthUiState.Error(e.message ?: "")
            }
        }
    }
}