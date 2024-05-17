package com.example.interviewpractice.network

interface HealthDataRemoteSource {
    suspend fun getHealthData(): List<HealthResponseItem>
}


class HealthDataRemoteSourceImpl(
    private val healthDataService: HealthDataService
) : HealthDataRemoteSource {
    override suspend fun getHealthData(): List<HealthResponseItem> {
        return healthDataService.getList()
    }
}