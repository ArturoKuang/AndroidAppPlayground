package com.example.interviewpractice.manual_di.di

import com.example.interviewpractice.manual_di.network.HealthDataRemoteSource
import com.example.interviewpractice.manual_di.network.HealthDataRemoteSourceImpl
import com.example.interviewpractice.manual_di.network.HealthDataService
import com.example.interviewpractice.manual_di.network.HealthRepository
import com.example.interviewpractice.manual_di.network.HealthRepositoryImpl
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer {

    companion object {
        const val LOCAL_HOST = "http://192.168.0.12:8000/"
    }

    private val gson = GsonBuilder().create()
    private val logging = HttpLoggingInterceptor()
    private val client = OkHttpClient()
        .newBuilder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(LOCAL_HOST)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val healthDataService = retrofit.create(HealthDataService::class.java)

    val healthDataRemoteSource: HealthDataRemoteSource =
        HealthDataRemoteSourceImpl(healthDataService)

    val healthRepository: HealthRepository =
        HealthRepositoryImpl(healthDataRemoteSource)
}