package com.example.interviewpractice.manual_di.network

import retrofit2.http.GET


interface HealthDataService {
    @GET("/list")
    suspend fun getList(): HealthResponse
}