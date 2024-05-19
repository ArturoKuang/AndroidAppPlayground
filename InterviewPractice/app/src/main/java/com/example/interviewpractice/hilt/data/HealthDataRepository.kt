package com.example.interviewpractice.hilt.data

import com.example.interviewpractice.hilt.network.HealthDataService
import com.example.interviewpractice.manual_di.network.HealthResponse
import javax.inject.Inject


enum class HealthType {
    STRONG, WEAK, DEAD
}

data class UserHealthData(
    val name: String,
    val age: Int,
    val image: String,
    val type: HealthType,
)

interface HealthDataRepository {
    suspend fun getUserHealthData(): List<UserHealthData>
}

class HealthDataRepositoryImpl @Inject constructor(private val healthDataService: HealthDataService) :
    HealthDataRepository {
    override suspend fun getUserHealthData(): List<UserHealthData> {
        return healthDataService.getHealthData().toUserHealthData()
    }
}

fun HealthResponse.toUserHealthData(): List<UserHealthData> {
    return this.map {
        val healthType = if (it.name.contains("a")) {
            HealthType.STRONG
        } else if (it.name.contains("John")) {
            HealthType.WEAK
        } else {
            HealthType.DEAD
        }

        UserHealthData(name = it.name, age = it.age, image = it.image, type = healthType)
    }
}