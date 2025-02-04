package com.example.electiva_libre.data.network.client

import alcaravan.guiriri.contribuyente.data.networks.response.ResponseOdoo
import alcaravan.guiriri.contribuyente.data.pref.SessionPref
import android.content.Context
import com.example.electiva_libre.BuildConfig
import com.example.electiva_libre.data.network.response.CookieSerializable
import com.example.electiva_libre.data.pref.CookiesPref
import com.example.electiva_libre.utils.ApiResult
import com.example.electiva_libre.utils.code
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.cookies.cookies
import io.ktor.client.request.cookie
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
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
            emit(ApiResult.Success(httpClient.get(urlString = host + url).body()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message ?: "Something went wrong"))
        }
    }

    suspend inline fun <reified T> post(
        url: String,
        bodyJson: JsonElement,
        host: String? = BuildConfig.HOST_BASE
    ): Flow<ApiResult<ResponseOdoo<T>?>> = flow {
        emit(ApiResult.Loading())
        try {
            val cookiesSave = CookieSerializable.deserializeCookie(CookiesPref.instanceValueFlow(context).value)
            val response = httpClient.post(urlString = host + url) {
                contentType(ContentType.Application.Json)
                setBody(bodyJson)
               cookiesSave?.let {
                    cookie(it.name, it.value,it.maxAge,it.expires, it.domain, it.path, it.secure, it.httpOnly, it.extensions)
               }
            }.body<ResponseOdoo<T>>()
            val cookies = httpClient.cookies(host.orEmpty()).first()
            CookiesPref.setInstancesPref(context = context, CookieSerializable.serializeCookie(cookies))
            emit(
                if (response.error == null) ApiResult.Success(response)
                else {
                    if (response.error.dataError?.message == "Session expired"){
                        SessionPref.setSesion(context, true)
                    }
                    ApiResult.Error(response.error.dataError?.message?:response.error.message)
                }
            )
        } catch (e: Exception) {
            emit(ApiResult.Error(code(0, context) ?: "Something went wrong"))
        }
    }
}