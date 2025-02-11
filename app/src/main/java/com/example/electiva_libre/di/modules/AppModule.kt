package com.example.electiva_libre.di.modules


import com.example.electiva_libre.data.repository.DataRespositoryImp
import com.example.electiva_libre.data.repository.UserRepositoryImp
import com.example.electiva_libre.domain.DataUC
import com.example.electiva_libre.domain.UserUC
import com.example.electiva_libre.domain.repository.DataRepostory
import com.example.electiva_libre.domain.repository.UserRepostory

import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@dagger.Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUserRepository(impl: UserRepositoryImp): UserRepostory = impl

    @Provides
    fun provideDataRepository(impl: DataRespositoryImp): DataRepostory = impl

    @Provides
    fun provideUserUseCase(repositoryImpl: UserRepositoryImp) = UserUC(repositoryImpl)

    @Provides
    fun provideDataUseCase(repositoryImpl: DataRespositoryImp) = DataUC(repositoryImpl)

}
