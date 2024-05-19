package com.example.interviewpractice

import android.app.Application
import com.example.interviewpractice.hilt.data.HealthDataRepository
import com.example.interviewpractice.manual_di.di.AppContainer
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import timber.log.Timber.*
import timber.log.Timber.Forest.plant
import javax.inject.Inject


@HiltAndroidApp
class MainApplication : Application() {

    val appContainer = AppContainer()

    @Inject private lateinit var healthDataRepository: HealthDataRepository

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }
        runBlocking {
            Timber.d(healthDataRepository.getUserHealthData())
        }
    }
}