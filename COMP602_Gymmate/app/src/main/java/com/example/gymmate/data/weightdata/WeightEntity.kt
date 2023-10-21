package com.example.gymmate.data.weightdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_weight")
data class WeightEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val weight: Float,
)