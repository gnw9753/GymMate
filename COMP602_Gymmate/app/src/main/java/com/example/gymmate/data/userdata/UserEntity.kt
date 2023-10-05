package com.example.gymmate.data.userdata

import androidx.room.Entity
import androidx.room.PrimaryKey

// This is the entity for the database

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String,
    var email: String,
    var age: Int,
    var gender: String,
    var goal: String,
    var height: Float,
    var weight: Float,
)