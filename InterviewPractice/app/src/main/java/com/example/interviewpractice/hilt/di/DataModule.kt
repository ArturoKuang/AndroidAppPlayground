package com.example.interviewpractice.hilt.di

import com.example.interviewpractice.hilt.data.HealthDataRepository
import com.example.interviewpractice.hilt.data.HealthDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindsHealthDataRepository(healthDataRepositoryImpl: HealthDataRepositoryImpl): HealthDataRepository
}