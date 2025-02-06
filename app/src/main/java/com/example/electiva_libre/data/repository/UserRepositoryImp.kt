package com.example.electiva_libre.data.repository

import com.example.electiva_libre.data.db.dao.UserDao
import com.example.electiva_libre.data.db.entity.UserEntity
import com.example.electiva_libre.domain.repository.UserRepostory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val userDao: UserDao): UserRepostory {

    override fun getUser(): Flow<UserEntity> = userDao.getUserFlow()

}