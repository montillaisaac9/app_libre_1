package com.example.electiva_libre.data.network.models.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParamTestimonialAdd(
    @SerialName("content") val content: String,
    @SerialName("is_approved") val isApproved: Boolean,
    @SerialName("author") val author: Int
)
