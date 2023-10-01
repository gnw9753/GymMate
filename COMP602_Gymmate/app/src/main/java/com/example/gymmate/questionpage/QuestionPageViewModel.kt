package com.example.gymmate.questionpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gymmate.data.exercisedata.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuestionPageViewModel(private val exerciseRepository: ExerciseRepository) : ViewModel() {
    /*
    var questionPageUiState by mutableStateOf(QuestionPageUiState())
        private set

     */
    private val _uiState = MutableStateFlow(QuestionPageUiState())
    val uiState: StateFlow<QuestionPageUiState> = _uiState.asStateFlow()

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var age by mutableStateOf("")
    var gender by mutableStateOf("")
    var goal by mutableStateOf("")
    var height by mutableStateOf("")
    var weight by mutableStateOf("")

    // For Gender Page
    var male by mutableStateOf(false)
    var female by mutableStateOf(false)
    var other by mutableStateOf(false)
    var hasSelectGender by mutableStateOf(false)

    // For Goal Page
    var loseWeight by mutableStateOf(false)
    var gainMuscle by mutableStateOf(false)
    var hasSelectGoal by mutableStateOf(false)

    // For Days page
    var monday by mutableStateOf(false)
    var tuesday by mutableStateOf(false)
    var wednesday by mutableStateOf(false)
    var thursday by mutableStateOf(false)
    var friday by mutableStateOf(false)
    var saturday by mutableStateOf(false)
    var sunday by mutableStateOf(false)
    var amountSelected by mutableIntStateOf(0)



    fun increasePageIndex() {
        if (_uiState.value.pageIndex <= 9) _uiState.value = _uiState.value.copy(pageIndex = _uiState.value.pageIndex + 1)
        if (_uiState.value.progress < 1f) _uiState.value = _uiState.value.copy(progress = _uiState.value.progress + 0.1f)

    }

    fun decreasePageIndex() {
        if (_uiState.value.pageIndex > 1) _uiState.value = _uiState.value.copy(pageIndex = _uiState.value.pageIndex - 1)
        if (_uiState.value.progress >= 0.2f) _uiState.value = _uiState.value.copy(progress = _uiState.value.progress - 0.1f)
    }

    fun setPageIndex(index: Int){
        if(index in 1..10) {
            _uiState.value = uiState.value.copy(pageIndex = index)
        }
    }

    // Reminder to create a proper validation
    // So it can teleport to the page where the data is missing
    // Not too important as there are checks on each page to assure ...
    // the data type needed is received
    fun validate():Boolean{
        if(
            name.isNotEmpty() &&
            email.isNotEmpty() &&
            (age.toInt() > 0) &&
            hasSelectGender &&
            hasSelectGoal &&
            (height.toFloat() > 0f) &&
            (weight.toFloat() > 0f) &&
            (amountSelected > 1)
        )return true
        return false
    }
}

data class QuestionPageUiState(
    var userDetails: UserDetails = UserDetails(),
    var pageIndex: Int = 1,
    var progress: Float = 0.1f,
)

data class UserDetails(
    var name: String = "",
    var email: String = "",
    var age: Int = 0,
    var gender: String = "",
    var goal: String = "",
    var height: Int = 0,
    var weight: Int = 0,
    var daysAvailable: List<Boolean> = listOf()
)
