package com.example.gymmate.homepage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmate.data.exercisedata.ExerciseRepository
import com.example.gymmate.data.logindata.LoginEntity
import com.example.gymmate.data.logindata.LoginEntityRepository
import com.example.gymmate.data.userdata.UserInstance
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

class HomepageViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val loginEntityRepository: LoginEntityRepository,
) : ViewModel() {
    var displayTutorial by mutableStateOf(false)
    var currentDate: Calendar by mutableStateOf(Calendar.getInstance())

    val homePageUiState: StateFlow<HomePageUiState> =
        loginEntityRepository.getUserLoginByEmail(getUserEmail()).map { HomePageUiState(it)}
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomePageUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun dayCompletedPressed(day: String) {
        val weekDayList = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
        val index = weekDayList.indexOf(day)

        println("DAY PRESSED: " + index)
        viewModelScope.launch {
            val currentLoginEntity = homePageUiState.value.loginEntity
            if (currentLoginEntity != null) {
                when (index) {
                    0 -> loginEntityRepository.updateUserLogin(currentLoginEntity.copy(mondayLoggedIn = true))
                    1 -> loginEntityRepository.updateUserLogin(currentLoginEntity.copy(tuesdayLoggedIn = true))
                    2 -> loginEntityRepository.updateUserLogin(currentLoginEntity.copy(wednesdayLoggedIn = true))
                    3 -> loginEntityRepository.updateUserLogin(currentLoginEntity.copy(thursdayLoggedIn = true))
                    4 -> loginEntityRepository.updateUserLogin(currentLoginEntity.copy(fridayLoggedIn = true))
                    5 -> loginEntityRepository.updateUserLogin(currentLoginEntity.copy(saturdayLoggedIn = true))
                    6 -> loginEntityRepository.updateUserLogin(currentLoginEntity.copy(sundayLoggedIn = true))
                }
            }
        }

    }

    private fun getUserEmail(): String {
        var userEmail: String = ""
        if(UserInstance.currentUser != null) {
            userEmail = UserInstance.currentUser!!.user_email
        }
        println("User email is :$userEmail")
        return userEmail
    }

    fun checkIfLoggedIn(day: String): Boolean {
        var didLogIn = false

        viewModelScope.launch {
            val currentLoginEntity = homePageUiState.value.loginEntity
            if (currentLoginEntity != null) {
                when (day) {
                    "Mon" -> if(currentLoginEntity.mondayLoggedIn)didLogIn = true
                    "Tue" -> if(currentLoginEntity.tuesdayLoggedIn)didLogIn = true
                    "Wed" -> if(currentLoginEntity.wednesdayLoggedIn)didLogIn = true
                    "Thu" -> if(currentLoginEntity.thursdayLoggedIn)didLogIn = true
                    "Fri" -> if(currentLoginEntity.fridayLoggedIn)didLogIn = true
                    "Sat" -> if(currentLoginEntity.saturdayLoggedIn)didLogIn = true
                    "Sun" -> if(currentLoginEntity.sundayLoggedIn)didLogIn = true
                }
            }
        }
        return didLogIn
    }

}

data class HomePageUiState(val loginEntity: LoginEntity? = null)


