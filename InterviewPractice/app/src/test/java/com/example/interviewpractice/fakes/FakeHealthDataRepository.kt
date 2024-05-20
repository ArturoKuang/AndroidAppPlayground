package com.example.interviewpractice.fakes

import com.example.interviewpractice.hilt.data.HealthDataRepository
import com.example.interviewpractice.hilt.data.UserHealthData

class FakeHealthDataRepository : HealthDataRepository {
    lateinit var getUserHealthDataResult: List<UserHealthData>
    override suspend fun getUserHealthData(): List<UserHealthData> {
        return getUserHealthDataResult
    }
}