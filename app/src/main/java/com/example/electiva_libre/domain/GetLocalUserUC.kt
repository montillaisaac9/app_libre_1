package com.example.electiva_libre.domain

import com.example.electiva_libre.data.db.entity.UserEntity
import com.example.electiva_libre.data.repository.UserRepositoryImp
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserUC @Inject constructor(private val userRepo: UserRepositoryImp) {
    operator fun invoke(): Flow<UserEntity> = userRepo.getUser()
}