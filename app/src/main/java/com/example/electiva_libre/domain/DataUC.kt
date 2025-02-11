package com.example.electiva_libre.domain

import com.example.electiva_libre.data.network.models.params.ParamsLogin
import com.example.electiva_libre.data.network.models.responses.ResponseListCourses
import com.example.electiva_libre.data.network.models.responses.ResponseListNews
import com.example.electiva_libre.data.network.models.responses.ResponseListTestimonials
import com.example.electiva_libre.data.network.models.responses.ResponseLogin
import com.example.electiva_libre.data.repository.DataRespositoryImp
import com.example.electiva_libre.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataUC  @Inject constructor(private val userRepo: DataRespositoryImp) {


    suspend fun getNews(): Flow<ApiResult<ResponseListNews>?> = userRepo.getNews()

    suspend fun getCourses(): Flow<ApiResult<ResponseListCourses>?> = userRepo.getCourses()

    suspend fun getTestimonials(): Flow<ApiResult<ResponseListTestimonials>?> = userRepo.getTestimonials()



}