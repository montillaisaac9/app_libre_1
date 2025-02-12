package com.example.electiva_libre.data.repository


import alcaravan.guiriri.contribuyente.data.networks.response.RequestBody
import alcaravan.guiriri.contribuyente.data.pref.InstancesPref
import android.content.Context
import com.example.electiva_libre.data.db.dao.UserDao
import com.example.electiva_libre.data.db.entity.UserEntity
import com.example.electiva_libre.data.network.client.ApiServiceImpl
import com.example.electiva_libre.data.network.models.params.ParamsLogin
import com.example.electiva_libre.data.network.models.params.ParamsRegister
import com.example.electiva_libre.data.network.models.responses.RegisterErrorResponse
import com.example.electiva_libre.data.network.models.responses.ResponseLogin
import com.example.electiva_libre.data.network.models.responses.User
import com.example.electiva_libre.domain.repository.UserRepostory
import com.example.electiva_libre.utils.ApiResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userDao: UserDao,
    private val apiService: ApiServiceImpl,
): UserRepostory {

    override suspend fun inserUser(user: UserEntity) = userDao.insertUser(user)

    override fun getUser(): Flow<UserEntity> = userDao.getUserFlow()

    override suspend fun deteleUser() = userDao.deleteUser()

    override suspend fun login(params: ParamsLogin): Flow<ApiResult<ResponseLogin>?> =
        apiService.post<ResponseLogin>(
            url = "api/auth/login",
            bodyJson = Json.encodeToJsonElement(params),
        )

    override suspend fun register(params: ParamsRegister): Flow<ApiResult<User>?> =
        apiService.postCustom<User, RegisterErrorResponse>(
            url = "api/auth/register",
            bodyJson = Json.encodeToJsonElement(params),
        )


}