package com.example.interviewpractice

import android.app.Application
import com.example.interviewpractice.di.AppContainer
import timber.log.Timber.*
import timber.log.Timber.Forest.plant


class MainApplication : Application() {

    val appContainer = AppContainer()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }
    }
}