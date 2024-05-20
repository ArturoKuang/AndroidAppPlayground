package com.example.interviewpractice.hilt.network

import com.example.interviewpractice.manual_di.network.HealthResponse
import com.example.interviewpractice.manual_di.network.ItemBody
import com.example.interviewpractice.manual_di.network.ItemResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface HealthDataService {
    @GET("/list")
    suspend fun getHealthData(): HealthResponse

    @POST("/items/{item_id}")
    suspend fun getItems(
        @Path("item_id") itemId: String,
        @QueryMap queryParams: Map<String, String>,
        @Body body: ItemBody
    ): ItemResponse
}