package com.example.gymmate.questionpage
/*
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import com.example.gymmate.R
import com.example.gymmate.data.GenerateWorkout
import com.example.gymmate.data.ReadExerciseCSV
import com.example.gymmate.data.exercisedata.Exercise
import com.example.gymmate.data.exercisedata.ExerciseDay
import com.example.gymmate.data.exercisedata.ExerciseRepository
import com.example.gymmate.data.userdata.User

class OldViewModel(private val exerciseRepository: ExerciseRepository) : ViewModel() {

    var exerciseUiState by mutableStateOf(ExerciseUiState())
        private set

    suspend fun insertExercise(context: Context) {
        println("insert pressed")
        val inputStream = context.resources.openRawResource(R.raw.resist_train_planner)
        val csvFile = ReadExerciseCSV(inputStream)
        val rows = csvFile.read()

        val daysAvailable: List<Boolean> = listOf(false, true, false, true, false, true, false)
        val exerciseList: List<ExerciseDay> = listOf()
        var user: User = User(
            "user@gmail.com",
            "Jun",
            "male",
            20,
            168,
            68,
            "Losing weight",
            daysAvailable,
            exerciseList
        )
        val gn = GenerateWorkout(user, rows)

        user.exercise_schedule = gn.generateWorkout()

        for (exerciseDay in user.exercise_schedule) {
            for (exercise in exerciseDay.exerciseList) {
                println(exercise)
                exerciseRepository.insertExercise(exercise)
            }
        }


    }
}


data class ExerciseUiState(
    val exerciseDetails: ExerciseDetails = ExerciseDetails(),
)

data class ExerciseDetails(
    var day: String = "",
    val muscleGroup: String = "",
    val exerciseName: String = "",
    val difficulty: String = "",
    val ulc: String = "",
    val pp: String = "",
    val modality: String = "",
    val joint: String = ""
)


fun ExerciseDetails.toItem(): Exercise = Exercise(
    day = day,
    muscleGroup = muscleGroup,
    exerciseName = exerciseName,
    difficulty = difficulty,
    ulc = ulc,
    pp = pp,
    modality = modality,
    joint = joint
)
*/