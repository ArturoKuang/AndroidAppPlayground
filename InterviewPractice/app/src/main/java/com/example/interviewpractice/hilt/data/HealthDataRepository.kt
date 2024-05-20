package com.example.interviewpractice.hilt.data

import com.example.interviewpractice.hilt.network.HealthDataService
import com.example.interviewpractice.manual_di.network.HealthResponse
import com.example.interviewpractice.manual_di.network.ItemBody
import com.example.interviewpractice.manual_di.network.ItemResponse
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
    suspend fun getItems(
        queryParams: Map<String, String>,
        itemId: String,
        body: ItemBody
    ): ItemResponse
}

class HealthDataRepositoryImpl @Inject constructor(private val healthDataService: HealthDataService) :
    HealthDataRepository {
    override suspend fun getUserHealthData(): List<UserHealthData> {
        return healthDataService.getHealthData().toUserHealthData()
    }

    override suspend fun getItems(
        queryParams: Map<String, String>,
        itemId: String,
        body: ItemBody
    ): ItemResponse {
        return healthDataService.getItems(queryParams = queryParams, itemId = itemId, body = body)
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