package com.example.electiva_libre.data.network.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterErrorResponse(
    @SerialName("username")
    val username: List<String>
)
