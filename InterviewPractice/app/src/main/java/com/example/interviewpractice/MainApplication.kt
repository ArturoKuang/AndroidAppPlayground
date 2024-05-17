package com.example.interviewpractice

import android.app.Application
import android.util.Log
import com.example.interviewpractice.di.AppContainer
import kotlinx.coroutines.runBlocking

class MainApplication : Application() {

    val appContainer = AppContainer()

    override fun onCreate() {
        super.onCreate()
        runBlocking {
            Log.d("Application","TESTING 0: ${appContainer.healthRepository.getHealthData()}")
        }
    }
}