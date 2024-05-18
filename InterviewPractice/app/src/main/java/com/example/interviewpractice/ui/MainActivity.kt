package com.example.interviewpractice.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.interviewpractice.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    private val healthViewModel: HealthViewModel by viewModels { HealthViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        healthViewModel.loadHealthData()
        setContent {
            MyTheme {
                val healthUiState = healthViewModel.uiState.collectAsState()
                MainPage(healthUiState = healthUiState.value)
            }
        }
    }
}

@Composable
fun MainPage(healthUiState: HealthUiState) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "LoginPage") {
        composable("HealthPage", enterTransition = { slideInHorizontally() }) {
            HealthPage(
                healthUiState = healthUiState,
                onNavigateToSettingsDialog = { navController.navigate("Settings") })
        }
        composable(
            "LoginPage",
            exitTransition = { fadeOut() }) {
            LoginPage(onNavigateToHealthPage = {
                navController.navigate(
                    "HealthPage"
                )
            })
        }
        dialog("Settings") { Settings() }
    }
}


@Composable
fun Settings() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "Settings ::::::::::::",
        color = MaterialTheme.colors.onPrimary
    )
}

@Composable
fun LoginPage(onNavigateToHealthPage: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = onNavigateToHealthPage) {
            Text(text = "Login")
        }
    }
}

@Composable
fun HealthPage(healthUiState: HealthUiState, onNavigateToSettingsDialog: () -> Unit) {
    when (healthUiState) {
        is HealthUiState.Error -> HealthDataErrorPage(healthPage = healthUiState)
        is HealthUiState.HealthPage -> HealthDataListPage(healthPage = healthUiState)
        is HealthUiState.Loading -> HealthLoadingPage(healthPage = healthUiState)
    }

    Button(onClick = onNavigateToSettingsDialog) {
        Text(text = "Settings")
    }
}

@Composable
fun HealthDataListPage(healthPage: HealthUiState.HealthPage) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(healthPage.healthData) { healthData ->
            Text(text = healthData.toString(), color = MaterialTheme.colors.primary)
            AsyncImage(
                model = healthData.image,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun HealthDataErrorPage(healthPage: HealthUiState.Error) {
    Text(text = "Error")
}

@Composable
fun HealthLoadingPage(healthPage: HealthUiState.Loading) {
    Text(text = "Loading...")
}


@Composable
@Preview
fun HealthPagePreview() {

}
