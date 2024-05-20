package com.example.interviewpractice.fakes

import com.example.interviewpractice.manual_di.network.HealthData
import com.example.interviewpractice.manual_di.network.HealthRepository

class FakeHealthRepository : HealthRepository {
    var healthDataResults: List<HealthData> = listOf()
    var healthDataException: Exception? = null

    override suspend fun getHealthData(): List<HealthData> {
        healthDataException?.let {
            throw it
        }

        return healthDataResults
    }
}