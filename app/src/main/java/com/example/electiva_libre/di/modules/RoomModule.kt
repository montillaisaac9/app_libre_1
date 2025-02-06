package com.example.electiva_libre.di.modules


import android.content.Context
import androidx.room.Room
import com.example.electiva_libre.data.db.database.DataBase
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private val database_name = "base_de_datos"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, DataBase::class.java, database_name
    )
//        .addMigrations(MIGRATION_1_2)
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideDaoUser(db: DataBase) = db.getUserDao()

    
}