package com.example.interviewpractice.hilt.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.interviewpractice.hilt.data.HealthType
import com.example.interviewpractice.hilt.data.UserHealthData
import com.example.interviewpractice.manual_di.ui.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class HealthActivity : AppCompatActivity() {

    private val healthViewModel: HealthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        healthViewModel.loadData()
        setContent {
            MyTheme {
                val uiState = healthViewModel.uiState.collectAsState()
                Timber.d(uiState.toString())
                HealthPage(healthDataUiState = uiState.value)
            }
        }
    }

    @Composable
    fun HealthPage(healthDataUiState: HealthDataUiState) {
        Column {
            Text(text = "Hilt")
            when (healthDataUiState) {
                is HealthDataUiState.Error -> HealthErrorPage(errorMessage = healthDataUiState.errorMessage)
                is HealthDataUiState.HealthPage -> HealthListPage(healthPage = healthDataUiState)
                HealthDataUiState.Loading -> HealthLoading()
            }
        }
    }

    @Composable
    fun HealthErrorPage(errorMessage: String) {
        Text(text = errorMessage)
    }

    @Composable
    fun HealthLoading() {
        Text(text = "Loading.........")
    }

    @Composable
    fun HealthListPage(healthPage: HealthDataUiState.HealthPage) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(healthPage.usersHealthData) { healthData ->
                when (healthData.type) {
                    HealthType.STRONG -> HealthStrong(healthData)
                    HealthType.WEAK -> HealthWeak(healthData)
                    HealthType.DEAD -> HealthDead(healthData)
                }
            }
        }
    }

    @Composable
    fun HealthWeak(healthData: UserHealthData) {
        Text(text = "Weak")
        Text(text = healthData.toString(), color = MaterialTheme.colors.primary)
        AsyncImage(
            model = healthData.image,
            contentDescription = null,
        )
    }

    @Composable
    fun HealthStrong(healthData: UserHealthData) {
        Text(text = "Strong", style = MaterialTheme.typography.body1)
        Text(text = healthData.toString(), color = MaterialTheme.colors.primary)
        AsyncImage(
            model = healthData.image,
            contentDescription = null,
        )
    }

    @Composable
    fun HealthDead(healthData: UserHealthData) {
        Text(text = "Dead")
        Text(text = healthData.toString(), color = MaterialTheme.colors.primary)
        AsyncImage(
            model = healthData.image,
            contentDescription = null,
        )
    }
}