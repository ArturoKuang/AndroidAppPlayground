package com.example.interviewpractice.fakes

import com.example.interviewpractice.manual_di.network.HealthDataService
import com.example.interviewpractice.manual_di.network.HealthResponse

class FakeHealthDataService : HealthDataService {
    lateinit var getListResult: HealthResponse
    override suspend fun getList(): HealthResponse {
        return getListResult
    }
}