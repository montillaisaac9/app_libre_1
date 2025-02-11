package com.example.electiva_libre.data.repository

import com.example.electiva_libre.data.network.client.ApiServiceImpl
import com.example.electiva_libre.data.network.models.params.ParamTestimonialAdd
import com.example.electiva_libre.data.network.models.responses.ResponseListCourses
import com.example.electiva_libre.data.network.models.responses.ResponseListNews
import com.example.electiva_libre.data.network.models.responses.ResponseListTestimonials
import com.example.electiva_libre.data.network.models.responses.Testimonial
import com.example.electiva_libre.domain.repository.DataRepostory
import com.example.electiva_libre.domain.repository.UserRepostory
import com.example.electiva_libre.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import javax.inject.Inject

class DataRespositoryImp  @Inject constructor(
    private val apiService: ApiServiceImpl,
): DataRepostory {

    override suspend fun getNews(): Flow<ApiResult<ResponseListNews>?> = apiService.get<ResponseListNews>(
    url = "api/news/")

    override suspend fun getCourses(): Flow<ApiResult<ResponseListCourses>?> = apiService.get<ResponseListCourses>(
        url = "api/courses/")

    override suspend fun getTestimonials(): Flow<ApiResult<ResponseListTestimonials>?> = apiService.get<ResponseListTestimonials>(
        url = "api/testimonials/")

    override suspend fun postTestimonials(params: ParamTestimonialAdd): Flow<ApiResult<Testimonial>?> =
        apiService.post<Testimonial>(
            url = "api/testimonials/",
            bodyJson = Json.encodeToJsonElement(params),
        )



}