package com.example.interviewpractice.network

import retrofit2.http.GET


interface HealthDataService {
    @GET("/list")
    suspend fun getList(): HealthResponse
}