package com.example.interviewpractice.fakes

import com.example.interviewpractice.manual_di.network.HealthDataRemoteSource
import com.example.interviewpractice.manual_di.network.HealthResponseItem

class FakeHealthDataRemoteSource : HealthDataRemoteSource{

    lateinit var getHealthDataResult: List<HealthResponseItem>
    override suspend fun getHealthData(): List<HealthResponseItem> {
        return getHealthDataResult
    }
}