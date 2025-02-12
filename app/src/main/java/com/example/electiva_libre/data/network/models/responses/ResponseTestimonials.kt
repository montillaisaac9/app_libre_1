package com.example.electiva_libre.data.network.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseListTestimonials(

    @SerialName("data")
    val data: List<Testimonial>
)

@Serializable
data class Testimonial(
    @SerialName("content") val content: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("is_approved") val isApproved: Boolean,
    @SerialName("author_detail") val author: Author
)

@Serializable
data class Author(
    @SerialName("id") val id: Int,
    @SerialName("username") val username: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String
)