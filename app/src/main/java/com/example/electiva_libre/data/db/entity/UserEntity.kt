package com.example.electiva_libre.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity (
    @PrimaryKey val id: Int = 0,
    val username: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = ""
)