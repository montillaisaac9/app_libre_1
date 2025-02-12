package com.example.electiva_libre.domain.repository

import com.example.electiva_libre.data.db.entity.UserEntity
import com.example.electiva_libre.data.network.models.params.ParamsLogin
import com.example.electiva_libre.data.network.models.params.ParamsRegister
import com.example.electiva_libre.data.network.models.responses.ResponseLogin
import com.example.electiva_libre.data.network.models.responses.User
import com.example.electiva_libre.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.JsonElement

interface UserRepostory {

    suspend fun inserUser(user: UserEntity)

    fun getUser(): Flow<UserEntity>

    suspend fun deteleUser()

    suspend fun login(params: ParamsLogin): Flow<ApiResult<ResponseLogin>?>

    suspend fun register(params: ParamsRegister): Flow<ApiResult<User>?>
}