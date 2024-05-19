package com.example.interviewpractice.manual_di.network

import kotlinx.coroutines.delay


data class HealthData(
    val age: Int,
    val name: String,
    val weight: Int,
    val image: String
)

interface HealthRepository {
    suspend fun getHealthData(): List<HealthData>
}

class HealthRepositoryImpl(
    private val healthDataRemoteSource: HealthDataRemoteSource
): HealthRepository {
    override suspend fun getHealthData(): List<HealthData> {
        return healthDataRemoteSource.getHealthData().map { it.toHealthData() }
    }
}


fun HealthResponseItem.toHealthData(): HealthData {
    return HealthData(age, name, weight, image)
}