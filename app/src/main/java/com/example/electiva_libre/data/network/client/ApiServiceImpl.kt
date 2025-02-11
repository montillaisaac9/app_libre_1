package com.example.electiva_libre.data.network.client


import alcaravan.guiriri.contribuyente.data.networks.response.ErrorResponse
import android.content.Context
import com.example.electiva_libre.BuildConfig
import com.example.electiva_libre.data.network.models.responses.RegisterErrorResponse
import com.example.electiva_libre.utils.ApiResult
import com.example.electiva_libre.utils.code
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.expectSuccess
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonElement
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    val httpClient: HttpClient,
    @ApplicationContext val context: Context
) {

    suspend inline fun <reified T> get(
        url: String,
        host: String? = BuildConfig.HOST_BASE
    ): Flow<ApiResult<T>> = flow {
        emit(ApiResult.Loading())
        try {
            val response = httpClient.get(urlString = host + url) {
                expectSuccess = false
                contentType(ContentType.Application.Json)
            }

            if (response.status.isSuccess()) {
                // Respuesta exitosa: deserializamos al tipo T esperado
                val result: T = response.body()
                emit(ApiResult.Success(result))
            } else {
                // Respuesta con error: deserializamos en ErrorResponse
                val errorResponse: ErrorResponse = response.body()
                emit(ApiResult.Error(errorResponse.detail))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message ?: "Something went wrong"))
        }
    }

    suspend inline fun <reified T> post(
        url: String,
        bodyJson: JsonElement,
        host: String? = BuildConfig.HOST_BASE
    ): Flow<ApiResult<T>> = flow {
        emit(ApiResult.Loading())
        try {
            val response = httpClient.post(urlString = host + url) {
                contentType(ContentType.Application.Json)
                setBody(bodyJson)
            }
            if (response.status.isSuccess()) {
                val result: T = response.body()
                emit(ApiResult.Success(result))
            } else {
                val errorResponse: ErrorResponse = response.body()
                emit(ApiResult.Error(errorResponse.detail))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message ?: "Something went wrong"))
        }
    }


    suspend inline fun <reified T, reified E> postCustom(
        url: String,
        bodyJson: JsonElement,
        host: String? = BuildConfig.HOST_BASE
    ): Flow<ApiResult<T>> = flow {
        emit(ApiResult.Loading())
        try {
            val response = httpClient.post(urlString = host + url) {
                expectSuccess = false
                contentType(ContentType.Application.Json)
                setBody(bodyJson)
            }
            if (response.status.isSuccess()) {
                val result: T = response.body()
                emit(ApiResult.Success(result))
            } else {
                val errorResponse: E = response.body()
                if (errorResponse is RegisterErrorResponse)  emit(ApiResult.Error(errorResponse.username[0]))
                else emit(ApiResult.Error("Algo ha ido mal culpen julio"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message ?: "Something went wrong"))
        }
    }


}