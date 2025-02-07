package com.example.electiva_libre.data.network.models.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParamsLogin(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)
