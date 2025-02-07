package com.example.electiva_libre.data.network.models.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParamsRegister(
    @SerialName("username")
    val username: String,

    @SerialName("password")
    val password: String,

    @SerialName("email")
    val email: String,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String
)