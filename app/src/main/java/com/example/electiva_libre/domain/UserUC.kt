package com.example.electiva_libre.domain

import com.example.electiva_libre.data.db.entity.UserEntity
import com.example.electiva_libre.data.network.models.params.ParamsLogin
import com.example.electiva_libre.data.network.models.params.ParamsRegister
import com.example.electiva_libre.data.network.models.responses.ResponseLogin
import com.example.electiva_libre.data.network.models.responses.User
import com.example.electiva_libre.data.repository.UserRepositoryImp
import com.example.electiva_libre.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUC @Inject constructor(private val userRepo: UserRepositoryImp) {

    suspend fun insertUser(user: UserEntity) = userRepo.inserUser(user)

    fun getLocalUser(): Flow<UserEntity> = userRepo.getUser()

    suspend fun deleteUserLocale() = userRepo.deteleUser()

    suspend fun login(params: ParamsLogin): Flow<ApiResult<ResponseLogin>?> = userRepo.login(params)

    suspend fun register(params: ParamsRegister): Flow<ApiResult<User>?> = userRepo.register(params)
}