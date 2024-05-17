package com.example.interviewpractice.network


data class HealthData(
    private val age: Int,
    private val name: String,
    private val weight: Int
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
    return HealthData(age, name, weight)
}