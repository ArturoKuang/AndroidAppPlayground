package com.example.interviewpractice.hilt.data

import com.example.interviewpractice.hilt.network.HealthDataService
import com.example.interviewpractice.manual_di.network.HealthResponse


data class UserHealthData(
    val name: String,
    val age: Int,
    val image: String
)

interface HealthDataRepository {
    suspend fun getUserHealthData(): List<UserHealthData>
}

class HealthDataRepositoryImpl(private val healthDataService: HealthDataService) :
    HealthDataRepository {
    override suspend fun getUserHealthData(): List<UserHealthData> {
        return healthDataService.getHealthData().toUserHealthData()
    }
}


fun HealthResponse.toUserHealthData(): List<UserHealthData> {
    return this.map {
        UserHealthData(name = it.name, age = it.age, image = it.image)
    }
}