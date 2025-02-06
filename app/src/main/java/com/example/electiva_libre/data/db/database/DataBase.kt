package com.example.electiva_libre.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.electiva_libre.data.db.dao.UserDao
import com.example.electiva_libre.data.db.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)

abstract class DataBase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
}