package com.example.gymmate.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmate.data.userdata.User
import com.example.gymmate.data.userdata.UserEntityRepository
import com.example.gymmate.data.userdata.UserInstance
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class LoginPageViewModel(
    private val userEntityRepository: UserEntityRepository
) : ViewModel() {

    var isError by mutableStateOf(false) // Initialize isError here
    var emailEntered by mutableStateOf("")
    var emailFound by mutableStateOf(false)

    suspend fun checkEmailInDatabase() :Boolean{
        val enteredEmail = emailEntered.trim()
        val userEntity = userEntityRepository.getUserByEmail(enteredEmail)
        viewModelScope.launch {
            val user = userEntity.firstOrNull()
            emailFound = (user != null)
            println(emailFound)
            if (user != null) {
                UserInstance.currentUser = User(
                    user_id = user.id,
                    user_email = user.email,
                    user_name = "",
                    user_gender = "",
                    user_age = 0,
                    user_height = 0f,
                    user_weight = 0f,
                    user_goal = "",
                    user_days = emptyList(),
                    exercise_schedule = emptyList(),
                    isInitialized = false
                )
            }
            else{
                isError = true
            }
        }
        return emailFound
    }

}