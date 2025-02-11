package com.example.electiva_libre.data.network.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseListNews(

    @SerialName("data")
    val data: List<News>
)

@Serializable
data class News(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("published_date") val publishedDate: String,
    @SerialName("is_featured") val isFeatured: Boolean,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("author") val author: Int
)