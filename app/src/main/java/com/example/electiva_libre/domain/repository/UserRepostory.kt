package com.example.electiva_libre.domain.repository

import com.example.electiva_libre.data.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepostory {

    fun getUser(): Flow<UserEntity>
}