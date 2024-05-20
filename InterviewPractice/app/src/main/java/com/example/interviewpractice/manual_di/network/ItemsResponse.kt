package com.example.interviewpractice.manual_di.network

data class ItemBody(
    val name: String,
    val description: String,
    val price: Float,
    val tax: Float? = null,
)


data class ItemResponse(
    val itemId: Int,
    val q: String?,
    val item: ItemBody,
    val sortBy: String?
)