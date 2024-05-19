package com.example.interviewpractice.hilt.network

import com.example.interviewpractice.manual_di.network.HealthResponse
import retrofit2.http.GET

interface HealthDataService {
    @GET("/list")
    suspend fun getHealthData(): HealthResponse
}