package com.example.electiva_libre.domain.repository

import com.example.electiva_libre.data.network.models.params.ParamTestimonialAdd
import com.example.electiva_libre.data.network.models.responses.ResponseListCourses
import com.example.electiva_libre.data.network.models.responses.ResponseListNews
import com.example.electiva_libre.data.network.models.responses.ResponseListTestimonials
import com.example.electiva_libre.data.network.models.responses.Testimonial
import com.example.electiva_libre.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface DataRepostory {

    suspend fun getNews(): Flow<ApiResult<ResponseListNews>?>

    suspend fun getCourses(): Flow<ApiResult<ResponseListCourses>?>

    suspend fun getTestimonials(): Flow<ApiResult<ResponseListTestimonials>?>

    suspend fun postTestimonials(params: ParamTestimonialAdd): Flow<ApiResult<Testimonial>?>
}