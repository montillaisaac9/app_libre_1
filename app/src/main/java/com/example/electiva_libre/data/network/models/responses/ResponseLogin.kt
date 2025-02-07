package com.example.electiva_libre.data.network.models.responses

import com.example.electiva_libre.data.db.entity.UserEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseLogin (

    @SerialName("refresh")
    val refresh: String,

    @SerialName("access")
    val access: String,

    @SerialName("user")
    val user: User
)

@Serializable
data class User(
    @SerialName("id")
    val id: Int,

    @SerialName("username")
    val username: String,

    @SerialName("email")
    val email: String,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String
)

fun User.toEntity() = UserEntity(
    id = this.id,
    username = this.username,
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName
)