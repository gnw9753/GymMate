package com.example.gymmate.data.logindata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_login")
data class LoginEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var email: String,
    var dateStarted: Long,
    val mondayLoggedIn: Boolean = false,
    val tuesdayLoggedIn: Boolean = false,
    val wednesdayLoggedIn: Boolean = false,
    val thursdayLoggedIn: Boolean = false,
    val fridayLoggedIn: Boolean = false,
    val saturdayLoggedIn: Boolean = false,
    val sundayLoggedIn: Boolean = false
)