package com.example.electiva_libre.data.network.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseListCourses(

    @SerialName("data")
    val data: List<Course>
)


@Serializable
data class Course(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("duration") val duration: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    @SerialName("is_workshop") val isWorkshop: Boolean,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("instructor") val instructor: Int
)