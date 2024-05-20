package com.example.interviewpractice

import android.app.Application
import com.example.interviewpractice.hilt.data.HealthDataRepository
import com.example.interviewpractice.manual_di.di.AppContainer
import com.example.interviewpractice.manual_di.network.ItemBody
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant
import javax.inject.Inject


@HiltAndroidApp
class MainApplication : Application() {

    val appContainer = AppContainer()

    @Inject
    lateinit var healthDataRepository: HealthDataRepository

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }

        runBlocking {
            Timber.d(
                healthDataRepository.getItems(
                    mapOf("q" to "1", "sort_by" to "2"),
                    "1000",
                    ItemBody(
                        "testing",
                        "des",
                        1f,
                        1f
                    )
                ).toString()
            )
        }
    }
}