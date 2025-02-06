package com.example.electiva_libre.di.modules


import com.example.electiva_libre.data.repository.UserRepositoryImp
import com.example.electiva_libre.domain.repository.UserRepostory

import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@dagger.Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUserRepository(impl: UserRepositoryImp): UserRepostory = impl

}
