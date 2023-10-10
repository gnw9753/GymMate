package com.example.gymmate.questionpage

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmate.R
import com.example.gymmate.data.GenerateWorkout
import com.example.gymmate.data.ReadExerciseCSV
import com.example.gymmate.data.exercisedata.Exercise
import com.example.gymmate.data.exercisedata.ExerciseDay
import com.example.gymmate.data.exercisedata.ExerciseRepository
import com.example.gymmate.data.logindata.LoginEntity
import com.example.gymmate.data.logindata.LoginEntityRepository
import com.example.gymmate.data.logindata.OfflineLoginEntityRepository
import com.example.gymmate.data.userdata.User
import com.example.gymmate.data.userdata.UserEntity
import com.example.gymmate.data.userdata.UserEntityRepository
import com.example.gymmate.data.userdata.UserInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class QuestionPageViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val userEntityRepository: UserEntityRepository,
    private val loginEntityRepository: LoginEntityRepository,
) : ViewModel() {
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


    fun genderToString() {
        gender = if (male) "male"
        else if (female) "female"
        else "other"
    }

    fun goalToString() {
        goal = if (loseWeight) "lose weight"
        else "gain muscle"
    }

    fun increasePageIndex() {
        if (_uiState.value.pageIndex <= 9) _uiState.value =
            _uiState.value.copy(pageIndex = _uiState.value.pageIndex + 1)
        if (_uiState.value.progress < 1f) _uiState.value =
            _uiState.value.copy(progress = _uiState.value.progress + 0.1f)

    }

    fun decreasePageIndex() {
        if (_uiState.value.pageIndex > 1) _uiState.value =
            _uiState.value.copy(pageIndex = _uiState.value.pageIndex - 1)
        if (_uiState.value.progress >= 0.2f) _uiState.value =
            _uiState.value.copy(progress = _uiState.value.progress - 0.1f)
    }

    fun setPageIndex(index: Int) {
        if (index in 1..10) {
            _uiState.value = uiState.value.copy(pageIndex = index)
        }
    }

    // Reminder to create a proper validation
    // So it can teleport to the page where the data is missing
    // Not too important as there are checks on each page to assure ...
    // the data type needed is received
    fun validate(): Boolean {
        if (
            name.isNotEmpty() &&
            email.isNotEmpty() &&
            (age.toInt() > 0) &&
            hasSelectGender &&
            hasSelectGoal &&
            (height.toFloat() > 0f) &&
            (weight.toFloat() > 0f) &&
            (amountSelected > 1)
        ) return true
        return false
    }

    fun dayToList(): MutableList<Boolean> {
        var tempDayList: MutableList<Boolean> = mutableListOf()
        tempDayList.add(monday)
        tempDayList.add(tuesday)
        tempDayList.add(wednesday)
        tempDayList.add(thursday)
        tempDayList.add(friday)
        tempDayList.add(saturday)
        tempDayList.add(sunday)
        return tempDayList
    }

    fun createWorkout(id: Int, context: Context): List<ExerciseDay> {
        val inputStream = context.resources.openRawResource(R.raw.resist_train_planner)
        val csvFile = ReadExerciseCSV(inputStream)
        val exerciseCSV = csvFile.read()
        return GenerateWorkout(id, goal, dayToList(), exerciseCSV).generateWorkout()
    }

    suspend fun toUserEntityDatabase() {
        var user: UserEntity = UserEntity(
            name = name,
            email = email,
            age = age.toInt(),
            gender = gender,
            goal = goal,
            height = height.toFloat(),
            weight = weight.toFloat(),
        )
        userEntityRepository.insertUser(user)
    }

    fun timestampToDateString(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        sdf.timeZone = TimeZone.getTimeZone("Pacific/Auckland") // Set the timezone to New Zealand (Pacific/Auckland)
        val date = Date(timestamp)
        return sdf.format(date)
    }

    private suspend fun toLoginEntityDatabase(id: Int, email: String) {
        val currentDate = Calendar.getInstance()
        currentDate.set(Calendar.HOUR_OF_DAY, 0)
        currentDate.set(Calendar.MINUTE, 0)
        currentDate.set(Calendar.SECOND, 0)
        currentDate.set(Calendar.MILLISECOND, 0)
        val dateStarted = currentDate.timeInMillis

        val loginEntity: LoginEntity = LoginEntity(
            id = id,
            email = email,
            dateStarted = dateStarted
        )
        loginEntityRepository.insertUserLogin(loginEntity)
    }

    suspend fun createUserProfile(context: Context) {

        toUserEntityDatabase()
        var user = userEntityRepository.getUserByEmail(email)
        viewModelScope.launch {
            var userExercise: List<ExerciseDay>?
            val userEntity = user.firstOrNull()
            userExercise = userEntity?.let { createWorkout(it.id, context) }

            if (userExercise != null) {
                for (exerciseDay in userExercise) {
                    for (exercise in exerciseDay.exerciseList) {
                        exerciseRepository.insertExercise(exercise)
                    }
                }
            }
            if (userEntity != null) {
                toLoginEntityDatabase(userEntity.id, userEntity.email)
            }
        }

        UserInstance.currentUser = User(
            user_id = 0,
            user_email = email,
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


