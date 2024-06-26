package com.example.interviewpractice.manual_di.network


import com.google.gson.annotations.SerializedName

data class HealthResponseItem(
    @SerializedName("age")
    val age: Int, // 30
    @SerializedName("conditions")
    val conditions: List<String>,
    @SerializedName("height")
    val height: Int, // 175
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("name")
    val name: String, // John Doe
    @SerializedName("weight")
    val weight: Int, // 70
    @SerializedName("image")
    val image: String,
)